package cn.hlx.acceptor;

import cn.hlx.handler.DefaultHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server implements Acceptor{
    
    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;
    
    public Server(Selector selector, int port) throws IOException {
        this.selector = selector;
        // 服务端创建 listen-socket 管道
        this.serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口
        this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置为非阻塞模式
        this.serverSocketChannel.configureBlocking(false);
        // ACCEPT 事件的附加器是 Acceptor
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, this);
    }

    @Override
    public void run() {
        try {
            // 处理ACCEPT事件
            // 为连接的客户端创建 client-socket 管道
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            // 设置为非阻塞
            clientSocketChannel.configureBlocking(false);
            // READ 事件的附加器是 Handler
            clientSocketChannel.register(selector, SelectionKey.OP_READ,
                    new DefaultHandler(clientSocketChannel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
