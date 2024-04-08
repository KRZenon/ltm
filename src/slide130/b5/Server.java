package slide130.b5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                // Nhận mảng số nguyên từ client
                ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objStream = new ObjectInputStream(byteStream);
                int[] numbers = (int[]) objStream.readObject();
                objStream.close();

                // Tính tổng các phần tử trong mảng
                int sum = 0;
                for (int num : numbers) {
                    sum += num;
                }

                // Chuẩn bị dữ liệu để gửi về client
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ObjectOutputStream objOutStream = new ObjectOutputStream(byteOutStream);
                objOutStream.writeObject(sum);
                objOutStream.flush();

                // Gửi tổng về client
                byte[] sendData = byteOutStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Sum sent to client: " + sum);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
