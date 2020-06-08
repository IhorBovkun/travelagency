package sandbox2.models;

import java.io.Serializable;
import java.sql.Date;

public class TourData extends Container implements Serializable {

    /**
     * All fields are iterates by iterator
     */
    private int id;
    private int tour_description_id;
    private int availability;
    private Date departure;
    private Date arrival;
    private double price;
    private String service;
//    private String name;
//    public static String[] fieldsGetters = {"getId", "getTour_description_id", "getAvailability",
//            "getDeparture", "getArrival", "getPrice"};
//    public static String[] fieldsSetters = {"setId", "setTour_description_id", "setAvailability",
//            "setDeparture", "setArrival", "setPrice"};
//    public static String[] fields = {"id", "tour_description_id", "availability", "departure", "arrival", "price"};

    /**
     * Getters-Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTour_description_id() {
        return tour_description_id;
    }

    public void setTour_description_id(int tour_description_id) {
        this.tour_description_id = tour_description_id;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public int getPrice() {
        return (int) price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    /**
     * END
     * Getters-Setters
     */

    @Override
    public String toString() {
        return "TourData{" +
                "id=" + id +
                ", tour_description_id=" + tour_description_id +
                ", availability=" + availability +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", price=" + price +
                '}';
    }

}