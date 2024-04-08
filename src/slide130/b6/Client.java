package slide130.b6;

import java.io.*;
import java.net.*;
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

            // Nhận mảng đã sắp xếp từ server
            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);
            clientSocket.receive(receivePacket);

            // Đọc mảng từ dữ liệu nhận được
            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream objStream = new ObjectInputStream(byteStream);
            int[] sortedNumbers = (int[]) objStream.readObject();
            objStream.close();

            // In mảng đã sắp xếp
            System.out.println("Sorted array received from server:");
            for (int num : sortedNumbers) {
                System.out.print(num + " ");
            }
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

