package slide92.b3;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server waiting...");

            Socket socket = serverSocket.accept();
            System.out.println("Client accessed.");

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

            // Đọc chuỗi từ client
            String clientMessage = inputStream.readLine();
            System.out.println("String received from client: " + clientMessage);

            // Tách chuỗi thành các từ vựng và gửi lại cho client
            StringTokenizer tokenizer = new StringTokenizer(clientMessage);
            while (tokenizer.hasMoreTokens()) {
                outputStream.println(tokenizer.nextToken());
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
