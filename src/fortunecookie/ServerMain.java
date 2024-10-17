
package fortunecookie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ExecutorService threadPool = null;
        ServerSocket server = null;

        try {
            // Start thread pool
            threadPool = Executors.newFixedThreadPool(4);

            // <-- Getting the port number and file from user -->

            // Exit program if number of arguments provided is not 2
            if (args.length != 2) {
                System.err.println(">>> Proper usage: port number (12345) + cookies.txt");
                System.exit(1);
            }

            int port = Integer.parseInt(args[0]);

            String cookiefilePath = args[1];
            // <- Getting the cookies ready to be sent to client --> 

            // Initialise Cookie
            Cookie cookieManager = new Cookie();

            // Read the cookie file
            cookieManager.readFile(cookiefilePath);

            System.out.printf(">>> Waiting for connection at %s\n", port);
            
            // Open a port to accept connection
            server = new ServerSocket(port);

            while (true) {
                Socket sock = server.accept();

                CookieClientHandler handler = new CookieClientHandler(sock, cookieManager);

                // It will be delegrated and continue running on this main thread.
                threadPool.submit(handler);
            }

        }
        finally {
            server.close();
            threadPool.shutdown();
        }
    
    }    
}