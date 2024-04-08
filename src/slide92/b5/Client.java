package slide92.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            // Nhập tên người dùng và mật khẩu từ bàn phím
            System.out.print("Name: ");
            String username = userInputReader.readLine();
            System.out.print("Pass: ");
            String password = userInputReader.readLine();

            // Gửi tên người dùng và mật khẩu tới server
            String userData = username + " " + password;
            writer.println(username + " " + password);
            System.out.println("Data sent to server: " + userData);

            // Nhận kết quả đăng nhập từ server và in ra màn hình
            String response = reader.readLine();
            System.out.println("Login result from server: " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

