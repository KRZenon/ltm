package slide92.b4;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;
        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

            // Gửi tên file cho server
            System.out.println("Nhap ten file:");
            String fileName = sc.nextLine(); // Đổi tên file tại đây
            outputStream.println(fileName);
            System.out.println("Flie name send to server: " + fileName);

            // Nhận nội dung của file từ server và in ra màn hình
            String line;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
