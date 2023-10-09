package cn.hlx.util.concurrent;

import java.util.concurrent.Executor;

public interface EventExecutorGroup {

    
    /**
     * Returns one of the {@link EventExecutor}s managed by this {@link EventExecutorGroup}.
     */
    EventExecutor next();
}
