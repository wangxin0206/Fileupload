package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author June
 * @date 2021/12/14 20:52
 */
@WebServlet("/ReadDataServlet")
public class ReadDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String tabledic = "dic_"+id+"_1";
        String tablename = "data_"+id+"_1";
        request.setAttribute("tabledata",tablename);
        request.setAttribute("tablename",tabledic);
        request.setAttribute("id",id);
        request.getRequestDispatcher("/datashow.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
