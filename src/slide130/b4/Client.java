package slide130.b4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;
        final int BUFFER_SIZE = 1024;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                // Sinh số ngẫu nhiên từ 0 đến 100
                int number = random.nextInt(101);

                // Gửi số và gửi địa chỉ IP của client tới server
                String message = String.valueOf(number);
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                // Đợi một khoảng thời gian ngắn trước khi gửi số tiếp theo
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
