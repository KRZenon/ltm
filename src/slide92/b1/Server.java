package slide92.b1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is waiting...");

            Socket socket = serverSocket.accept();
            System.out.println("Client accessed.");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // Đọc chuỗi từ client
            String clientMessage = inputStream.readUTF();
            System.out.println("String reiceived from client: " + clientMessage);

            // Lấy 2 ký tự đầu của chuỗi
            String response = clientMessage.substring(0, 2);

            // Gửi lại cho client
            outputStream.writeUTF(response);

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

