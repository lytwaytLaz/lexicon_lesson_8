package lesson_8_1;

import java.io.*;
import java.net.Socket;

/**
 * @author László Hágó
 * @version 1.0
 * @since 2016-06-20
 */
public class EchoClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("usage: java EchoClient <IP> <port> <message>");
            return;
        }

        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]))) {
            OutputStream os = socket.getOutputStream();
            String message = args[2];

//            // add line break so that the echo server recognizies end of in-stream
//            if (!message.substring(message.length() - 1, message.length()).equals("\n"))
//                message = message + "\n";
//            byte[] bytes = message.getBytes();
//            os.write(bytes);
//            os.flush();

            PrintWriter writer = new PrintWriter(os);
            writer.println(message);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());

//            InputStream is = socket.getInputStream();
//            int ch;
//            while ((ch = is.read()) != -1)
//                System.out.println((char) ch);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
