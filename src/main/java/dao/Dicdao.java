package dao;

import entity.EntityDic;
import entity.Filestatus;
import servlet.DbHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/11/15 10:36
 */
public class Dicdao {

    public boolean createTable(String id,String n){
        boolean flag = false;
        String tablename = "dic_"+id+"_"+n;
        Connection conn = DbHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql="create table "+tablename+
                        " (tid int primary key auto_increment,firstname longtext,secondname longtext,thirdname longtext,forthname longtext)";
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }



    public List<String> selectDic(String tablename) {        //根据id进行表头信息的获取
        List<String> list = new ArrayList<>();
        String name = null;
        String sql = "select firstname from "+tablename;
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                name=rst.getString("firstname");
                list.add(name);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
    public void abAdd(List<EntityDic> dic,String id,String n){
        for(int i=0;i< dic.size();i++){
            Add(dic.get(i),id,n);
        }
    }
    public boolean Add(EntityDic dic,String id,String n) {   //插入文件相关值
        boolean flag = false;
        int count = 0;
        String tablename = "dic_"+id+"_"+n;
        String sql = "insert into "+tablename+" (firstname,secondname,thirdname,forthname) values(?,?,?,?)";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, dic.getFirstname());
            pst.setString(2, dic.getSecondname());
            pst.setString(3, dic.getThirdname());
            pst.setString(4, dic.getForthname());
            count = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            if(count>0)
                flag = true;
        }
        return flag;
    }
    public List<String> selectTitle(String id,String num) {        //根据id进行表头信息的获取
        String tablename = "dic_"+id+"_"+num;
        List<String> list = new ArrayList<>();
        String name = null;
        String sql = "select COLUMN_NAME from "+tablename;
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                name=rst.getString("COLUMN_NAME");
                list.add(name);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }

    public List<EntityDic> selectdata(String tablename) {           //获取文件全部数据信息
        List<EntityDic> list = new ArrayList<>();
        Connection conn  = DbHelper.getConnection();
        String sql = "select COLUMN_NAME,column_comment,data_type,COLUMN_KEY from information_schema.columns "+
                "where table_name='"+tablename+"' and table_schema='datamake'";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                EntityDic user = new EntityDic();
                user.setFirstname(rst.getString("COLUMN_NAME"));
                user.setSecondname(rst.getString("column_comment"));
                user.setThirdname(rst.getString("data_type"));
                user.setForthname(rst.getString("COLUMN_KEY"));
                list.add(user);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }

    public void updateData(EntityDic dic, String tablename,int tid) {
        Connection conn = DbHelper.getConnection();
        boolean flag =false;
        int count = 0;
        String sql = "update "+tablename+" set firstname=?,secondname=?,thirdname =?,forthname =? where tid =?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, dic.getFirstname());
            pst.setString(2, dic.getSecondname());
            pst.setString(3, dic.getThirdname());
            pst.setString(4, dic.getForthname());
            pst.setInt(5, tid);
            count = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            if(count>0) {
                flag = true;
            }
        }

        // TODO 自动生成的方法存根
    }

    public List<EntityDic> selectTll(String tablename) {
        List<EntityDic> list = new ArrayList<>();
        Connection conn  = DbHelper.getConnection();
        String sql = "select * from "+tablename;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                EntityDic user = new EntityDic();
                user.setId(rst.getInt("tid"));
                user.setFirstname(rst.getString("firstname"));
                user.setSecondname(rst.getString("secondname"));
                user.setThirdname(rst.getString("thirdname"));
                user.setForthname(rst.getString("forthname"));
                list.add(user);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }
}
