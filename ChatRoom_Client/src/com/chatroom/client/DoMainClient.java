package com.chatroom.client;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class DoMainClient {
    public static void main(String[] args) throws IOException {
        System.out.println("client init");
        ChatClient client = new ChatClient();
        client.connect();
        client.showLoginView();
    }
}
