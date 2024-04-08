package b1;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class App {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("vnexpress.net");
            System.out.println("Dia chi IP cua may chu vnexpress.net la: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("Khong the tim thay dia chi IP cho may chu vnexpress.net");
            e.printStackTrace();
        }
        try {
            InetAddress address = InetAddress.getByName("tuoitre.vn");
            System.out.println("Dia chi IP cua may chu tuoitre.vn la: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("Khong the tim thay dia chi IP cho may chu tuoitre.vn");
            e.printStackTrace();
        }
    }
}
