package sandbox2.utils;

import sandbox2.dao.impl.TourDaoImpl;
import sandbox2.dao.impl.TourDescriptionDaoImpl;
import sandbox2.models.TourData;
import sandbox2.models.TourDescriptionData;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class Utils {

    public static Map<String, HashSet<String>> descrToCategories(List<TourData> tours, Map<Integer, TourDescriptionData> t_descr) {

        Map<String, HashSet<String>> uniq_descr = new HashMap<>();

        uniq_descr.put("countries", new HashSet<>());
        uniq_descr.put("resorts", new HashSet<>());
        uniq_descr.put("hotels", new HashSet<>());

        for (TourData tour : tours) {
            uniq_descr.get("countries").add(t_descr.get(tour.getTour_description_id()).getCountry());
            uniq_descr.get("resorts").add(t_descr.get(tour.getTour_description_id()).getResort());
            uniq_descr.get("hotels").add(t_descr.get(tour.getTour_description_id()).getHotel());
        }
        return uniq_descr;
    }

    public static Map<String, HashSet<String>> descrToCategories(Map<Integer, TourDescriptionData> t_descr) {

        Map<String, HashSet<String>> uniq_descr = new HashMap<>();

        uniq_descr.put("countries", new HashSet<>());
        uniq_descr.put("resorts", new HashSet<>());
        uniq_descr.put("hotels", new HashSet<>());

        for (TourDescriptionData value : t_descr.values()) {
            uniq_descr.get("countries").add(value.getCountry());
            uniq_descr.get("resorts").add(value.getResort());
            uniq_descr.get("hotels").add(value.getHotel());
        }
        return uniq_descr;
    }

    public static Map<Integer, TourDescriptionData> listToursDescrFromSession(HttpSession session) {
        Object hotel = session.getAttribute("hotel");
        Object resort = session.getAttribute("resort");
        Object country = session.getAttribute("country");
        Map<Integer, TourDescriptionData> t_descr;
        String entry = null;

        if (country != null) {
            entry = (String)country;
            if (! entry.equalsIgnoreCase("all")) {
                t_descr = TourDescriptionDaoImpl.listTourDescriptions();
                return t_descr;
            }
        }

        if (resort != null) {
            entry = (String)resort;
            if (! entry.equalsIgnoreCase("all")) {
                t_descr = TourDescriptionDaoImpl.listTourDescriptions(entry, TourDescriptionDaoImpl.listToursDescrBy.COUNTRY);
                return t_descr;
            }
        }

        if (hotel != null) {
            entry = (String)hotel;
            if (! entry.equalsIgnoreCase("all")) {
                t_descr = TourDescriptionDaoImpl.listTourDescriptions(entry, TourDescriptionDaoImpl.listToursDescrBy.RESORT);
                return t_descr;
            }
        }

        t_descr = TourDescriptionDaoImpl.listTourDescriptions();
        return t_descr;
    }

    public static List<TourData> listToursFromSession(HttpSession session) {
        Object hotel = session.getAttribute("hotel");
        Object resort = session.getAttribute("resort");
        Object country = session.getAttribute("country");
        List<TourData> tours;
        String entry = null;

        if (hotel != null) {
            entry = (String)hotel;
            if (! entry.equalsIgnoreCase("all")) {
                tours = TourDaoImpl.listTours(entry, TourDaoImpl.listToursBy.HOTEL);
                return tours;
            }
        }

        if (resort != null) {
            entry = (String)resort;
            if (! entry.equalsIgnoreCase("all")) {
                tours = TourDaoImpl.listTours(entry, TourDaoImpl.listToursBy.RESORT);
                return tours;
            }
        }

        if (country != null) {
            entry = (String)country;
            if (! entry.equalsIgnoreCase("all")) {
                tours = TourDaoImpl.listTours(entry, TourDaoImpl.listToursBy.COUNTRY);
                return tours;
            }
        }

        tours = TourDaoImpl.listTours();
        return tours;
    }

    //  Capitalize first Symbol
    public static String capitalize(String s) {
        String res = null;

        if (s == null) {
            return null;
        }
        switch (s.length()) {
            case 0:
                break;
            case 1:
                res = s.toUpperCase();
                break;
            default:
                res = s.substring(0,1).toUpperCase() + s.substring(1);
        }
        return res;
    }
}
