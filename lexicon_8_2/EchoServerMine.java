package lexicon_8_2;

import com.sun.corba.se.spi.activation.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-21
 */
public class EchoServerMine {
    final static int PORT = 6666;
    public static void main(String[] args) {
        System.out.println("Started server. Listening on port " + PORT);
        // while loop to keep listening
        while (true)
        {
            try (ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
            {
                System.out.println("Client connected on port " + PORT);
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
                    out.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
