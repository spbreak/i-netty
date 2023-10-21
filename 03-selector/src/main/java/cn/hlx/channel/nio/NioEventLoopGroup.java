package cn.hlx.channel.nio;

import cn.hlx.util.concurrent.DefaultEventExecutorChooserFactory;
import cn.hlx.util.concurrent.EventExecutor;
import cn.hlx.util.concurrent.EventExecutorChooserFactory;
import cn.hlx.util.concurrent.EventExecutorGroup;

import java.nio.channels.spi.SelectorProvider;


public class NioEventLoopGroup implements EventExecutorGroup {
    private final EventExecutor[] children;

    private static final int DEFAULT_EVENT_LOOP_THREADS = 16;

    private final EventExecutorChooserFactory.EventExecutorChooser chooser;
    
    public NioEventLoopGroup() {
        this(0);
    }
    
    public NioEventLoopGroup(int nThreads) {
        this(nThreads, SelectorProvider.provider());
    }
    
    public NioEventLoopGroup(int nThreads, final SelectorProvider selectorProvider) {
        this(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, selectorProvider, DefaultEventExecutorChooserFactory.INSTANCE);
    }

    protected NioEventLoopGroup(int nThreads, final SelectorProvider selectorProvider, EventExecutorChooserFactory chooserFactory) {
        children = new NioEventLoop[nThreads];

        for (int i = 0; i < nThreads; i ++) {
            children[i] = newChild(selectorProvider);
        }
        chooser = chooserFactory.newChooser(children);
    }

    @Override
    public EventExecutor next() {
        return chooser.next();
    }

    public EventExecutor newChild(SelectorProvider selectorProvider){
        return new NioEventLoop(selectorProvider);
    }

}
