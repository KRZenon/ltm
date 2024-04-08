package slide92.b1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // Gửi chuỗi cho server
            String message = "Welcome";
            outputStream.writeUTF(message);
            System.out.println("String send to server: " + message);

            // Nhận lại 2 ký tự từ server
            String response = inputStream.readUTF();
            System.out.println("Server return: " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

