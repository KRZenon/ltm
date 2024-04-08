package slide109.b2;

import java.io.IOException;
import java.net.Socket;

public class HelloWorldThreads {
    public static void main(String[] args) {
        try {
            // Tạo và khởi chạy 3 luồng
            Thread thread1 = new Thread(new HelloThread());
            Thread thread2 = new Thread(new WorldThread());

            thread1.start();
            thread2.start();

            // Luồng chính kết nối tới máy localhost, cổng 12345 để in ra "Hello World"
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            // Đóng kết nối sau khi in ra "Hello World"
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Thread in ra chữ "Hello"
class HelloThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello");
    }
}

// Thread in ra chữ "World"
class WorldThread implements Runnable {
    @Override
    public void run() {
        System.out.println("World");
    }
}
