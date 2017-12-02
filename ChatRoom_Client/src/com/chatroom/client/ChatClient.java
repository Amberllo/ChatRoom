package com.chatroom.client;

import com.chatroom.client.contoller.AbstractController;
import com.chatroom.client.contoller.GroupController;
import com.chatroom.client.contoller.MessageController;
import com.chatroom.client.contoller.UserController;
import com.chatroom.client.protocol.Protocol;
import com.chatroom.client.protocol.ProtocolResult;
import com.chatroom.client.view.JChatRoomView;
import com.chatroom.client.view.JLoginView;
import com.chatroom.client.view.JMainView;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatClient {
    private static final String DIVIDED = "<end>";
    private Socket socket;
    public UserController user;
    public GroupController group;
    public MessageController message;

    public JLoginView jLoginView;
    public JMainView jMainView;
    public JChatRoomView jChatRoomView;

    public Map<String,AbstractController> controllers = new HashMap<>();

    public ChatClient(){
        user = new UserController(this);
        message = new MessageController(this);
        group = new GroupController(this);
        controllers.put("user",user);
        controllers.put("message",message);
        controllers.put("group",group);

        jLoginView = new JLoginView(this);
        jMainView = new JMainView(this);
    }

    public void connect() throws IOException {
        socket  = new Socket("127.0.0.1",7777);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket != null && !socket.isClosed() && socket.isConnected()) {
                        InputStream is = socket.getInputStream();
                        if (is.available() > 0) {

                            String streamString = readStream(socket.getInputStream());
                            String[] jsonArray = streamString.split(DIVIDED);
                            for(String json:jsonArray){
                                if(!"".equals(json)){
                                    handleResponse(json);
                                    System.out.println("response data :"+json);
                                }
                            }

                        }
                        sleep(1000);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }).start();

    }

    private void write(String json) {

        try {
            json = json + DIVIDED;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write(json);
            writer.flush();
            sleep(200);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @功能 读取流
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    private static String readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        String json = new String(b,"UTF-8");
        return json;
    }



    public AbstractController getController(String api){
        AbstractController controller = controllers.get(api.toLowerCase());
        return controller;
    }

    public void handleResponse(String json){
        try{

            Gson gson = new Gson();
            ProtocolResult result = gson.fromJson(json,ProtocolResult.class);

            if(result.resource.startsWith("Broadcast_")){
                String resource = result.resource.substring("Broadcast_".length());
                AbstractController controller = getController(resource);
                if(controller!=null){
                    controller.onBroadcast(result);
                }
            }else{
                AbstractController controller = getController(result.resource);
                if(controller!=null){
                    controller.handleResponse(result);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(String resource, String action, Map<String, String> params) {
        write(Protocol.doPost(resource,action,params));
    }



//    public static final void main(String[] args){
//        String json = "{\"resultCode\":1,\"resource\":\"User\",\"actin\":\"offline\",\"resultParams\":{\"userid\":\"abb6ba94-2b65-4f38-8c96-2c82a6d0514e\"}}";
//        String json2 = "{\"resultCode\":1,\"resource\":\"User\",\"actin\":\"offline\",\"resultParams\":{\"userid\":\"abb6ba94-2b65-4f38-8c96-2c82a6d0514e\"}}";
//        String str  = json + DIVIDED + json2 + DIVIDED;
//
//        String[] array = str.split(DIVIDED);
//        for(String s:array){
//            System.out.println(s);
//        }
//    }

}
