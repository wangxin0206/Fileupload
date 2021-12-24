package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/12/17 15:07
 */
public class Client {
    public static void main(String[] args){
        Filedao dao = new Filedao();
        List<String> list = dao.selectT();
        dao.change(list);
    }
}
