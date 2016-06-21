package lexicon_8_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author princeton.edu
 */
public class EchoServer
{
    public static void main(String[] args) throws IOException
    {

        int port = 7777;
        //start with creating a socket which is like a
        //virtual connection
        ServerSocket serverSock = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        // repeatedly wait for connection, and process
        while (true)
        {
            // a "blocking" call that waits until connection is requested
            Socket clientSock = serverSock.accept();
            System.err.println("Accepted connection from client");

//            // open IO stream NOT WORKING
//            InputStream inS = clientSock.getInputStream();
//            OutputStream outS;
//            byte[] myByte;
//            while ((myByte = inS.read(byte myBytes[])) != -1)
//            {
//                if (outS != null) {
//                    outS.write(myByte);
//                }
//            }

            //open IO stream ORIGINAL using In and Out class
            In in = new In (clientSock);
            Out out = new Out (clientSock);


            //waits for data, reads until connection dies
            //readLine() blocks until server receives a new line from client
            String s;
            while ((s = in.readLine()) != null)
            {
                out.println(s);
            }

            //close IO streams, then socket
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSock.close();
        }
    }


}
