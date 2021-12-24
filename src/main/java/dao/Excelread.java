package dao;

import entity.EntityDic;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/11/5 8:40
 */
public class Excelread {
    private String numbiao;

    public String getNumbiao() {
        return numbiao;
    }

    public void setNumbiao(String numbiao) {
        this.numbiao = numbiao;
    }

    public boolean read(String id, String filepath) {
        //文件读取主要流程
        List<String> dicname = new ArrayList<>();
        Excelread excelTest=new Excelread();
        Workbook wb = excelTest.getExcel(filepath);
        int number = wb.getNumberOfSheets();
        String num;
        if(wb==null){
            System.out.println("文件读入出错");
            return false;
        }
        else {
            for(int n=0;n<number;n++)
            {
                num = String.valueOf(n+1);
                dicname=excelTest.unanalyzeExcel(wb,id,num);
                excelTest.analyzeData(dicname,wb,id,num);
                Dicdao dao = new Dicdao();
                if(dao.createTable(id,num)){
                    System.out.println("建表成功");
                }
                List<EntityDic> list = dao.selectdata("data_"+id+"_"+num);
                dao.abAdd(list,id,num);
                setNumbiao(num);
            }
            return true;
        }
    }

    public void analyzeData(List<String> dicname,Workbook wb,String id,String n){
        Sheet sheet=wb.getSheetAt(Integer.parseInt(n)-1);//读取sheet(从0计数)
        int rowNum=sheet.getLastRowNum();//读取行数(从0计数)
        for(int i=1;i<=rowNum;i++){
            List<String> datas = new ArrayList<>();
            Row row=sheet.getRow(i);//获得行
            int colNum=row.getLastCellNum();//获得当前行的列数
            for(int j=0;j<colNum;j++){
                Cell cell=row.getCell(j);//获取单元格
                if(cell==null)
                    System.out.print("null     ");
                else
                {
                    System.out.print(cell.toString()+"     ");
                    datas.add(cell.toString());
                }
            }
            System.out.println();
            Filedao dao = new Filedao();
            if(dao.adData(dicname,id,datas,n)){
                System.out.println("数据插入成功！！");
            }
        }

    }

    //存储读取字符数组
    public List<String> unanalyzeExcel(Workbook wb,String id,String n)
    {
        Sheet sheet=wb.getSheetAt(Integer.parseInt(n)-1);//读取sheet(从0计数)
        List<String> name = new ArrayList<>();
        String dicname = null;
        Row row=sheet.getRow(0);//获得行
        int colNum=row.getLastCellNum();//获得当前行的列数
        for(int j=0;j<colNum;j++)
        {
            Cell cell = row.getCell(j);//获取单元格
            if(cell==null)
            {
                System.out.print("null     ");
                dicname = "";
                name.add(dicname);
            }
            else {
                dicname = cell.toString();
                System.out.println(cell.toString()+"<--->");
                name.add(dicname);
            }
        }
        Filedao dao = new Filedao();
        if(dao.createTable(id,name,n)){
            System.out.println("建表成功！！");
        }
        return name;
    }

    public Workbook getExcel(String filePath){
        Workbook wb=null;
        File file=new File(filePath);
        if(!file.exists()){
            System.out.println("文件不存在");
            wb=null;
        }
        else {
            String fileType=filePath.substring(filePath.lastIndexOf("."));//获得后缀名
            try {
                InputStream is = new FileInputStream(filePath);
                if(".xls".equals(fileType)){
                    wb = new HSSFWorkbook(is);
                }else if(".xlsx".equals(fileType)){
                    wb = new XSSFWorkbook(is);
                }else{
                    System.out.println("格式不正确");
                    wb=null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return wb;
    }


}
