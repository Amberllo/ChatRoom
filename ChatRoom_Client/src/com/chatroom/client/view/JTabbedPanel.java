package com.chatroom.client.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JTabbedPanel extends JPanel {
  
    private JTabbedPane jTabbedpane = new JTabbedPane();// 存放选项卡的组件

    public JTabbedPanel(LinkedHashMap<String, JScrollPane> panels) {
        layoutComponents(panels);
    }  
  
    private void layoutComponents(LinkedHashMap<String, JScrollPane> panels) {

        for(Map.Entry<String,JScrollPane> entry:panels.entrySet()){
            String text = entry.getKey();
            JScrollPane panel = entry.getValue();
            jTabbedpane.addTab(text, null, panel, text);// 加入第一个页面
        }

        jTabbedpane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(onTabSelectListener!=null){
                    onTabSelectListener.onSelect(jTabbedpane.getSelectedIndex());
                }
            }
        });
        setLayout(new GridLayout(1, 1));
        add(jTabbedpane);  
  
    }
    private OnTabSelectListener onTabSelectListener;

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.onTabSelectListener = onTabSelectListener;
    }

    public interface OnTabSelectListener{
         void onSelect(int index);
    }

    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
  
            public void run() {  
                //JFrame.setDefaultLookAndFeelDecorated(true);// 将组建外观设置为Java外观

                LinkedHashMap<String,JScrollPane> map = new LinkedHashMap<>();
                map.put("好友",new JScrollPane());
                map.put("群组",new JScrollPane());

                JFrame frame = new JFrame();  
                frame.setLayout(null);  
                frame.setContentPane(new JTabbedPanel(map));
                frame.setSize(400, 400);  
                frame.setVisible(true);  
                // new TabComponentsDemo().runTest();  
            }  
        });  
    }  
  
}  