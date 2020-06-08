package sandbox2.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "SessionAttributesServlet", urlPatterns = ("/sessionAttributes"))
public class SessionAttributesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Enumeration<String> parameterNames = request.getParameterNames();
        String name;
        Object value;
        while (parameterNames.hasMoreElements()) {
            name = parameterNames.nextElement();
            value = request.getParameter(name);
            if (value == null && session.getAttribute(name) != null){
                session.removeAttribute(name);
            } else if (value != null) {
                session.setAttribute(name, value);
            }
        }
        response.getWriter().write("ok");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
