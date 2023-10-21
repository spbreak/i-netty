package cn.hlx;

import cn.hlx.reactor.DefaultReactor;
import cn.hlx.reactor.Reactor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Reactor reactor = new DefaultReactor();
        reactor.run();
    }
}
