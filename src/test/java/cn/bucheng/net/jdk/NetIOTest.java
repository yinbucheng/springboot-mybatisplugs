package cn.bucheng.net.jdk;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：yinchong
 * @create ：2019/6/21 13:52
 * @description：网络请求
 * @modified By：
 * @version:
 */
public class NetIOTest {

    @Test
    public void testClientTimeWait() throws IOException {
        try (Socket client = new Socket()) {
            client.bind(new InetSocketAddress(9988));
            client.setSoLinger(true, 0);
            client.connect(new InetSocketAddress("127.0.0.1", 9090));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer.write("this is message from client");
            writer.newLine();
            writer.flush();
            client.shutdownOutput();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Test
    public void testServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9090));
        for (; ; ) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getPort());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.write("this is message for server");
            writer.newLine();
            writer.flush();
            socket.shutdownOutput();
            System.out.println("============socket close===========");
        }
    }


    @Test
    public void testClient() throws Exception {
        try (Socket client = new Socket()) {
            client.bind(new InetSocketAddress(9008));
            client.connect(new InetSocketAddress("127.0.0.1", 9090));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write("this is message from client");
            writer.newLine();
            writer.flush();
            client.shutdownOutput();
        }
    }

    @Test
    public void testFINWAIT2Client() throws Exception {
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress(9099));
        socket.connect(new InetSocketAddress("127.0.1", 9090));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        for (int i = 0; i < 20; i++) {
            writer.println("this is message from client, this number is " + i);
            writer.flush();
            Thread.sleep(2000);
        }
        socket.shutdownOutput();
        String line;
        while((line=reader.readLine())!=null){
            System.out.println(line);
        }
        socket.close();
    }

    @Test
    public void testCLOSEWAITServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9090));
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("accept new client");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while((line= reader.readLine())!=null){
                System.out.println(line);
            }
            for (int i = 0; i < 20; i++) {
                writer.println("this message from server,this number is "+i);
                writer.flush();
                Thread.sleep(2000);
            }
            socket.shutdownOutput();
            socket.close();
        }
    }

}
