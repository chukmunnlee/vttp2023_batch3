package day07;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) throws Exception {

        int port = 3000;
        String msg = "hello, world";
        switch (args.length) {
            case 1:
                msg = args[0];
                break;

            case 2:
                port = Integer.parseInt(args[0]);
                msg = args[1];

            default:
                System.err.printf("Error: ");
                System.exit(0);
        }

        Socket sock = new Socket("127.0.0.1", port);

        OutputStream os = sock.getOutputStream();
        os.write(msg.getBytes());
        os.flush();

        os.close();
        sock.close();

    }
    
}
