package fortunecookie;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cookie {
    
    private ArrayList<String> cookies;

    public Cookie() {
        this.cookies = new ArrayList<>();
    }

    // read cookies from file
    public void readFile(String filePath) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        while (true) {
            String cookie = br.readLine();
            if (cookie == null) {
                break;
            }
            cookies.add(cookie);
        }

        br.close();
    }

    // get Random Cookie
    public String getRandomCookie() {
        Random rand = new Random();
        // Get a random index of cookies
        int randCookieIndex = rand.nextInt(cookies.size());

        return cookies.get(randCookieIndex);
    }

}
