package slide130.b1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

                // Chuyển đổi chuỗi sang chữ hoa
                String response = clientMessage.toUpperCase();

                // Gửi lại kết quả cho client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent response to client: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
