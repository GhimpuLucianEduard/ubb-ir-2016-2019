import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServicePersonal {

    private RepoDbPersonal repoDbPersonal;

    public Personal findPersonal(int id) {
        return repoDbPersonal.findOne(id);
    }

    public ServicePersonal(RepoDbPersonal repoDbPersonal) {
        this.repoDbPersonal = repoDbPersonal;
    }

    public Personal findByUsername(String username) throws RepositoryException {
        final Personal[] aux = new Personal[1];
        repoDbPersonal.findAll().forEach(x -> {
            if (x.getUserName().compareTo(username) == 0) {
                aux[0] = x;
            }
        });
        if (aux[0] != null) {
            return aux[0];
        } else {
            return null;
        }
    }


    public void loginHandler() throws RepositoryException {


        boolean keepProcessing = true;
        ServerSocket server=null;
        try{
            server=new ServerSocket(5555);

            while(keepProcessing){
                Socket client=server.accept();
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");
                System.out.println("Socket Extablished...");

                try(BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter writer=new PrintWriter(client.getOutputStream())) {

                    String p = br.readLine();
                    System.out.println(p);
                    System.out.println(p);
                    System.out.println(p);
                    System.out.println(p);
                    System.out.println(p);
                    System.out.println(p);

                    String rez = "";
                    String[] parts = p.split(";");
                    Personal aux = findByUsername(parts[0]);
                    if(aux==null){
                        rez="false";
                    }else {
                        if (aux.getPass().compareTo(parts[1])==0){
                            rez="true";
                        }
                        else {
                            rez="false";
                        }
                    }

                    System.out.println("Response: " + rez);
                    writer.write(rez);
                    writer.flush();
                    client.close();


                    System.out.println("Finished  processing request ...");
                } catch (IOException e) {
                    System.out.println("Error in processing client request " + e);
                }




            }
        }catch(IOException ex){

        } finally{
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}

