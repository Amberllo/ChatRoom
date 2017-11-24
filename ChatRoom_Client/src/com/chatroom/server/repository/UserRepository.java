package com.chatroom.server.repository;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.UserBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserRepository extends AbstractRepository{
    private static final String TABLE_USER = "t_user";
    private static final String TABLE_USER_STATE = "t_user_state";



    public UserRepository(DBHelper dbHelper){
        super(dbHelper);
    }

    public List<UserBean> getFriends(String uid){
        List<UserBean> friends = new ArrayList<>();
        String sql = "SELECT "+TABLE_USER+".*, "+TABLE_USER_STATE+".state FROM "+TABLE_USER+"\n" +
                "LEFT JOIN "+TABLE_USER_STATE+" ON "+TABLE_USER+".userid = "+TABLE_USER_STATE+".userid \n" +
                "WHERE t_user.userid NOT IN ( '"+uid+"' ) ORDER BY username";
        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String userid = set.getString("userid");
                    String username = set.getString("username");
                    String password = set.getString("password");
                    String nickname = set.getString("nickname");
                    int state = set.getInt("state");
                    UserBean userBean = new UserBean();
                    userBean.setUserid(userid);
                    userBean.setNickname(nickname);
                    userBean.setPassword(password);
                    userBean.setUsername(username);
                    userBean.setState(state==0? UserBean.UserState.Offline: UserBean.UserState.Online);
                    friends.add(userBean);

                }//显示数据
                set.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return friends;

    }
}
