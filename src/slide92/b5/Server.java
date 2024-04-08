package slide92.b5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;
        final String FILE_PATH = "D:\\ltm\\slide68\\src\\slide92\\b5\\users.txt"; // Đường dẫn đến file chứa thông tin người dùng và mật khẩu

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is waiting...");

            Socket socket = serverSocket.accept();
            System.out.println("Client accessed.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Nhận tên người dùng và mật khẩu từ client
            String userInput = reader.readLine();
            String[] userPass = userInput.split(" ");
            String username = userPass[0];
            String password = userPass[1];
            password = password.toString();
            System.out.println("User name and pass from client: " + username + ", " + password);

            // Kiểm tra tên người dùng và mật khẩu trong file
            boolean loginSuccess = checkLogin(FILE_PATH, username, password);

            // Gửi kết quả đăng nhập về client
            if (loginSuccess) {
                writer.println("Login successful");
            } else {
                writer.println("Login fail");
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkLogin(String filePath, String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

