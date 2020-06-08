package sandbox2.dao.impl;

import sandbox2.models.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl {

    private static final String DB_TABLE = "users";
    private static final String CREATE_USER_BY_ID = "INSERT " + DB_TABLE + " SET "
            + "role = ?, login = ?, password = ?, email = ?, name = ?, last_name = ?, birth = ?";
    private static final String READ_USER_BY_LOGIN = "SELECT * FROM " + DB_TABLE + " WHERE login = ?";
    private static final String READ_USER_BY_ID = "SELECT * FROM " + DB_TABLE + " WHERE id = ?";
    private static final String LIST_ALL_USERS = "SELECT * FROM " + DB_TABLE;
    private static final String UPDATE_USER_BY_ID = "UPDATE " + DB_TABLE + " SET "
            + "role = ?, login = ?, password = ?, email = ?, name = ?, last_name = ?, birth = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM " + DB_TABLE + " WHERE id = ?";


    public static int createUser(UserData userData) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(CREATE_USER_BY_ID)
        ) {
            StatementHandler.create(statement, userData);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static UserData readUser(String login) {
        UserData userData = null;

        try (
//                Connection conn = ConnectionPool.getInstance().getConnection();
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_USER_BY_LOGIN)
        ) {
            statement.setString(1, login);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    userData = new UserData();
                    StatementHandler.read(rs, userData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public static UserData readUser(int user_id) {
        UserData userData = null;

        try (
//                Connection conn = ConnectionPool.getInstance().getConnection();
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_USER_BY_ID)
        ) {
            statement.setInt(1, user_id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    userData = new UserData();
                    StatementHandler.read(rs, userData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }

    public static List<UserData> listUsers() {
        List<UserData> listUsers = new LinkedList<>();
        UserData userData = null;
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(LIST_ALL_USERS)
        ) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    userData = new UserData();
                    StatementHandler.read(rs, userData);
                    listUsers.add(userData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listUsers;
    }

    public static synchronized int updateUser(UserData userData, int user_id) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_USER_BY_ID)
        ) {
            StatementHandler.update(statement, userData);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public static synchronized int deleteUser(int user_id) {
        int result = 0;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_USER_BY_ID)
        ) {
            statement.setInt(1, user_id);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
