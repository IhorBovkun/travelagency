package sandbox2.dao.impl;

import sandbox2.models.TourDescriptionData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TourDescriptionDaoImpl {

    public enum listToursDescrBy {
        COUNTRY("SELECT * FROM tours_descriptions WHERE country = ?"),
        RESORT("SELECT * FROM tours_descriptions WHERE resort = ?"),
        HOTEL("SELECT * FROM tours_descriptions WHERE hotel = ?)");
        String value;
        private listToursDescrBy(String value) {
            this.value = value;
        }
    }

    private static final String DB_TABLE = "tours_descriptions";
    private static final String CREATE_T_DESCR_BY_ID = "INSERT " + DB_TABLE + " SET " +
            "hotel = ?, resort = ?, country = ?, short_description = ?, long_description = ?";
    private static final String READ_T_DESCR_BY_ID = "SELECT * FROM " + DB_TABLE + " WHERE id = ?";
    private static final String UPDATE_T_DESCR_BY_ID = "INSERT " + DB_TABLE + " SET " +
        "hotel = ?, resort = ?, country = ?, short_description = ?, long_description = ? WHERE id = ?";
    private static final String DELETE_T_DESCR_BY_ID = "DELETE FROM " + DB_TABLE + " WHERE id = ?";
    private static final String READ_MAP_T_DESCR = "SELECT * FROM " + DB_TABLE;
    private static final String READ_MAP_T_DESCR_BY_COUNTRY = "SELECT * FROM " + DB_TABLE + " WHERE country = ?";

    public static int createTourDescription(TourDescriptionData tourDescriptionData) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(CREATE_T_DESCR_BY_ID)
        ) {
            StatementHandler.create(statement, tourDescriptionData);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static TourDescriptionData readTourDescription(int t_descr_id) {
        TourDescriptionData tourDescriptionData = null;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_T_DESCR_BY_ID)
        ) {
            statement.setInt(1,t_descr_id);

            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    tourDescriptionData = new TourDescriptionData();
                    StatementHandler.read(rs, tourDescriptionData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tourDescriptionData;
    }

    public static synchronized int updateTourDescription(TourDescriptionData tourDescriptionData) {
            int result = 0;

            try (
                    Connection conn = ConnectionDB.getConnection();
                    PreparedStatement statement = conn.prepareStatement(UPDATE_T_DESCR_BY_ID)
            ) {
                StatementHandler.update(statement, tourDescriptionData);
                result = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }

    public static synchronized int deleteTourDescription(int t_descr_id) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_T_DESCR_BY_ID)
        ) {
            statement.setInt(1, t_descr_id);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Map<Integer, TourDescriptionData> listTourDescriptions() {
        Map<Integer, TourDescriptionData> map = new HashMap<>();
        TourDescriptionData tourDescriptionData = null;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_MAP_T_DESCR)
        ) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tourDescriptionData = new TourDescriptionData();
                    StatementHandler.read(rs, tourDescriptionData);
                    map.put((Integer)tourDescriptionData.getId(), tourDescriptionData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<Integer, TourDescriptionData> listTourDescriptions(String location, listToursDescrBy query) {

        Map<Integer, TourDescriptionData> map = new HashMap<>();
        TourDescriptionData tourDescriptionData = null;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(query.value)
        ) {

            statement.setString(1, location);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tourDescriptionData = new TourDescriptionData();
                    StatementHandler.read(rs, tourDescriptionData);
                    map.put(tourDescriptionData.getId(), tourDescriptionData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }
}

