package sandbox2.controllers;

import sandbox2.dao.impl.TourDescriptionDaoImpl;
import sandbox2.models.TourData;
import sandbox2.models.TourDescriptionData;
import sandbox2.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    public void init() {

        //  Tours descriptions
        if (getServletContext().getAttribute("locations") == null) {

            Map<Integer, TourDescriptionData> t_descr = TourDescriptionDaoImpl.listTourDescriptions();
            Map<String, HashSet<String>> t_descr_cat = Utils.descrToCategories(t_descr);
            getServletContext().setAttribute("locations", t_descr);
            getServletContext().setAttribute("locations_by_category", t_descr_cat);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("hello from doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<TourData> tours = Utils.listToursFromSession(session);

//        Map<Integer, TourDescriptionData> t_descr = (Map<Integer, TourDescriptionData>)(getServletContext().getAttribute("locations"));
        Map<Integer, TourDescriptionData> t_descr = TourDescriptionDaoImpl.listTourDescriptions();
        Map<String, HashSet<String>> t_descr_cat = Utils.descrToCategories(tours, t_descr);

        String sort;
        if ((sort = request.getParameter("sort")) != null) {
            if (sort.equalsIgnoreCase("countryName")) {
                Map<Integer, TourDescriptionData> countries = (Map<Integer, TourDescriptionData>) (getServletContext().getAttribute("locations"));
                tours.sort((TourData a, TourData b) -> {
                    return countries.get(a.getTour_description_id()).getCountry().compareToIgnoreCase(
                            countries.get(b.getTour_description_id()).getCountry());
                });
                session.setAttribute("selectBySort", "countryName");
            } else if (sort.equalsIgnoreCase("price")) {
                tours.sort((TourData a, TourData b) -> {
                    return (int) (a.getPrice() - b.getPrice());
                });
                session.setAttribute("selectBySort", "price");
            } else if (sort.equalsIgnoreCase("date")) {
                tours.sort((TourData a, TourData b) -> {
                    return a.getDeparture().compareTo(b.getDeparture());
                });
                session.setAttribute("selectBySort", "date");
            } else if (sort.equalsIgnoreCase("random")) {

                session.setAttribute("selectBySort", "random");
            }
        } else if ((sort = (String)session.getAttribute("selectBySort")) != null) {
            if (sort.equalsIgnoreCase("countryName")) {
                Map<Integer, TourDescriptionData> countries = (Map<Integer, TourDescriptionData>) (getServletContext().getAttribute("locations"));
                tours.sort((TourData a, TourData b) -> {
                    return countries.get(a.getTour_description_id()).getCountry().compareToIgnoreCase(
                            countries.get(b.getTour_description_id()).getCountry());
                });
            } else if (sort.equalsIgnoreCase("price")) {
                tours.sort((TourData a, TourData b) -> {
                    return (int) (a.getPrice() - b.getPrice());
                });
            } else if (sort.equalsIgnoreCase("date")) {
//                tours.sort(Comparator.comparing(TourData::getDeparture));
                tours.sort((TourData a, TourData b) -> {
                    return a.getDeparture().compareTo(b.getDeparture());
                });
            } else if (sort.equalsIgnoreCase("random")) {

            }
        }

        request.setAttribute("tours", tours);
//        request.setAttribute("locations", t_descr);
        request.setAttribute("locations_by_category", t_descr_cat);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}