package cn.bucheng.net.netty;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ：yinchong
 * @create ：2019/7/2 8:55
 * @description：
 * @modified By：
 * @version:
 */
public class NetClient {
    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new IdleStateHandler(10, -1, -1, TimeUnit.SECONDS));
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addFirst(new StringEncoder());
                    socketChannel.pipeline().addFirst(new LengthFieldPrepender(4));
                    socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                            if ("PING".equals(msg))
                                return;
                            BaseMessage baseMessage = JSON.parseObject(msg, BaseMessage.class);
                            System.out.println(baseMessage.getTital());
                            System.out.println(baseMessage.getBody());
                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            BaseMessage baseMessage = new BaseMessage();
                            baseMessage.setTital("haha");
                            baseMessage.setBody(new byte[]{1, 3, 7, 9, 0, 3});
                            ctx.pipeline().writeAndFlush(JSON.toJSONString(baseMessage));
                        }

                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            if (event.state() == IdleState.READER_IDLE) {
                                ctx.pipeline().writeAndFlush("PING");
                            }
                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.out.println(cause);
                        }
                    });
                }
            });

            ChannelFuture sync = bootstrap.connect("127.0.0.1", 9097);
            System.out.println("client start");
            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println("=================connection success========");
                    Channel channel = channelFuture.channel();
                    BaseMessage baseMessage = new BaseMessage();
                    baseMessage.setTital("haha");
                    baseMessage.setBody(new byte[]{1, 3, 7, 9, 0, 3});
                    channel.writeAndFlush(JSON.toJSONString(baseMessage));
                }
            });
           countDownLatch.await(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
              workGroup.shutdownGracefully();
        }
    }
}
