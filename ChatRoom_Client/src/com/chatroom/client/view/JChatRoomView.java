package com.chatroom.client.view;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.MessageBean;
import com.chatroom.client.model.UserBean;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class JChatRoomView extends JFrame {
    private ChatClient chatClient;
    private UserBean userBean;
    private UserBean friend;

    private JTextField edt_content;
    JChatRoomView(ChatClient chatClient, UserBean userBean, UserBean friend){
        this.chatClient = chatClient;
        this.userBean = userBean;
        this.friend = friend;
        init();
    }

    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width/3;
        int height = screenSize.height/2;
        this.setSize(width,height);
        //让窗口居中显示
        this.setLocation(screenSize.width/2-width/2,screenSize.height/2-height/2);
        this.setTitle(friend.getNickname());

        JPanel container = new JPanel();
        this.setContentPane(container);
        this.getContentPane().setLayout(new BorderLayout(0,0));



        JPanel panel_message = new JPanel();
        panel_message.setSize(width,400);

        edt_content = new JTextField();
        Dimension  dimension = new Dimension();
        dimension.width = width;
        dimension.height = 100;
        edt_content.setSize(dimension);


        container.add(panel_message);
        container.add(edt_content);

    }

    public void onShow() {
        setVisible(true);
        getMessage();
    }

    public void getMessage(){
        chatClient.message.getMessage(userBean,friend);
    }

    public void setMessages(List<MessageBean> messages) {
       for (MessageBean msg:messages){
           System.out.println(msg.getContent());
       }
    }
}
