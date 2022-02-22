import java.io.IOException;
import java.net.*;

public class Rfc865UpServer {
    public static void main (String[] args) throws IOException {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(17);
        }catch (SocketException e){
            System.out.println("Socket exception.");
            socket = null;
        }

        while(true){
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            String received = new String(request.getData(), 0, request.getLength());
            System.out.println("Server received: " + received);

            InetAddress address = request.getAddress();
            int port = request.getPort();

            //
            // 3. Send UDP reply to client
            //
            // Create data packet with verification code

            String responseMessage = "Received successfully from " + received;
            byte[] messageInBytes = responseMessage.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(messageInBytes, messageInBytes.length, address, port);
            socket.send(responsePacket);	// send to client
        }


    }

}
