package fortunecookie;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    
    public static void main(String[] args) throws UnknownHostException, IOException {

        // Default port: 12345
        int port = Constants.PORT;

        // If port is provided in args
        if (args.length > 1) {
            port = Integer.parseInt(args[0]);
        }

        // Try to connect to server on port
        Socket sock = new Socket("localhost", port );

        // Instantiate scanner to receive input from user
        Scanner scan = new Scanner(System.in);

        // Continuously receive input from user
        while (true) {

            // Receive command to either get cookie or close server
            System.out.printf("\nCommand (%s/%s): ", Constants.GET_COOKIE, Constants.CLOSE);
            
            String command = scan.nextLine().trim().toLowerCase();

            // If command is not get-cookie nor close OR is blank
            if ((!command.equals(Constants.GET_COOKIE) && 
                !command.equals(Constants.CLOSE)) || 
                command.isBlank())
            {
                System.out.println("\nCommand does not exist!");
                // Get user to provide a new command
                continue;
            }
            
            // Send user command to server
            ClientSender cs = new ClientSender(sock);
            cs.send(command);

            // If command sent is "close", end client
            if (command.equals(Constants.CLOSE)) {
                // Close scanner and socket
                sock.close();
                scan.close();
                break;
            }
            // Receive respone from server
            ClientReceiver cr = new ClientReceiver(sock);
            cr.receive();
        }
        
    }
}
