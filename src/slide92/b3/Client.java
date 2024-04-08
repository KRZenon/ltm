package slide92.b3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

            // Gửi chuỗi cho server
            String message = "This is a sample string";
            outputStream.println(message);
            System.out.println("String send to server: " + message);

            // Nhận các từ vựng từ server và in ra màn hình
            String word;
            while ((word = inputStream.readLine()) != null) {
                System.out.println(word);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
