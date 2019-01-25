package utils;

import rpcprotocol.ChatClientRpcReflectionWorker;
import services.IServer;

import java.net.Socket;


public class ChatRpcConcurrentServer extends AbsConcurrentServer {
    private IServer chatServer;
    public ChatRpcConcurrentServer(int port, IServer chatServer) {
        super(port);
        this.chatServer = chatServer;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
       // ChatClientRpcWorker worker=new ChatClientRpcWorker(chatServer, client);
        ChatClientRpcReflectionWorker worker=new ChatClientRpcReflectionWorker(chatServer, client);

        Thread tw=new Thread(worker);
        return tw;
    }
}
