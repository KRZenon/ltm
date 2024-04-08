package slide130.b3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

                // Nhận số từ client
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received number from client: " + clientMessage);

                // Đảo ngược số và gửi lại cho client
                int number = Integer.parseInt(clientMessage);
                String reversedNumber = String.valueOf(reverseNumber(number));
                byte[] sendData = reversedNumber.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Sent response to client: " + reversedNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }
}
