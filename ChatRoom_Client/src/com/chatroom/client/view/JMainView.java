package com.chatroom.client.view;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.UserBean;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */
public class JMainView extends JFrame{
    ChatClient chatClient;
    private UserBean userBean;
    private JLabel tv_username;
    private JScrollPane scrollPane;
    private List<UserBean> friends = new ArrayList<>();

    public JMainView(ChatClient chatClient) {
        this.chatClient = chatClient;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = 300;
        int height = (int) (screenSize.getHeight()-300);
        int x = (int) (screenSize.getWidth()-width);

        this.setBounds(x, 100, width, height);


        JPanel container = new JPanel();
        container.setAutoscrolls(true);

        BoxLayout layout= new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        tv_username = new JLabel();
        container.add(tv_username);


        scrollPane = new JScrollPane();
        container.add(scrollPane);


        this.setContentPane(container);
    }

    public void setUserBean(UserBean userBean){
        this.userBean = userBean;
        tv_username.setText(userBean.getNickname());
    }

    public void onShow(){
        setVisible(true);
    }


    public void onHide(){
        setVisible(false);
    }

    public static void  main(String[] args){
        new JMainView(null).onShow();
    }

    public void initView() {
        chatClient.user.friends(userBean.getUserid());
    }

    public void setFriends(List<UserBean> friends) {
        this.friends.clear();
        this.friends.addAll(friends);


        DefaultListModel listModel = new DefaultListModel();
        for(UserBean friend:friends){
            System.out.println("friend name = "+friend.getNickname());
            listModel.addElement(friend.getNickname() +" -- "+friend.getState().text);
        }
        JList<String> list = new JList<>(listModel);
        scrollPane.setViewportView(list);
    }
}
