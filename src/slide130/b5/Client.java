package slide130.b5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

            // Sinh mảng số nguyên ngẫu nhiên
            int[] numbers = new int[10];
            for (int i = 0; i < 10; i++) {
                numbers[i] = random.nextInt(101);
            }
            System.out.println("mang vua nhap: ");
            for (int i = 0; i < 10; i++) {
                System.out.println(numbers[i]);
            }

            // Gửi mảng số nguyên tới server
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);
            objOutStream.writeObject(numbers);
            objOutStream.flush();
            byte[] sendData = byteOutStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            // Nhận tổng từ server
            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);
            clientSocket.receive(receivePacket);

            // Đọc tổng từ dữ liệu nhận được
            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objStream = new ObjectInputStream(byteStream);
            int sum = (int) objStream.readObject();
            objStream.close();

            System.out.println("Sum received from server: " + sum);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
