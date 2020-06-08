package sandbox2.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String JDBC_MYSQL_HOST = "jdbc:mysql://project2-259623:europe-west3:project2-sql/" +
            "socketFactory=com.google.cloud.sql.mysql.SocketFactory";

//    ***   Google driver + Google DB
//    private static final String MYSQL_JDBC_DRIVER_NAME = "com.mysql.jdbc.GoogleDriver";
//    private static final String URL = "jdbc:google:mysql://project2-259623:europe-west3:project2-sql/";

//    Local Driver + Google DB
//    private static final String MYSQL_JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://35.246.209.192:3306/";
//    private static final String DB_NAME = "travel";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "1234";

//    private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

//    Local Driver + Local DB
    private static final String MYSQL_JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "agencytravel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "h3x4d3c1m4l";

    public static Connection getConnection() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER_NAME);
            return DriverManager.getConnection(URL
                            + DB_NAME,
                    USERNAME,
                    PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
