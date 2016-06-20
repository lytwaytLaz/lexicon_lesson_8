package lexicon_8_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-20
 */
public class EchoServer
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.err.println("usage: EchoServer <port>");
            System.exit(1);
        }

        int portNum = Integer.parseInt(args[0]);

        try (ServerSocket echoServer = new ServerSocket(portNum))
        {
            //create socket
            int port = 5555;
            ServerSocket echoServer = new ServerSocket(port);
            System.err.println("Started server on port " + port);

            //repeatedly wait for connections, and process
            while (true) {

            }
        }
    }


}
