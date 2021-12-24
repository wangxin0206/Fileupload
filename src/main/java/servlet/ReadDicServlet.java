package servlet;

import dao.Dicdao;
import entity.EntityDic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author June
 * @date 2021/12/15 19:01
 */
@WebServlet("/ReadDicServlet")
public class ReadDicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String strid = request.getParameter("name");
        String tablename = "dic"+strid.substring(4);
        request.setAttribute("tablename",tablename);
        request.getRequestDispatcher("/dataedit.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
