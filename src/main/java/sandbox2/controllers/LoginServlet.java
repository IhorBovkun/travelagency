package sandbox2.controllers;

import sandbox2.dao.impl.UserDaoImpl;
import sandbox2.models.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("user").trim();
        String password = request.getParameter("password").trim();
        UserData ud = UserDaoImpl.readUser(login);
        HttpSession session = request.getSession();

        if (ud == null) {
            response.getWriter().write("user");
        } else if (! ud.getPassword().equals(password)) {
            response.getWriter().write("password");
        } else {
            session.setAttribute("user", ud);
            response.getWriter().write("ok");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }
}
