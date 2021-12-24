package dao;

/**
 * @author June
 * @date 2021/12/16 18:38
 */
import entity.EntityDic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    public boolean read(String strid, String filepath) {

        List<String> dicname = readCsvTitle(filepath);
        if(dicname == null){
            System.out.println("文件读入出错！！");
            return false;
        }else{
            Filedao filedao = new Filedao();
            if(filedao.createTable(strid,dicname,"x")){
                System.out.println("建表成功！！");
            }
            List<List<String>> dataname = readCsvData(filepath);
            for(int i=1;i<dataname.size();i++){
                    if(filedao.adData(dicname,strid,dataname.get(i),"x")){
                        System.out.println("数据插入成功！！");
                }
            }
            Dicdao dao = new Dicdao();
            if(dao.createTable(strid,"x")){
                System.out.println("建表成功");
            }
            List<EntityDic> list = dao.selectdata("data_"+strid+"_"+"x");
            dao.abAdd(list,strid,"x");
            return true;
        }
    }
    public List<List<String>> readCsvData(String filepath) {
        List<List<String>> list = new ArrayList<>();
        String csvFile = filepath;
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                List<String> rowline = Arrays.asList(line.split(cvsSplitBy));
                list.add(rowline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<String> readCsvTitle(String filepath) {
        List<String> list = new ArrayList<>();
        String csvFile = filepath;
        String line = "";
        boolean flag = true;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                flag =false;
                String[] country = line.split(cvsSplitBy);
                for (int i=0;i<country.length;i++){
                    list.add(country[i]);
                }
                if(flag == false){
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}