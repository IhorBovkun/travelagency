package sandbox2.dao.impl;

import sandbox2.models.TourData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TourDaoImpl {

    public enum listToursBy {
        COUNTRY("SELECT * FROM tours t WHERE " +
                "t.tour_description_id = ANY (SELECT id FROM tours_descriptions WHERE country = ?)"),
        RESORT("SELECT * FROM tours t WHERE " +
                "t.tour_description_id = ANY (SELECT id FROM tours_descriptions WHERE resort = ?)"),
        HOTEL("SELECT * FROM tours t WHERE " +
                "t.tour_description_id = ANY (SELECT id FROM tours_descriptions WHERE hotel = ?)");
        String value;
        private listToursBy(String value) {
            this.value = value;
        }
    }

    private static final String DB_TABLE = "tours";
    private static final String CREATE_TOUR_BY_ID = "INSERT " + DB_TABLE + " SET " +
            "tour_description_id = ?, availability = ?, departure = ?, arrival = ?, price = ?, name = ?";
    private static final String READ_TOUR_BY_ID = "SELECT * FROM " + DB_TABLE + " WHERE id = ?";
    private static final String UPDATE_TOUR_BY_ID = "UPDATE " + DB_TABLE + " SET "
            + "tour_description_id = ?, availability = ?, departure = ?, arrival = ?, price = ?, name = ? WHERE id = ?";
    private static final String DELETE_TOUR_BY_ID = "DELETE FROM " + DB_TABLE + " WHERE id = ?";

    private static final String LIST_ALL_TOURS = "SELECT * FROM " + DB_TABLE + " ORDER BY RAND()";
    private static final String LIST_SOME_TOURS = "SELECT * FROM " + DB_TABLE + " ORDER BY RAND() LIMIT ?";


    public static int createTour(TourData tourData) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(CREATE_TOUR_BY_ID)
        ) {
            StatementHandler.create(statement, tourData);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static TourData readTour(int tour_id) {
        TourData tourData = null;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_TOUR_BY_ID)
        ) {
            statement.setInt(1, tour_id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    tourData = new TourData();
                    StatementHandler.read(rs, tourData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tourData;
    }

    public static List<TourData> listTours() {

        List<TourData> listTours = new LinkedList<>();
        TourData tourData = null;
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(LIST_ALL_TOURS)
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tourData = new TourData();
                    StatementHandler.read(rs, tourData);
                    listTours.add(tourData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTours;
    }

    public static List<TourData> listTours(int limit) {

        List<TourData> listTours = new LinkedList<>();
        TourData tourData = null;
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(LIST_SOME_TOURS)
        ) {
            statement.setInt(1, limit);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tourData = new TourData();
                    StatementHandler.read(rs, tourData);
                    listTours.add(tourData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTours;
    }

    public static List<TourData> listTours(String location, listToursBy query) {

        List<TourData> listTours = new LinkedList<>();
        TourData tourData = null;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(query.value)
        ) {

            statement.setString(1, location);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tourData = new TourData();
                    StatementHandler.read(rs, tourData);
                    listTours.add(tourData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTours;
    }

    public static synchronized int updateTour(TourData tourData, int tour_id) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_TOUR_BY_ID)
        ) {
            StatementHandler.update(statement, tourData);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static synchronized int deleteTour(int tour_id) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_TOUR_BY_ID)
        ) {
            statement.setInt(1, tour_id);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}