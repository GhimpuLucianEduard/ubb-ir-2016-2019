package rpcprotocol;
import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import Models.Rezervare;
import dto.*;
import services.IObserver;
import services.IServer;
import services.ServerException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CacheRequest;
import java.net.Socket;


public class ChatClientRpcWorker implements Runnable, IObserver {
    private IServer server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ChatClientRpcWorker(IServer server, Socket connection) {
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
        if (request.type()==RequestType.LOGIN){
            System.out.println("Login request ..."+request.type());
            PersonalDTO udto=(PersonalDTO)request.data();
            Personal user= DTOUtils.getFromDTO(udto);
            try {
                server.login(user, this);
                return okResponse;
            } catch (ServerException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type()==RequestType.GET_DESTINATII){
            System.out.println("Get destinatii request ..."+request.type());
            try {
                Destinatie[] desti = server.getDestinatii();
                DestinatieDTO[] desti2 = DTOUtils.getDTO(desti);
                return new Response.Builder().type(ResponseType.GET_DESTINATII).data(desti2).build();
            } catch (ServerException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type()==RequestType.GET_CURSE){
            System.out.println("Get curse request ..."+request.type());
            CursaDTO udto=(CursaDTO)request.data();
            Cursa cursa= DTOUtils.getFromDTO(udto);
            try {
                Loc[] c = server.getLocuri(cursa);
                LocDTO[] desti2 = DTOUtils.getDTO(c);
                return new Response.Builder().type(ResponseType.GET_LOCURI).data(desti2).build();
            } catch (ServerException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type()==RequestType.GET_CURSE){
            System.out.println("Get curse request ..."+request.type());
            try {
                Cursa[] c = server.getCurse();
                CursaDTO[] desti2 = DTOUtils.getDTO(c);
                return new Response.Builder().type(ResponseType.GET_CURSE).data(desti2).build();
            } catch (ServerException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if (request.type()==RequestType.ADD_REZERVARE){
            System.out.println("add rezervare request ..."+request.type());
            try {
                RezervareDTO udto=(RezervareDTO)request.data();
                server.addRezervare(udto.getIdCursa(),udto.getNume(),udto.getPrenume(),udto.getNrLocuri());
                return new Response.Builder().type(ResponseType.OK).data(null).build();
            } catch (ServerException e) {
                connected=false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        return response;
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }

    @Override
    public void newRezervare(Cursa[] curse) throws ServerException {
        CursaDTO[] rez = DTOUtils.getDTO(curse);
        Response resp=new Response.Builder().type(ResponseType.UPDATE).data(rez).build();
        System.out.println("new cursa ");
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
