package com.chatroom.server.repository;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.GroupBean;
import com.chatroom.server.model.MessageBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class GroupRepository extends AbstractRepository{
    public static final String TABLE_GROUP = "t_group";
    public static final String TABLE_GROUP_REF_USER = "t_group_ref_user";
    public GroupRepository(DBHelper dbHelper) {
        super(dbHelper);
    }

    public List<GroupBean> userGroup(String userid){
        String sql = "select g.* from "+TABLE_GROUP+" as g\n" +
                "LEFT JOIN "+TABLE_GROUP_REF_USER+" as ref\n" +
                "on g.groupid = ref.groupid\n" +
                "where ref.userid = '"+userid+"'";
        List<GroupBean> groupBeans = new ArrayList<>();
        PreparedStatement statement = dbHelper.execSql(sql);
        if(statement!=null){
            try {
                ResultSet set = statement.executeQuery();
                while (set.next()) {
                    String groupid = set.getString("groupid");
                    String groupname = set.getString("groupname");
                    GroupBean group = new GroupBean();
                    group.setGroupid(groupid);
                    group.setGroupname(groupname);
                    groupBeans.add(group);
                }//显示数据
                set.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return groupBeans;
    }

}
