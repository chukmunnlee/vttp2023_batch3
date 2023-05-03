package day07;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import day07.ClientHandler;

public class LoopServerMain {

    public static void main(String[] args) throws Exception {

        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        ServerSocket server = new ServerSocket(3000);

        String input;
        int count = 0;

        while (true) {

            System.out.println("Waiting for new client connection...");

            Socket sock = server.accept();

            System.out.printf("Got a new connection: %s\n\n", sock);

            ClientHandler handler = new ClientHandler(sock);
            System.out.println("Dispatching client to thread pool");
            thrPool.submit(handler);

            // count++;
            // if (count >= 2)
            //     break;

            /*
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(bis);

            System.out.printf("Got a new connection: %s\n\n", sock);

            boolean exit = false;
            String msg;

            while (!exit) {
                msg = ois.readUTF();
                if ("exit".equals(msg.trim().toLowerCase())) {
                    System.out.println("Closing client connection");
                    exit = true;
                    continue;
                }

                System.out.printf(">>> from client: %s\n", msg);
            }
            */
        }

        //System.out.println("============ Out of the main loop");
        //thrPool.shutdown();
    }
    
}
