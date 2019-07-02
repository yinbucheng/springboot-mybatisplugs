package cn.bucheng.net.jdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author ：yinchong
 * @create ：2019/7/2 9:20
 * @description：
 * @modified By：
 * @version:
 */
public class NioNetClient {


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            Selector workSelector = Selector.open();
            SocketChannel client = SocketChannel.open();
            Charset charset = Charset.forName("UTF-8");
            client.configureBlocking(false);
            client.register(workSelector, SelectionKey.OP_CONNECT, client);
            client.connect(new InetSocketAddress("192.168.11.14", 8282));
            Thread workThread = new Thread(() -> {
                try {
                    while (workSelector.select() > 0) {
                        Iterator<SelectionKey> iterator = workSelector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey sk = iterator.next();
                            switch (sk.interestOps()) {
                                case SelectionKey.OP_CONNECT:
                                    SocketChannel channel = (SocketChannel) sk.attachment();
                                    channel.finishConnect();
                                    channel.write(charset.encode("active "));
                                    sk.interestOps(SelectionKey.OP_READ);
                                    break;
                                case SelectionKey.OP_READ:
                                    SocketChannel channel2 = (SocketChannel) sk.attachment();
                                    String content = "";
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    try {
                                        while (channel2.read(buffer) > 0) {
                                            buffer.flip();
                                            content += charset.decode(buffer);
                                        }
                                        System.out.println("------------------->recive msg:" + content);
                                        sk.interestOps(SelectionKey.OP_READ);
                                        channel2.write(charset.encode("client message"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        break;
                                    }
                                    break;
                            }
                            iterator.remove();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            workThread.start();
        }
    }
}
