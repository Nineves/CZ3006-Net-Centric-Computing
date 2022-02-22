import java.io.IOException;
import java.net.*;

public class Rfc865UpClient {
    public static void main (String[] args) throws IOException {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
        }catch (SocketException e){
            System.out.println("Socket exception.");
            socket = null;
        }
        InetAddress ipAddress;
        try{
            ipAddress = InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            System.out.println("Unknown host.");
            ipAddress = null;
        }
        while(true){
            String msgToSend = "Wang Yiying, SSP1, 172.21.144.154";
            byte[] msgBytes = msgToSend.getBytes();
            DatagramPacket request = new DatagramPacket(msgBytes, msgBytes.length, ipAddress, 17);
            socket.send(request);
            System.out.println("Message sent: " + msgToSend);

            byte[] buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
        }
    }
}
