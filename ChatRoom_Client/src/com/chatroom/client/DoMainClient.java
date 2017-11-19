package com.chatroom.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class DoMainClient {
    public static void main(String[] args) throws IOException {
        System.out.println("client init");
        Socket socket = new Socket("127.0.0.1",7777);
        socket.getOutputStream().write(11);
    }
}
