package net.company.lan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSocketServer implements AutoCloseable{
    private final int MESSAGE_SIZE = 2048;
    private int port;
    private String address;
    private int ttl;
    private MulticastSocket multicastSocket;
    private InetAddress inetAddress;

    /**
     * Создание Multicast Socket соединения
     * @param port Порт соединения
     * @param address Адрес группы
     * @param ttl "Время жизни" пакета
     * @throws IOException
     */
    public MulticastSocketServer(int port, String address, int ttl) throws IOException {
        this.port = port;
        this.address = address;
        this.ttl = ttl;
        inetAddress = InetAddress.getByName(address);
        multicastSocket = new MulticastSocket(port);
        multicastSocket.setTimeToLive(ttl);
        multicastSocket.joinGroup(inetAddress);
    }

    /**
     * Создание Multicast Socket соединения
     * @param port Порт соединения
     * @param address Адрес группы
     */
    public MulticastSocketServer(int port, String address) {
        this.port = port;
        this.address = address;
    }

    /**
     * Отправка данных
     * @param bytes
     * @throws IOException
     */
    public void send (byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
        multicastSocket.send(datagramPacket);
    }

    /**
     * Получение данных
     * @return Строка с сообщением
     * @throws IOException
     */
    public String recieve() throws IOException {
        byte[] bytes = new byte[MESSAGE_SIZE];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        multicastSocket.receive(datagramPacket);
        return new String(datagramPacket.getData());
    }

    /**
     *
     * @throws IOException
     */
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
