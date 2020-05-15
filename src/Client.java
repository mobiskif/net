import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public Client() {
        super();
    }

    public static Thread transferThread(BufferedReader in, PrintWriter out, String msg) {
        return new Thread(() -> {
            String readLine = "";
            try {
                while ((readLine = in.readLine()) != null) out.println(readLine);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(msg + " finished");
        });
    }

    public static void transferHead(PrintWriter out, long contentLength) {
        System.out.println("" + contentLength);
        String header = "" +
                "HTTP/1.1 200 OK\n" +
                //"Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                "Server: Apache/2.2.14 (Win32)\n" +
                //"Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\n" +
                "Content-Length: " + contentLength + "\n" +
                "Content-Type: text/html\n" +
                "Connection: keep-alive\n";
        out.println(header);
    }
}
