package fortunecookie;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cookie {
    
    // Cookie messages read from file will be saved in ArrayList
    private ArrayList<String> cookies;

    public Cookie() {
        this.cookies = new ArrayList<>();
    }

    // Read cookies from file
    public void readFile(String filePath) {

        try (FileReader fr = new FileReader(filePath)) {

            BufferedReader br = new BufferedReader(fr);

            while (true) {
                // Read line by line from file
                String cookie = br.readLine();

                // Break the reading at end of file
                if (cookie == null) {
                    break;
                }

                // Add each line as a String to the ArrayList<String> cookies
                cookies.add(cookie);
            }
        }

        catch (IOException e) {
            System.err.printf("An error occurred: %s\n", e.getMessage());
        } 
    }

    // get Random Cookie
    public String getRandomCookie() {

        Random rand = new Random();

        // Get a random index of cookies
        int randCookie = rand.nextInt(cookies.size());

        // Return a random cookie with index chosen
        return cookies.get(randCookie);
    }

}
