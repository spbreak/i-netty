package cn.hlx.util.concurrent;

public interface EventExecutorGroup {

    
    /**
     * Returns one of the {@link EventExecutor}s managed by this {@link EventExecutorGroup}.
     */
    EventExecutor next();
}
