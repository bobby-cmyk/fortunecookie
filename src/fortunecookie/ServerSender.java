package fortunecookie;

import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

public class ServerSender {
    
    private final Socket sock;
    private Cookie cookie;

    public ServerSender(Socket sock, Cookie cookie) {
        this.sock = sock;
        this.cookie = cookie;
    }

    public void sendCookie() {

        try {
            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // If client command is get-cookie, send a random cookie to client
            dos.writeUTF(cookie.getRandomCookie());
            dos.flush();
            System.out.println(">>> Sent a cookie to client");
        }
        
        catch (IOException ie) {
            System.err.printf("An error occured: %s\n", ie.getMessage());
            try {
                sock.close();
            } 
            catch (Exception e) {
                System.err.printf("An error occured: %s\n", e.getMessage());
            }
        }
    }
}
