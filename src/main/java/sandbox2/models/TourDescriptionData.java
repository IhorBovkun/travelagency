package sandbox2.models;

public class TourDescriptionData extends Container {
    private int id;
    private int rating;
    private String hotel;
    private String resort;
    private String country;
    private String short_description;
    private String long_description;

    /**
     * GETTERS-SETTERS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }
    /**
     * END
     * GETTERS-SETTERS
     */

    @Override
    public String toString() {
        return "TourDescriptionData{" +
                "id=" + id +
                ", hotel='" + hotel + '\'' +
                ", resort='" + resort + '\'' +
                ", country='" + country + '\'' +
                ", short_description='" + short_description + '\'' +
                ", long_description='" + long_description + '\'' +
                '}';
    }
}
