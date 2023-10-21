package cn.hlx.reactor;

import cn.hlx.acceptor.Acceptor;
import cn.hlx.acceptor.Server;
import cn.hlx.handler.Handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class DefaultReactor implements Reactor {
    
    private final static int PORT = 8080;
    
    private final Selector selector;
    private final Server server;

    public DefaultReactor() throws IOException {
        this(PORT);
    }
    
    public DefaultReactor(int port) throws IOException {
        selector = Selector.open();
        server = new Server(selector, port);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // 获取发生的事件
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterable = selectionKeys.iterator();
                while (iterable.hasNext()) {
                    // 对事件进行分发
                    dispatch(iterable.next());
                    iterable.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            LockSupport.parkNanos(1000 * 1000 * 1000);
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        // 获取事件的附加器
        // ACCEPT 事件的附加器是 Acceptor, 故由 Acceptor 来处理 ACCEPT 事件
        // READ 事件的附加器是 Handler, 故由 Handler 来处理 READ 事件
        Object attachment = selectionKey.attachment();
        if (attachment instanceof Acceptor) {
            ((Acceptor) attachment).run();
            return;
        }
        if (attachment instanceof Handler) {
            ((Handler) attachment).run();
            return;
        }
    }
}
