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
        System.out.println("print 1 pos");
        try (InputStream is = this.sock.getInputStream()) {
            System.out.println("print 2 pos");
            BufferedInputStream bis = new BufferedInputStream(is);
            System.out.println("print 3 pos");
            DataInputStream dis = new DataInputStream(bis);
            System.out.println("print 4 pos");
            String clientCommand = dis.readUTF();
            System.out.println("print 5 pos");
            
            // Return client command
            return clientCommand;  
        }

        catch (IOException e) {
            System.err.printf("An error occured: %s\n", e.getMessage());
            return null;
        }
    }


}
