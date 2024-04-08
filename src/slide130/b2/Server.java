package slide130.b2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;
        final int BUFFER_SIZE = 1024;

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Server is running...");

            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);

            while (true) {
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: " + clientMessage);

                // Đảo ngược chuỗi và gửi lại cho client
                String reversedMessage = reverseString(clientMessage);
                byte[] sendData = reversedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Sent response to client: " + reversedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
