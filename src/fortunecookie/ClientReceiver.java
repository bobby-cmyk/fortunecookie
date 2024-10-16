package fortunecookie;

import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ClientReceiver {

    // Why should this be final?
    private final Socket sock;

    public ClientReceiver(Socket sock) {
        this.sock = sock;
    }

    public void receive() {
        try (InputStream is = this.sock.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            // Receive response from server
            String serverResponse = dis.readUTF();
            
            // Print out the fortune cookie reply between quotations
            System.out.printf("\"%s\"\n", serverResponse);
        }
        catch (IOException e) {
            System.err.printf("An error occured: %s\n", e.getMessage());
        }
    }
 }