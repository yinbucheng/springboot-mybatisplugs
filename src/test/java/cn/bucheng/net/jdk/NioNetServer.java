package cn.bucheng.net.jdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author ：yinchong
 * @create ：2019/7/1 11:24
 * @description：
 * @modified By：
 * @version:
 */
public class NioNetServer {
    private static volatile Selector bossSelector = null;
    private static volatile Selector workSelector = null;

    public static void main(String[] args) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        bossSelector = Selector.open();
        workSelector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8282);
        server.bind(address);
        server.configureBlocking(false);
        server.register(bossSelector, SelectionKey.OP_ACCEPT);
        //这个线程专门负责处理新连接
        Thread bossThread = new Thread(() ->
        {
            try {
                while (bossSelector.select(1) > 0) {
                    Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey sk = iterator.next();
                        if (sk.isAcceptable()) {
                            System.out.println("------------------>get new client" + System.nanoTime());
                            SocketChannel sc = server.accept();
                            sc.configureBlocking(false);
                            //将客户端绑定到selector上面并注册事件
                            sc.register(workSelector, SelectionKey.OP_READ,sc);
                            sc.write(charset.encode("hahaha"));
                            //再次向selector上面注册事件
                            sk.interestOps(SelectionKey.OP_ACCEPT);
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                System.err.println("error:" + e);
            }
        });

        //这个线程专门负责处理读事件
        Thread workThread = new Thread(() -> {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (workSelector.select(1) > 0) {
                        try {
                            Iterator<SelectionKey> iterator = workSelector.selectedKeys().iterator();
                            while (iterator.hasNext()) {
                                SelectionKey sk = iterator.next();
                                if (sk.isReadable()) {
                                    SocketChannel channel = (SocketChannel) sk.channel();
                                    String content = "";
                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                    try {
                                        while (channel.read(buffer) > 0) {
                                            buffer.flip();
                                            content += charset.decode(buffer);
                                        }
                                        System.out.println("------------------->recive msg:" + content);
                                        sk.interestOps(SelectionKey.OP_READ);
                                        channel.write(charset.encode("hahaha"));
                                    } catch (Exception e) {
                                        //取消客户端上面事件
                                        sk.cancel();
                                        System.err.println("error:" + e);
                                        break;

                                    }
                                }
                                iterator.remove();
                            }
                        } catch (Exception e) {
                            System.err.println("ERROR:" + e);
                        }
                    }
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                System.err.println("error:" + e);
            }
        });
        bossThread.start();
        workThread.start();
        System.out.println("------------------>server start 8282");
    }

}

