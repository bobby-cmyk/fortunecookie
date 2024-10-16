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

    public void send(String clientCommand) {
        try (OutputStream os = sock.getOutputStream()) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            if (clientCommand.equals(Constants.GET_COOKIE)) {
                // If client command is get-cookie, send a random cookie to client
                dos.writeUTF(cookie.getRandomCookie());
                dos.flush();
            }
            else {
                dos.writeUTF("Command does not exist");
                dos.flush();
            }
        }

        catch (IOException e) {
            System.err.printf("An error occured: %s\n", e.getMessage());
        }
    }
}
