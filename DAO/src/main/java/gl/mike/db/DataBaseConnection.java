package gl.mike.db;


import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    private final static Logger LOGGER = Logger.getLogger(DataBaseConnection.class);

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_CONN_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_CONN_NAME = "root";
    private static final String DB_CONN_PASS = "1234";

    private static DataBaseConnection instance;

    private List<Connection> poolConnections = new ArrayList<Connection>(0);

    private DataBaseConnection() {
        // load driver
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
            System.out.println("jvm-library.log");

            LOGGER.info("Registered JDBC driver");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("adsadsa " + "sadsa ");
            }
        }
    }

    public static synchronized DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }

        return instance;
    }

    public synchronized Connection getConnection() {
        Connection conn;
        if (!poolConnections.isEmpty()) {
            conn = poolConnections.get(poolConnections.size() - 1);
            poolConnections.remove(conn);

            try {
                if (conn.isClosed()) {
                    LOGGER.info("Removed bad connection");
                    // try again
                    conn = getConnection();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                // try again
                conn = getConnection();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                // try again
                conn = getConnection();
            }
        } else {
            conn = newConnection();
            if (conn != null) {
                poolConnections.add(conn);
                LOGGER.info("Created new connection");
            }
        }

        return conn;
    }

    private Connection newConnection() {
        try {
            return DriverManager.getConnection(DB_CONN_URL, DB_CONN_NAME, DB_CONN_PASS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public synchronized void closeAllConnections() {
        List<Connection> connToRemove = new ArrayList<>(0);

        for (Connection conn : poolConnections) {
            try {
                conn.close();
                connToRemove.add(conn);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        poolConnections.removeAll(connToRemove);
        LOGGER.info("Closed all connections");
    }
}
