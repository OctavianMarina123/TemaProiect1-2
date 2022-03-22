package repository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 * Created by grigo on 3/4/17.
 */
public class JdbcUtils {
    private Properties jdbcProps;

    private static final Logger logger= LogManager.getLogger();
    public JdbcUtils(Properties props){
        this.jdbcProps=props;
    }
    private  Connection instance=null;
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            String url = jdbcProps.getProperty("jdbc.url");
            String user = jdbcProps.getProperty("jdbc.user");
            String pass = jdbcProps.getProperty("jdbc.pass");
            logger.info("trying to connect to database ... {}",url);
            logger.info("user: {}",user);
            logger.info("pass: {}", pass);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            logger.error(e);
            throw new Error("Problem", e);

        }
        logger.traceExit();
        return conn;
    }
/*
    public Connection getConnection(){
        return null;
    }*/
    @Override
    public String toString() {
        return "JdbcUtils{" +
                "jdbcProps=" + jdbcProps +
                ", instance=" + instance +
                '}';
    }
}
