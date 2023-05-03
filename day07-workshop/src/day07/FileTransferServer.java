package day07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class FileTransferServer {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(8080);

        System.out.println("Staring file transfer server on port 3000");

        Socket client = server.accept();

        try {

            InputStream is = client.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            System.out.printf("Transferring file %s (%d)\n", fileName, fileSize);

            byte[] buff = new byte[4 * 1024];
            long totalRecevied = 0;
            int size = 0;
            OutputStream os = new FileOutputStream(fileName + (new Date()).toString());
            BufferedOutputStream bos = new BufferedOutputStream(os);

            while ((size = dis.read(buff)) > 0) {
                bos.write(buff, 0, size);
                totalRecevied += size;
                System.out.printf("total bytes received: %d\n", totalRecevied);
            }

            System.out.printf("FINAL total bytes received: %d\n", totalRecevied);

            bos.flush();
            bos.close();
            os.close();

        } finally {
            client.close();
        }

        server.close();

    }

}