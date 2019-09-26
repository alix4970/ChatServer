/**
 * @Author Ali Al-Sharefi
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class implements Runnable interface with the method named "run()"
 */

public class ServerThread implements Runnable {

    private String name;
    private int portNumber;

    /**
     * This is the constructor, takes in 2 parameters
     * @param s a name for a task
     * @param port the port number that the server will run on
     */

    public ServerThread(String s, int port){
        name = s;
        portNumber = port;

    }

    /**
     * The "run()" method that will be executed from the runnable interface
     */
    @Override
    public void run(){
        System.out.println("Creating a Server socket on the port: " + portNumber);
        ServerSocket ss = null;

        /**
         * we create a try/catch in case of something is not going as planned
         */
        try {
            ss = new ServerSocket(portNumber);
            Socket s = ss.accept();
            System.out.println("Connected to client");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            /**
             * This will be our "Loginformation" it will create a log for every conversation beetween the users on the server
             *
             */
            Logger log = Logger.getLogger("LogInformation");
            FileHandler fileHandler = new FileHandler("/Users/alialsharefi/Desktop/Datamatiker/3.semester/Kode/ChatServer/LogInformation/chatlog.txt", true);
            SimpleFormatter formatter = new SimpleFormatter();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            fileHandler.setFormatter(formatter);
            log.addHandler(fileHandler);
            String msg ="";
            String secondUser;
            secondUser = dis.readUTF();


            System.out.println("Username: " + secondUser);

            String str = "", s1 = "";

            while (!str.equals("STOP")){
                str = dis.readUTF();
                System.out.println(secondUser + " : " + str);
                msg = str;
                dos.writeUTF(msg);
                log.info(secondUser + ": " + str);
                dos.flush();
            }

            dis.close();
            s.close();
            ss.close();

            /**
             * We try to catch the error, and will get the trace of the error
             */

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
