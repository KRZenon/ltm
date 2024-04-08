package slide168.b2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    public static void main(String[] args) {
        final String MULTICAST_GROUP = "230.0.0.0";
        final int PORT = 12345;
        final int BUFFER_SIZE = 1024;

        try (MulticastSocket multicastSocket = new MulticastSocket(PORT)) {
            InetAddress groupAddress = InetAddress.getByName(MULTICAST_GROUP);
            multicastSocket.joinGroup(groupAddress);

            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);

            // Nhận dữ liệu từ nhóm multicast
            multicastSocket.receive(receivePacket);

            // Đọc mảng số nguyên từ dữ liệu nhận được
            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objStream = new ObjectInputStream(byteStream);
            int[] numbers = (int[]) objStream.readObject();
            objStream.close();

            // Tính tổng các số nguyên
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }

            // In tổng
            System.out.println("Sum of received numbers: " + sum);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
