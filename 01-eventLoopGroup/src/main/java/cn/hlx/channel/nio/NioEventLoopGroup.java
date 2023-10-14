package cn.hlx.channel.nio;

import cn.hlx.util.concurrent.DefaultEventExecutorChooserFactory;
import cn.hlx.util.concurrent.EventExecutor;
import cn.hlx.util.concurrent.EventExecutorChooserFactory;
import cn.hlx.util.concurrent.EventExecutorGroup;


public class NioEventLoopGroup implements EventExecutorGroup {
    private final EventExecutor[] children;

    private static final int DEFAULT_EVENT_LOOP_THREADS = 16;

    private final EventExecutorChooserFactory.EventExecutorChooser chooser;
    
    public NioEventLoopGroup() {
        this(DEFAULT_EVENT_LOOP_THREADS, DefaultEventExecutorChooserFactory.INSTANCE);
    }
    
    protected NioEventLoopGroup(int nThreads, EventExecutorChooserFactory chooserFactory) {
        children = new NioEventLoop[nThreads];

        for (int i = 0; i < nThreads; i ++) {
            children[i] = newChild();
        }
        chooser = chooserFactory.newChooser(children);
    }

    @Override
    public EventExecutor next() {
        return chooser.next();
    }

    public EventExecutor newChild(){
        return new NioEventLoop();
    }

}
