package slide130.b4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

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

                // Lấy dữ liệu từ client và địa chỉ IP của client
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();

                // In thông tin số và địa chỉ IP của client
                System.out.println("Received number from client: " + clientMessage + ", Client IP: " + clientAddress.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
