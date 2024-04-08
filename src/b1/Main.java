package b1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.*;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        String host = "dantri.com.vn";
        int port = 80; // Port mặc định cho HTTP là 80

        try {
            // Lấy địa chỉ IP của máy chủ
            InetAddress address = InetAddress.getByName(host);
            System.out.println("Dia chi IP cua may chu dantri.com.vn la: " + address.getHostAddress());

            // Kết nối tới máy chủ để lấy port của máy cục bộ
            Socket socket = new Socket(host, port);
            int localPort = socket.getLocalPort();
            System.out.println("Port cua may ket noi cuc bo toi: " + localPort);

            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            System.err.println("Khong the ket noi toi may chu " + host);
            e.printStackTrace();
        }
        }
    //     public static void main(String[] args) {
    //         String host = "dantri.com.vn";
    //         try {
    //         InetAddress theAddress = InetAddress.getByName(host);
    //         for (int i = 1; i < 65536; i++) {
    //         Socket connection = null;
    //         try {
    //             connection = new Socket(host, i);
    //             int localPort = connection.getLocalPort();
    //             System.out.println("There is a server on port" + localPort + " of " + host);
    //             connection.close();
    //             }
    //             catch (IOException ex) {
    //             // must not be a server on this port
    //             }
    //             finally {
    //             try {
    //             if (connection != null) connection.close( ); 
    //             }
    //             catch (IOException ex) {}
    //             }
    //         } // end for
    //     } // end try
    //     catch (UnknownHostException ex) {
    //     System.err.println(ex);
    //     }
    // } // end main  
} // end PortScanner


