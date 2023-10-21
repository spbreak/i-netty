package cn.hlx.eventLoopGroup;

import cn.hlx.channel.nio.NioEventLoopGroup;
import org.junit.Test;

public class selector {
    
    @Test
    public void selector() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
        System.out.println(bossGroup.next());
    }
}
