package fortunecookie;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClientHandler implements Runnable {

    private Socket sock;
    private Cookie cookieManager;
    private boolean isRunning;

    public CookieClientHandler(Socket sock, Cookie cookieManager) {
        this.sock = sock;
        this.cookieManager = cookieManager;
    }

    @Override
    public void run(){

        try {

            String threadName = Thread.currentThread().getName();
            
            System.out.printf("%s\n", threadName);

            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            while (true) {
                String clientCommand = dis.readUTF();

                String cookieToSend = "";

                if (clientCommand.equals("get-cookie")) {
                    cookieToSend = cookieManager.getRandomCookie();
                }

                else if (clientCommand.equals("close")) {

                    break;
                }

                dos.writeUTF(cookieToSend);
                dos.flush();
            }
            
            dos.close();
            bos.close();
            os.close();

            dis.close();
            bis.close();
            is.close();
            
            System.err.println("Connection ended!");

            sock.close();
        }

        catch (IOException ie) {
            System.err.println(ie.getStackTrace());
        }
    }
} 