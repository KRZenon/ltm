package slide109.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server.");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gửi các số ngẫu nhiên từ 0 đến 200 tới server
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int number = random.nextInt(201);
                writer.println(String.valueOf(number));
            }

            // Đọc và in ra kết quả từ server
            String serverResponse;
            while ((serverResponse = reader.readLine()) != null) {
                System.out.println("Server response: " + serverResponse);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
