package cn.hlx.util.concurrent;

import cn.hlx.channel.EventLoop;

/**
 * Abstract base class for {@link EventExecutor} implementations.
 */
public abstract class AbstractEventExecutor implements EventExecutor {

    @Override
    public EventExecutor next() {
        return this;
    }
}
