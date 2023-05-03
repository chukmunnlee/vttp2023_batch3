package day07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LoopClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 3000);

        OutputStream os = socket.getOutputStream();
        //BufferedOutputStream bos = new BufferedOutputStream(os);
        ObjectOutputStream oos = new ObjectOutputStream(os);

        InputStream is = socket.getInputStream();
        //BufferedInputStream bis = new BufferedInputStream(is);
        ObjectInputStream ois = new ObjectInputStream(is);

        String msg = "";

        Console cons = System.console();

        while (!"exit".equals(msg.trim().toLowerCase())) {
            msg = cons.readLine("> ");
            oos.writeUTF(msg);
            oos.flush();

            msg = ois.readUTF();
            System.out.printf("[from server]: %s\n", msg);
        }

        os.close();
        socket.close();

    }
    
}
