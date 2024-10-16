package fortunecookie;

import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;


public class ClientSender {

    // Why sould socket be set to final?
    private final Socket sock;
    
    public ClientSender(Socket sock) {
        this.sock = sock;
    }

    public void send(String command) {
        try {
            OutputStream os = this.sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // Send user command to the server
            dos.writeUTF(command);
            dos.flush();

            System.out.println("\n>>> Command sent to server\n");
        }

        catch (IOException e) {
            System.err.printf("An error occured: %s\n", e.getMessage());
        }
    }
}
