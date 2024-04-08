package slide92.b2;

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
            System.out.println("Server is waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client is connected.");

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // Receive array of integers from client
            int[] array = new int[inputStream.readInt()];
            for (int i = 0; i < array.length; i++) {
                array[i] = inputStream.readInt();
            }

            // Calculate sum of elements in the array
            int sum = 0;
            for (int num : array) {
                sum += num;
            }

            // Send the result back to client
            outputStream.writeInt(sum);

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

