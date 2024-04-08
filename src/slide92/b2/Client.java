package slide92.b2;

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

            // Send array of integers to server
            int[] array = {1, 2, 3, 4};
            outputStream.writeInt(array.length);
            for (int num : array) {
                outputStream.writeInt(num);
            }

            // Receive the result from server
            int result = inputStream.readInt();
            System.out.println("Result from server: " + result);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
