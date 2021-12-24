package dao;

import entity.Filestatus;
import servlet.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/11/10 21:13
 */
public class FileNamedao {

    public boolean Add(Filestatus file) {           //添加文件状态信息
        boolean flag = false;
        int count = 0;
        String sql = "insert into filename_d(filename,filetype,uploader,filestatus,filepath) values(?,?,?,?,?)";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, file.getFilename());
            pst.setString(2, file.getFiletype());
            pst.setString(3, file.getUploader());
            pst.setBoolean(4, file.getFilestatus());
            pst.setString(5, file.getFilepath());
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
    public boolean deleteDicByID(String id) {     //根据id值进行文件及文件信息的删除
        Connection conn = DbHelper.getConnection();
        // TODO 自动生成的方法存根
        boolean flag = false;
        int count =0;
        String sql = "drop table dic_"+id+"_1";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            count = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            if(count>0)
                flag = true;
        }
        return flag;
    }
    public boolean deleteDaByID(String id) {     //根据id值进行文件及文件信息的删除
        Connection conn = DbHelper.getConnection();
        // TODO 自动生成的方法存根
        boolean flag = false;
        int count =0;
        String sql = "drop table data_"+id+"_1";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            count = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            if(count>0)
                flag = true;
        }
        return flag;
    }

    public boolean deleteFileByID(int id) {     //根据id值进行文件及文件信息的删除
        Connection conn = DbHelper.getConnection();
        // TODO 自动生成的方法存根
        boolean flag = false;
        int count =0;
        String sql = "delete from filename_d where id = ?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            count = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            if(count>0)
                flag = true;
        }
        return flag;
    }

    public List<String> selecTable(int i) {          //遍历数据库文件信息
        List<String> list = new ArrayList<>();
        String id = String.valueOf(i);
        String name = "";
        Connection conn  = DbHelper.getConnection();
        String sql = "select table_name from information_schema.tables where table_schema='datamake' and table_name like '%data_"+id+"%'";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                name = rst.getString("table_name");
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

    public List<Filestatus> Selectingd() {          //遍历数据库文件信息
        List<Filestatus> list = new ArrayList<>();
        Connection conn  = DbHelper.getConnection();
        String sql = "select * from filename_d";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                Filestatus user = new Filestatus();
                user.setId(rst.getInt("id"));
                user.setFilename(rst.getString("filename"));
                user.setFiletype(rst.getString("filetype"));
                user.setUploader(rst.getString("uploader"));
                user.setFilestatus(rst.getBoolean("filestatus"));
                user.setFilepath(rst.getString("filepath"));
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
    public Filestatus SelectFilebyID(int id) {      //根据id值进行文件及文件信息的查找
        Connection conn  = DbHelper.getConnection();
        String sql = "select * from filename_d where id = ?";
        Filestatus user = new Filestatus();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                user.setId(rst.getInt("id"));
                user.setFilename(rst.getString("filename"));
                user.setFiletype(rst.getString("filetype"));
                user.setUploader(rst.getString("uploader"));
                user.setFilestatus(rst.getBoolean("filestatus"));
                user.setFilepath(rst.getString("filepath"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return user;
    }
    public boolean UpdateStatus(Filestatus file) {          //根据id值进行文件读取状态的更新
        boolean flag = false;
        int count = 0;
        String sql = "update filename_d set filestatus =? where id =?";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setBoolean(1, file.getFilestatus());
            pst.setInt(2, file.getId());
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


}
