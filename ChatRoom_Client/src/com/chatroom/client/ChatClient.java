package com.chatroom.client;

import com.chatroom.client.protocol.Protocol;
import com.chatroom.client.view.JLoginView;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatClient {
    Socket socket;
    public  void showLoginView(){
        new JLoginView(this);
    }

    public void connect() throws IOException {
        socket  = new Socket("127.0.0.1",7777);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket != null && !socket.isClosed() && !socket.isConnected()) {
                        InputStream is = socket.getInputStream();
                        if (is.available() > 0) {

                            String json = readStream(socket.getInputStream());
                            System.out.println("response data :"+json);
                        }
                        System.out.println("Client is Running");
                        sleep(1000);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    public void write(String json) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @功能 读取流
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static String readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        String json = new String(b,"UTF-8");
        return json;
    }

}
