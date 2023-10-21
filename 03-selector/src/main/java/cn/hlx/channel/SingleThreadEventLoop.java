package cn.hlx.channel;

import cn.hlx.util.concurrent.AbstractEventExecutor;

/**
 * Abstract base class for {@link EventLoop}s that execute all its submitted tasks in a single thread.
 *
 */
public abstract class SingleThreadEventLoop extends AbstractEventExecutor {

    @Override
    public EventLoop next() {
        return (EventLoop) super.next();
    }
}
