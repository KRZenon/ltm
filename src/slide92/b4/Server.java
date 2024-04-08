package slide92.b4;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;
        final String FILE_DIRECTORY = "D:\\ltm\\slide68\\src\\slide92\\b4\\";

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is waiting...");

            Socket socket = serverSocket.accept();
            System.out.println("Client accessed.");

            BufferedReader reader;
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

            // Đọc tên file từ client
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fileName = inputStream.readLine();
            System.out.println("File name from client: " + fileName);

            // Đọc nội dung của file và gửi lại cho client
            try {
                reader = new BufferedReader(new FileReader(FILE_DIRECTORY + fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    outputStream.println(line);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                outputStream.println("File none exist");
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
