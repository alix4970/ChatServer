/**
 * @Author Ali Al-Sharefi
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class will run our clients
 * We throw an Exception, otherwise we couldnt run the program..
 */

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int portNumber = 0;

        System.out.println("Chose your port: \n" +
                "Press 1: port 3000 \n" +
                "Press 2: port 3301 \n" +
                "Press 3: port 3302 \n" +
                "Press 4: port 3303 \n" +
                "Press 5: port 3304");

        boolean runport = true;


        /**
         * This while loop will keep running until you have chosen a specific port
         */
        while (runport){
            int choices;

            try {

                choices = sc.nextInt();

                if (choices == 1){

                    portNumber = 3300;
                    runport = false;

                }else if(choices == 2){

                    portNumber = 3301;
                    runport = false;

                }else if (choices == 3){

                    portNumber = 3302;
                    runport = false;

                }else if (choices == 4){

                    portNumber = 3303;
                    runport = false;

                }else if (choices == 5){

                    portNumber = 3304;
                    runport = false;

                }else {
                    System.out.println("Try picking a valid port");
                }

            }catch (InputMismatchException e){
                System.out.println("WRONG CHOICE TRY AGAIN");
                System.out.println("TRY AGAIN - PICK A NUMBER BETWEEN 1-5!");
                System.exit(0);
            }
        }

        /**
         * we create a try/catch in case of something going wrong
         */

        try {
            /**
             * We create a new socket, the socket is the bound to the port number
             * We need to get data that, we create a Datainput and Dataoutputstream
             * A reader so it can read line by line
             */
            Socket socket = new Socket("Localhost", portNumber);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "";
            String allMsg = "";
            String user;

            boolean run = true;
            /**
            * this while loop will keep running, till you have chosed a username.
             *  it must match the regex pattern, otherwise you gonna get a error
            */
            while (run){
                System.out.print("Pick a username: ");
                user = br.readLine();

                if (user.matches("^[a-zA-Z.\\-_]{1,12}$")){
                    dos.writeUTF(user);
                    run = false;

                } else {
                    System.out.println("Your username must be max be 12 chars long \n Only letters, digits, ‘-‘ and ‘_’ allowed");
                }
            }

            System.out.println("Welcome to the chatroom!");
            System.out.println("You can now text your friend's in this room");


            /**
             * This while loop will keep on running till you write "STOP" with big letter
             * As soon you write "STOP" the client will log off
             */
            while (!str.equals("STOP")){
                str = br.readLine();
                /*dos.writeUTF(str);
                allMsg = dis.readUTF();
                System.out.println("You're text: " + allMsg);
                */

                if (str.matches("^[0-9a-zA-Z.\\-_,.!?æøåÆØÅ ]{1,250}$")){
                    dos.writeUTF(str);
                    allMsg = dis.readUTF();
                    System.out.println("You're text: " + allMsg);

                    dos.flush();
                    System.out.println("Send");
                    System.out.println("Type \"STOP\" to disconnect from the server");
                }else{
                    System.out.println("Too manny charaters! Mimimize your text");
                }
                /*
                dos.flush();
                System.out.println("Send");
                System.out.println("Type \"STOP\" to disconnect from the server");
                */
            }

            dos.close();
            socket.close();


        }catch (ConnectException e){


            System.exit(0);

        }
    }
}
