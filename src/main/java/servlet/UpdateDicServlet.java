package servlet;

import dao.Dicdao;
import entity.EntityDic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author June
 * @date 2021/12/15 17:00
 */
@WebServlet("/UpdateDicServlet")
public class UpdateDicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        String tablename = request.getParameter("tablename");
        String name = tablename;
        tablename = tablename.substring(4);
        tablename = "dic"+tablename;
        String firstname = request.getParameter("firstname");
        String secondname = request.getParameter("lastname");
        String thirdname = request.getParameter("jobTitle");
        String forthname = request.getParameter("forthName");
        EntityDic dic = new EntityDic();
        dic.setFirstname(firstname);
        dic.setSecondname(secondname);
        dic.setThirdname(thirdname);
        dic.setForthname(forthname);
        Dicdao dao = new Dicdao();
        dao.updateData(dic,tablename,id);
        request.setAttribute("tablename",name);
        request.getRequestDispatcher("/dicshow.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
