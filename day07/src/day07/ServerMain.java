package day07;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws Exception {

        int port = 3000;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);

        System.out.printf("Starting server on port %d\n", port);

        // Create a server to listen on port
        ServerSocket server = new ServerSocket(port);

        boolean exit = false;

        while (!exit) {
            Socket sock = server.accept();

            System.out.printf("Got a connection: %s\n", sock);

            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = bis.readAllBytes();
            String msg = new String(buffer);
            System.out.printf(">>> from client: %s\n", msg);

            if ("exit".equals(msg.trim().toLowerCase())) {
                System.out.println("Bye bye!");
                sock.close();
                server.close();
                System.exit(0);
            }

            Thread.sleep(30 * 1000);

            /* 
            int size = 0;
            byte[] buffer = new byte[1024];
            while ((size = is.read(buffer)) > 0) {
                String msg = new String(buffer);
                System.out.printf(">>> from client: %s\n", msg);
            }
            */

            System.out.printf("Closing connection and exit");
            sock.close();
        }
    }

}