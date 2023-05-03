package day07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket sock;

    public ClientHandler(Socket s) {
        sock = s;
    }

    @Override
    public void run() {

        try {
            InputStream is = sock.getInputStream();
            //BufferedInputStream bis = new BufferedInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(is);

            OutputStream os = sock.getOutputStream();
            //BufferedOutputStream bos = new BufferedOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            boolean exit = false;
            String msg;

            while (!exit) {
                msg = ois.readUTF();
                if ("exit".equals(msg.trim().toLowerCase())) {
                    System.out.println("Closing cient connection");
                    is.close();
                    exit = true;
                    continue;
                }

                if ("exception".equals(msg.trim().toLowerCase())) 
                    throw new IOException("This is a forced exception");

                System.out.printf("[from client]: %s\n", msg);

                oos.writeUTF(msg.toUpperCase());
                oos.flush();
            }
        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
            ex.printStackTrace();
        } finally {
            System.out.println("In finally block, closing socket");
            try { sock.close(); } catch (Exception e) { }
        }
    }
}
