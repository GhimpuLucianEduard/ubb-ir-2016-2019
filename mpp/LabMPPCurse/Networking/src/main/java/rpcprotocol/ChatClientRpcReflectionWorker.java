package rpcprotocol;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import Models.Rezervare;
import com.sun.org.apache.regexp.internal.RE;
import dto.*;
import services.IObserver;
import services.IServer;
import services.ServerException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class ChatClientRpcReflectionWorker implements Runnable, IObserver {
    private IServer server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ChatClientRpcReflectionWorker(IServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }


    private static Response okResponse=new Response.Builder().type(ResponseType.OK).build();
    //  private static Response errorResponse=new Response.Builder().type(ResponseType.ERROR).build();
    private Response handleRequest(Request request){
        Response response=null;
        String handlerName="handle"+(request).type();
        System.out.println("HandlerName "+handlerName);
        try {
            Method method=this.getClass().getDeclaredMethod(handlerName, Request.class);
            response=(Response)method.invoke(this,request);
            System.out.println("Method "+handlerName+ " invoked");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Response handleLOGIN(Request request){
        System.out.println("Login request ..."+request.type());
        PersonalDTO udto=(PersonalDTO) request.data();
        Personal user= DTOUtils.getFromDTO(udto);
        try {
            server.login(user, this);
            return okResponse;
        } catch (ServerException e) {
            connected=false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
        //return null;
    }

    private Response handleGET_DESTINATII(Request request)
    {
        System.out.println("GetDestinatii Request ...");
        //DestinatieDTO udto=(DestinatieDTO)request.data();
        //User user=DTOUtils.getFromDTO(udto);
        try {
            Destinatie[] friends=server.getDestinatii();
            DestinatieDTO[] frDTO=DTOUtils.getDTO(friends);
            return new Response.Builder().type(ResponseType.GET_DESTINATII).data(frDTO).build();
        } catch (ServerException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }


    private Response handleGET_CURSE(Request request)
    {
        System.out.println("GetCUrse Request ...");
        try {
            Cursa[] friends=server.getCurse();
            CursaDTO[] frDTO=DTOUtils.getDTO(friends);
            return new Response.Builder().type(ResponseType.GET_CURSE).data(frDTO).build();
        } catch (ServerException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_LOCURI(Request request)
    {
        CursaDTO udto=(CursaDTO) request.data();
        Cursa c= DTOUtils.getFromDTO(udto);
        System.out.println("Get locuri Request ...");
        try {
            Loc[] friends=server.getLocuri(c);
            LocDTO[] frDTO=DTOUtils.getDTO(friends);
            return new Response.Builder().type(ResponseType.GET_LOCURI).data(frDTO).build();
        } catch (ServerException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }


    private Response handleADD_REZERVARE(Request request) {
        try {
            RezervareDTO udto=(RezervareDTO) request.data();
            server.addRezervare(udto.getIdCursa(),udto.getNume(),udto.getPrenume(),udto.getNrLocuri());
            return new Response.Builder().type(ResponseType.OK).data(null).build();
        } catch (ServerException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

//
//    private Response handleLOGOUT(Request request){
//        System.out.println("Logout request...");
//        UserDTO udto=(UserDTO)request.data();
//        User user=DTOUtils.getFromDTO(udto);
//        try {
//            server.logout(user, this);
//            connected=false;
//            return okResponse;
//
//        } catch (ChatException e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
//    private Response handleSEND_MESSAGE(Request request){
//            System.out.println("SendMessageRequest ...");
//            MessageDTO mdto=(MessageDTO)request.data();
//            Message message=DTOUtils.getFromDTO(mdto);
//            try {
//                server.sendMessage(message);
//                return okResponse;
//            } catch (ChatException e) {
//                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//            }
//
//
//    }
//
//    private Response handleGET_LOGGED_FRIENDS(Request request){
//        System.out.println("GetLoggedFriends Request ...");
//        UserDTO udto=(UserDTO)request.data();
//        User user=DTOUtils.getFromDTO(udto);
//        try {
//            User[] friends=server.getLoggedFriends(user);
//            UserDTO[] frDTO=DTOUtils.getDTO(friends);
//            return new Response.Builder().type(ResponseType.GET_LOGGED_FRIENDS).data(frDTO).build();
//        } catch (ChatException e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }

    @Override
    public void newRezervare(Cursa[] curse) throws ServerException {
        CursaDTO[] rez = DTOUtils.getDTO(curse);
        Response response = new Response.Builder().type(ResponseType.UPDATE).data(rez).build();
        try {
            sendResponse(response);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
