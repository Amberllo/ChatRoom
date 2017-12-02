package com.chatroom.client.view;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.GroupBean;
import com.chatroom.client.model.UserBean;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */
public class JMainView extends JFrame{
    ChatClient chatClient;
    private UserBean userBean;
    private JLabel tv_username;
    private JScrollPane friendPane;
    private JScrollPane groupPane;
    private JTabbedPanel mainJPanel;
    private List<UserBean> friends = new ArrayList<>();
    private List<GroupBean> groups = new ArrayList<>();
    private boolean friendHasInit = false;
    private boolean groupHasInit = false;
    public JMainView(ChatClient chatClient) {
        this.chatClient = chatClient;
        friendPane = new JScrollPane();
        groupPane = new JScrollPane();
        tv_username = new JLabel();

        LinkedHashMap<String,JScrollPane> map = new LinkedHashMap<>();
        map.put("好友",friendPane);
        map.put("群组",groupPane);
        mainJPanel = new JTabbedPanel(map);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = 300;
        int height = (int) (screenSize.getHeight()-300);
        int x = (int) (screenSize.getWidth()-width);

        this.setBounds(x, 100, width, height);


        JPanel container = (JPanel) getContentPane();
        container.setAutoscrolls(true);

        BoxLayout layout= new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(tv_username);
        container.add(mainJPanel);

        mainJPanel.setOnTabSelectListener(new JTabbedPanel.OnTabSelectListener() {
            @Override
            public void onSelect(int index) {
                switch (index){
                    case 0:
                        if(!friendHasInit){
                            chatClient.user.friends(userBean.getUserid());
                        }
                        break;
                    case 1:
                        if(!groupHasInit){
                            chatClient.group.userGroups(userBean.getUserid());

                        }
                        break;
                }
            }
        });

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


    public void setFriends(List<UserBean> friends) {
        this.friendHasInit = true;
        this.friends.clear();
        this.friends.addAll(friends);


        DefaultListModel listModel = new DefaultListModel();
        for(UserBean friend:friends){
            System.out.println("friend name = "+friend.getNickname());
            listModel.addElement(friend.getNickname() +" -- "+friend.getState().text);
        }
        JList<String> list = new JList<>(listModel);
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() >= 2){

                    int position = list.getSelectedIndex();
                    UserBean friend = friends.get(position);
                    System.out.println(" open chatroom "+friend.getNickname());
                    chatClient.jChatRoomView = new JChatRoomView(chatClient,userBean,friend);
                    chatClient.jChatRoomView.onShow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        friendPane.setViewportView(list);
    }

    public void setGroups(List<GroupBean> groups){
        this.groupHasInit = true;
        this.groups.clear();
        this.groups.addAll(groups);
        DefaultListModel listModel = new DefaultListModel();
        for(GroupBean group:groups){
//            System.out.println("friend name = "+group.getNickname());
            listModel.addElement(group.getGroupname());
        }
        JList<String> list = new JList<>(listModel);
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() >= 2){

                    int position = list.getSelectedIndex();
                    GroupBean groupBean = groups.get(position);
//                    chatClient.jChatRoomView = new JChatRoomView(chatClient,userBean,friend);
//                    chatClient.jChatRoomView.onShow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        groupPane.setViewportView(list);
    }

    public static void  main(String[] args){

        UserBean userBean = new UserBean();
        JMainView mainView = new JMainView(null);
        mainView.onShow();
    }

    public void initView() {
        if(!friendHasInit){
            chatClient.user.friends(userBean.getUserid());
        }
    }
}
