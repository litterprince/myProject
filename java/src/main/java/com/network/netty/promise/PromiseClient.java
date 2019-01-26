package main.java.com.network.netty.promise;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PromiseClient {
    private ClientHandler clientHandler = new ClientHandler();
    private final int CONNECT_TIMEOUT = 6000;

    public PromiseClient(String host, int port) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT);//连接超时时间
            b.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch)  {
                    ch.pipeline().addLast("handler", clientHandler);
                }
            });
            Channel channel = b.connect(host, port)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) {
                            if (!future.isSuccess()) {
                                System.err.println("Connect to host error: " + future.cause());
                            }
                        }
                    })
                    .sync().channel();
            while (!channel.isActive()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            workerGroup.shutdownGracefully();
        }
    }

    public String getResponse() throws InterruptedException {
        ChannelPromise promise = clientHandler.sendMessage("hello".getBytes());
        promise.await();
        return clientHandler.getMessage();
    }

    public static void main(String[] args) throws InterruptedException {
        PromiseClient client = new PromiseClient("127.0.0.1", 9999);
        System.out.println("" + client.getResponse());
    }

}