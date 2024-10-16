package fortunecookie;

import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedInputStream;

public class ServerReceiver {
    
    private final Socket sock;

    public ServerReceiver(Socket sock) {
        this.sock = sock;
    }

    public String receive() {

        try {
            InputStream is = this.sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String clientCommand = dis.readUTF();

            // Return client command
            return clientCommand;  
        }

        catch (IOException ie) {
            System.err.printf("An error occured: %s\n", ie.getMessage());
            try {
                sock.close();
            } 
            catch (Exception e) {
                System.err.printf("An error occured: %s\n", e.getMessage());
            }
            return null;
        }
    }
}
