package fortunecookie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    
    public static void main(String[] args) throws UnknownHostException, IOException {

        // Default port: 12345
        int port = Constants.PORT;

        // If port is provided in args
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // Try to connect to server on port
        Socket sock = new Socket("localhost", port );

        // Continuously receive input from user
        System.out.println("Connected!");

        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("get-cookie / close: ");

            String clientCommand = scanner.nextLine();

            dos.writeUTF(clientCommand); 

            dos.flush();

            if (clientCommand.equals("close")) {
                break;
            }

            String cookieResponse = dis.readUTF();

            System.out.println(cookieResponse);

        }
        scanner.close();

        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();

        sock.close();
    }
}
