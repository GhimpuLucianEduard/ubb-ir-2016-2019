import Models.Personal.ValidatorPersonal;
import Repository.RepoDbPersonal;
import Service.MainService;
import Service.ServicePersonal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.IServer;
import services.ServerException;
import utils.AbstractServer;
import utils.ChatRpcConcurrentServer;

import java.io.IOException;
import java.util.Properties;

public class Main {
    private static int defaultPort=55555;
    public static void main(String[] args) {
//        Properties serverProps=new Properties();
//        try {
//            //serverProps.load(Main.class.getResourceAsStream("/chatserver.properties"));
//            System.out.println("Server properties set. ");
//            serverProps.list(System.out);
//        } catch (IOException e) {
//            System.err.println("Cannot find chatserver.properties "+e);
//            return;
//        }

        ApplicationContext context=new ClassPathXmlApplicationContext("CurseMPP.xml");
        MainService  mainService = context.getBean(MainService.class);



        IServer chatServerImpl=new RunServerRemote(mainService);
        int chatServerPort=defaultPort;
        try {
            chatServerPort = 55556;
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+chatServerPort);
        AbstractServer server = new ChatRpcConcurrentServer(chatServerPort, chatServerImpl);
        try {
            server.start();
        } catch (utils.ServerException e) {
            e.printStackTrace();
        }
    }
}
