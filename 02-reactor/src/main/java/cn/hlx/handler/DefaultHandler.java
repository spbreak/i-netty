package cn.hlx.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DefaultHandler implements Handler {
    private final SocketChannel clientSocketChannel;

    public DefaultHandler(SocketChannel clientSocketChannel) {
        this.clientSocketChannel = clientSocketChannel;
    }


    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            // 读取数据
            int read = clientSocketChannel.read(byteBuffer);
            if (read <= 0) {
                clientSocketChannel.close();
            } else {
                System.out.println("----" + new String(byteBuffer.array()) + "----");
                // 响应结果 200, 模拟请求响应
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Length: 11\r\n\r\n" +
                        "Yes, He is";
                ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());// 数据存放在byte数组
                while (buffer.hasRemaining()) {
                    // hasRemaining() 返回是否有剩余的可用长度
                    clientSocketChannel.write(buffer); // 非阻塞
                }
            }
        } catch (IOException e1) {
            try {
                clientSocketChannel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            e1.printStackTrace();
        }
    }
    
    
}
