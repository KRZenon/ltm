package slide168.b2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class MulticastSender {
    public static void main(String[] args) {
        final String MULTICAST_GROUP = "230.0.0.0";
        final int PORT = 12345;
        final int BUFFER_SIZE = 1024;

        try (MulticastSocket multicastSocket = new MulticastSocket()) {
            InetAddress groupAddress = InetAddress.getByName(MULTICAST_GROUP);
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

            // Chuẩn bị dữ liệu để gửi
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);
            objOutStream.writeObject(numbers);
            objOutStream.flush();
            byte[] sendData = byteOutStream.toByteArray();

            // Gửi dữ liệu tới nhóm multicast
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, groupAddress, PORT);
            multicastSocket.send(sendPacket);

            System.out.println("Data sent to multicast group.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

