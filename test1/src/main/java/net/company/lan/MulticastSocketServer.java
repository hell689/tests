package net.company.lan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSocketServer implements AutoCloseable{
    private final int MESSAGE_SIZE = 2048;
    private int port;
    private String address;
    private int ttl;
    private MulticastSocket multicastSocket;
    private InetAddress inetAddress;

    public MulticastSocketServer(int port, String address, int ttl) throws IOException {
        this.port = port;
        this.address = address;
        this.ttl = ttl;
        inetAddress = InetAddress.getByName(address);
        multicastSocket = new MulticastSocket(port);
        multicastSocket.setTimeToLive(ttl);
        multicastSocket.joinGroup(inetAddress);
        //multicastSocket.setSoTimeout(10000);
    }

    public void send (byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
        multicastSocket.send(datagramPacket);
    }

    public String recieve() throws IOException {
        byte[] bytes = new byte[MESSAGE_SIZE];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        multicastSocket.receive(datagramPacket);
        return new String(datagramPacket.getData());
    }

    public void leaveGroup() throws IOException {
        if(multicastSocket != null) {
            multicastSocket.leaveGroup(inetAddress);
        }
    }

    @Override
    public void close() throws Exception {
        leaveGroup();
    }
}
