package dao;

import entity.EntityDic;
import entity.Filestatus;
import servlet.DbHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/11/16 8:33
 */
public class Filedao {
    public boolean createTable(String id, List<String> names, String n){
        boolean flag = false;
        String tablename = "data_"+id+"_"+n;
        String typename="";
        int len = names.size();
        Connection conn = DbHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            //得出建表语句中属性字段相关的部分
            for(int i=0;i<len;i++)
            {
                typename=typename+names.get(i)+" longtext,";
            }
            typename = typename.substring(0,typename.length()-1);
            String sql="create table "+tablename+
                    " ("+typename+")";
            if(stmt.execute(sql))
                flag=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }
    public boolean adData(List<String> dicname,String id, List<String> names, String n) {   //插入文件相关值
        boolean flag = false;
        int count = 0;
        String ssq="";
        String dic="";
        String tablename = "data_"+id+"_"+n;
        int len = names.size();
        for(int i=0;i<len-1;i++)
        {
            ssq = ssq +"?,";
        }
        for(int i=0;i<dicname.size();i++){
            dic=dic+dicname.get(i)+",";
        }
        dic = dic.substring(0,dic.length()-1);
        String sql ="insert into "+tablename+" ("+dic+") values("+ssq+"?)";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            for(int j=0;j<len;j++)
            {
                pst.setString(j+1, names.get(j));
            }
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

    public boolean change(List<String> list){
        String name = "";
        for(int j=0;j<list.size();j++) {
            name = "";
            String sql = "select dmms from nomal where dm = '" + list.get(j)+"'";
            Connection conn = DbHelper.getConnection();
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(sql);
                ResultSet rst = pst.executeQuery();
                while(rst.next()) {
                    name = rst.getString("dmms");
                    update(name,list.get(j));
                }
                pst.close();
            } catch (SQLException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean update(String name,String tablename) {        //根据id进行表头信息的获取
        int num = 0;
        String sql = "updata data_44_1 set sbelongwhere = sbelongwhere +"+name+" where sbelongwhere ='"+tablename+"'";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return false;
    }
    public List<String> selectT() {        //根据id进行表头信息的获取
        List<String> num = new ArrayList<>();
        String sql = "select sbelongwhere from data_44_1";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                num.add(rst.getString("sbelongwhere"));
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return num;
    }
    public boolean updatetable(String tablename) {        //根据id进行表头信息的获取
        int num = 0;
        String sql = "ALTER TABLE "+tablename+" DROP COLUMN sstate,swhy,smancheck,serror,salert,salertdemo,serroralert,swhysubmit";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                num=rst.getInt("count(*)");
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return false;
    }

    public int selectRow(String tablename) {        //根据id进行表头信息的获取
        int num = 0;
        String sql = "select count(*) from "+tablename;
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                num=rst.getInt("count(*)");
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return num;
    }

    public List<String> selectDataRow(String num,String tablename,List<String> dicname) {        //根据id进行表头信息的获取
        List<String> list = new ArrayList<>();
        String name = null;
        String sql = "select * from "+tablename+" limit "+num+",1";
        Connection conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                for(int i=0;i<dicname.size();i++) {
                    name = rst.getString(dicname.get(i));
                    list.add(name);
                }
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return list;
    }

    public int selectReaserchNum() {
        Connection conns  = DbHelper.getConnection();
        int num =0;
        String sql = "SELECT count(id) FROM data_38_1 WHERE " +
                "budget !='0' and revenue !='0' and popularity !='0' and vote_average !='0' and vote_count >=50";
        try {
            PreparedStatement pst = conns.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                num = rst.getInt("count(id)");
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return num;
    }

    public List<Filestatus> selectingreaserch() {
        List<Filestatus> list = new ArrayList<>();
        Connection conns  = DbHelper.getConnection();
        String sql = "SELECT id,budget,popularity,release_date,runtime,title,vote_average,vote_count FROM data_38_1 WHERE " +
                "budget !='0' and revenue !='0' and popularity !='0' and vote_average !='0' and vote_count >=50";
        try {
            PreparedStatement pst = conns.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                Filestatus user = new Filestatus();
                user.setId(rst.getInt("vote_count"));
                user.setFilename(rst.getString("id"));
                user.setFiletype(rst.getString("budget"));
                user.setFilepath(rst.getString("popularity"));
                user.setName1(rst.getString("release_date"));
                user.setName2(rst.getString("runtime"));
                user.setName3(rst.getString("title"));
                user.setName4(rst.getString("vote_average"));
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

    public List<Filestatus> selectingclean1() {
        List<Filestatus> list = new ArrayList<>();
        Connection conns  = DbHelper.getConnection();
        String sql = "SELECT id,original_title FROM data_38_1 WHERE release_date =''";
        try {
            PreparedStatement pst = conns.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                Filestatus user = new Filestatus();
                user.setId(rst.getInt("id"));
                user.setFilename(rst.getString("original_title"));
                user.setFiletype("release_date");
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
    public List<Filestatus> selectingclean2() {
        List<Filestatus> list = new ArrayList<>();
        Connection conns  = DbHelper.getConnection();
        String sql = "SELECT id,original_title FROM data_38_1 WHERE runtime =''";
        try {
            PreparedStatement pst = conns.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                Filestatus user = new Filestatus();
                user.setId(rst.getInt("id"));
                user.setFilename(rst.getString("original_title"));
                user.setFiletype("runtime");
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
