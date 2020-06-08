package sandbox2.controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(urlPatterns = {"/*"})
public class ParametersFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String lang = req.getParameter("lang");

        if (lang != null) {
            String[] split = lang.split("_");
            session.setAttribute("lang", new Locale(split[0], split[1]));
        } else if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", new Locale("en", "US"));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
