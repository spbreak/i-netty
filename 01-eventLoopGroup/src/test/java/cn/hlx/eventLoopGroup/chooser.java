package cn.hlx.eventLoopGroup;

import cn.hlx.channel.nio.NioEventLoopGroup;
import org.junit.Test;

public class chooser {
    
    @Test
    public void chooser() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
    }
}
