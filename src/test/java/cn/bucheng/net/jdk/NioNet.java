package cn.bucheng.net.jdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * @author ：yinchong
 * @create ：2019/7/1 11:24
 * @description：
 * @modified By：
 * @version:
 */
public class NioNet {
    private static volatile Selector selector = null;
    private static volatile  Selector selector2 = null;
    public static void main(String[] args)throws Exception {
        Charset charset = Charset.forName("UTF-8");
        selector = Selector.open();
        selector2 = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8282);
        server.socket().bind(address);
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        //这个线程专门负责处理新连接
        Thread thread1  =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (selector.select(1) > 0) {
                        for (SelectionKey sk : selector.selectedKeys()) {
                            selector.selectedKeys().remove(sk);
                            if (sk.isAcceptable()) {
                                System.out.println("------------------>get new client"+System.nanoTime());
                                SocketChannel sc = server.accept();
                                sc.configureBlocking(false);
                                //将客户端绑定到selector上面并注册事件
                                sc.register(selector2, SelectionKey.OP_READ);
                                sc.write(charset.encode("hahaha"));
                                //再次向selector上面注册事件
                                sk.interestOps(SelectionKey.OP_ACCEPT);
                            }
                        }
                    }
                }catch (Exception e){
                    System.err.println("error:"+e);
                }
            }
        });

        //这个线程专门负责处理读事件
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<Integer.MAX_VALUE;i++) {
                        if(selector2.select(1)>0) {
                            try {
                                Set<SelectionKey> selectionKeys = selector2.selectedKeys();
                                for(SelectionKey sk:selectionKeys){
                                    selector2.selectedKeys().remove(sk);
                                    if(sk.isReadable()) {
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
                                        }catch (Exception e){
                                            //取消客户端上面事件
                                            sk.cancel();
                                            System.err.println("error:"+e);
                                            break;

                                        }
                                    }
                                }
                            }catch (Exception e){
                                System.err.println("ERROR:"+e);
                            }
                        }
                        Thread.sleep(10);
                    }
                }catch (Exception e){
                    System.err.println("error:"+e);
                }
            }
        });
        thread1.start();
        thread2.start();
        System.out.println("------------------>server start 8282");
    }

}

