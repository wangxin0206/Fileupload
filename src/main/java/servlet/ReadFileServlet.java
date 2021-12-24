package servlet;

import dao.CSVReader;
import dao.Dicdao;
import dao.Excelread;
import dao.FileNamedao;
import entity.Filestatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author June
 * @date 2021/11/15 10:06
 */
@WebServlet("/ReadFileServlet")
public class ReadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String strid=request.getParameter("id");
        String message = "";
        int id = Integer.parseInt(strid);
        FileNamedao dao = new FileNamedao();
        Filestatus files = dao.SelectFilebyID(id);
        String filepath = files.getFilepath();
        String fileType=files.getFiletype();//获得后缀名
        if(fileType.equals("csv")){
            CsvToXlsxUtil change = new CsvToXlsxUtil();
            String changepath=change.csvToXLSX(filepath,"utf-8");
            Excelread reads = new Excelread();
            if(reads.read(strid, "E:\\IDEA\\IDEA\\Working\\Fileupload\\target\\Fileupload\\WEB-INF\\upload\\"+changepath)){
                files.setFilestatus(true);
                dao.UpdateStatus(files);
                message = "文件读取成功！！";
                String n=reads.getNumbiao();
                request.setAttribute("message",message);
                request.setAttribute("tablename","data_"+strid+"_1");
                request.getRequestDispatcher("/dicshow.jsp").forward(request, response);
            }else{
                message = "文件读取失败！！";
                request.setAttribute("message",message);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else{
            Excelread reads = new Excelread();
            if(reads.read(strid, filepath)){
                files.setFilestatus(true);
                dao.UpdateStatus(files);
                message = "文件读取成功！！";
                String n=reads.getNumbiao();
                request.setAttribute("message",message);
                request.setAttribute("tablename","data_"+strid+"_"+n);
                request.getRequestDispatcher("/dicshow.jsp").forward(request, response);
            }else{
                message = "文件读取失败！！";
                request.setAttribute("message",message);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
