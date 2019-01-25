package rpcprotocol;

import Models.Cursa.Cursa;
import Models.Destinatie.Destinatie;
import Models.Loc;
import Models.Personal.Personal;
import dto.*;
import services.IObserver;
import services.IServer;
import services.ServerException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ChatServerRpcProxy implements IServer {
    private String host;
    private int port;

    private IObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ChatServerRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();
    }

    public void login(Personal pers, IObserver client) throws ServerException {
        initializeConnection();
        PersonalDTO udto= DTOUtils.getDTO(pers);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            closeConnection();
            throw new ServerException(err);
        }
    }

    @Override
    public Destinatie[] getDestinatii() throws ServerException {

        Request req=new Request.Builder().type(RequestType.GET_DESTINATII).data(null).build();
        sendRequest(req);
        Response response=readResponse();

        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            throw new ServerException(err);
        }
        DestinatieDTO[] frDTO=(DestinatieDTO[])response.data();
        Destinatie[] friends=DTOUtils.getFromDTO(frDTO);
        return friends;
    }

    @Override
    public Cursa[] getCurse() throws ServerException {

        Request req=new Request.Builder().type(RequestType.GET_CURSE).data(null).build();
        sendRequest(req);
        Response response=readResponse();

        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            throw new ServerException(err);
        }
        CursaDTO[] frDTO=(CursaDTO[])response.data();
        Cursa[] friends=DTOUtils.getFromDTO(frDTO);
        return friends;
    }

    @Override
    public Loc[] getLocuri(Cursa c) throws ServerException {
        CursaDTO cdto = DTOUtils.getDTO(c);
        Request req=new Request.Builder().type(RequestType.GET_LOCURI).data(cdto).build();
        sendRequest(req);
        Response response=readResponse();

        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            throw new ServerException(err);
        }
        LocDTO[] frDTO=(LocDTO[])response.data();
        Loc[] friends=DTOUtils.getFromDTO(frDTO);
        return friends;
    }

    @Override
    public void addRezervare(int idCursa, String nume, String prenume, int nrLocuri) throws ServerException {

        RezervareDTO udto= new RezervareDTO(idCursa,nume,prenume,nrLocuri);
        Request req=new Request.Builder().type(RequestType.ADD_REZERVARE).data(udto).build();
        sendRequest(req);
        Response response=readResponse();
        if (response.type()== ResponseType.OK){
            this.client=client;
            return;
        }
        if (response.type()== ResponseType.ERROR){
            String err=response.data().toString();
            throw new ServerException(err);
        }
    }


    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request)throws ServerException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new ServerException("Error sending object "+e);
        }

    }

    private Response readResponse() throws ServerException {
        Response response=null;
        try{
            /*synchronized (responses){
                responses.wait();
            }
            response = responses.remove(0);    */
            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() throws ServerException {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    private void handleUpdate(Response response){
        if (response.type()== ResponseType.UPDATE){
            CursaDTO[] curse = (CursaDTO[])response.data();
            Cursa[] rez = DTOUtils.getFromDTO(curse);
            System.out.println("update new rezervare in ");
            try {
                client.newRezervare(rez);
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isUpdate(Response response){
        return response.type()== ResponseType.UPDATE;
    }

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{

                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
