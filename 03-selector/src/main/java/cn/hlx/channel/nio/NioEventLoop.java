package cn.hlx.channel.nio;

import cn.hlx.channel.ChannelException;
import cn.hlx.channel.SingleThreadEventLoop;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

public class NioEventLoop extends SingleThreadEventLoop {

    private Selector selector;
    private final SelectorProvider provider;
    
    public NioEventLoop(SelectorProvider selectorProvider) {
        this.provider = selectorProvider;
        this.selector = openSelector();
    }

    private Selector openSelector() {
        try {
            return provider.openSelector();
        } catch (IOException e) {
            throw new ChannelException("failed to open a new selector", e);
        }
    }
}
