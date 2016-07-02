package lesson_8_3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-21
 */
class MainServer {
    final static int PORT = 60065;
    public static void main(String[] args) throws IOException {
        Synonyms syn = new Synonyms();
        System.out.println("Activating server, creating thesaurus-HashMap");
        try (DatagramSocket dgSocket = new DatagramSocket(PORT))
        {
            byte[] data = new byte[1000];
            DatagramPacket dgPacket = new DatagramPacket(data, data.length);
            while (true)
            {
                dgSocket.receive(dgPacket);
                // important to use getLength() to eliminate null values in byte array (data)
                String key = new String(dgPacket.getData(), 0, dgPacket.getLength());

                byte[] dataOut = syn.getSynonyms(key).getBytes("UTF-8");
                dgPacket = new DatagramPacket(dataOut, dataOut.length,
                        dgPacket.getAddress(), dgPacket.getPort());
                dgSocket.send(dgPacket);
            }
        }
    }
}
