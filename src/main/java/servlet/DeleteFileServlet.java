package servlet;

import dao.FileNamedao;
import entity.Filestatus;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author June
 * @date 2021/11/10 11:06
 */
@WebServlet("/DeleteFileServlet")
public class DeleteFileServlet extends HttpServlet{

    private static final java.util.UUID UUID = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String message ="";
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        FileNamedao dao = new FileNamedao();
        Filestatus files = dao.SelectFilebyID(id);
        File file = new File(files.getFilepath());
        file.delete();
        if (file.exists()==false) {
            if(dao.deleteFileByID(id)) {
                dao.deleteDicByID(strid);
                dao.deleteDaByID(strid);
                message = "文件已删除！";
            }else{
                message = "文件删除失败！";
            }
        }else{
            message = "文件不存在！";
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}