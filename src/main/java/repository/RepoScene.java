package repository;

import domain.Scene;
import exceptions.RepoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class RepoScene implements Repository<Integer, Scene> {
    private JdbcUtils dbUtils;
    public RepoScene(Properties dbUtils) {
        this.dbUtils=new JdbcUtils(dbUtils);
    }
    private static final Logger logger= LogManager.getLogger();
    @Override
    public int size() {
        logger.traceEntry();
        String sql="SELECT COUNT(*) FROM \"Scene\"";
        int count=0;
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //statement.setInt(1, integer);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                   count = resultSet.getInt("count");
                }
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();
        return count;
    }


    @Override
    public void add(Scene scene) throws Exception {
        logger.traceEntry("Parameters {}: ",scene);
        String sql = "insert into \"Scene\" (\"Name\", \"Capacity\")" +
                " values (?,?)";
        System.out.println(sql);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, scene.getName());
            ps.setInt(2, scene.getCapacity());
            logger.traceExit();
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Scene scene) throws Exception {
        logger.traceEntry("Parameters: {} ",scene);
        String sql = "update \"Scene\" set \"Name\"=?, \"Capacity\"=? " +
                "where id=?";
        Collection<Scene> scenes = findAll();
        for (Scene sc : scenes) {
            if (sc.equals(scenes)) {
                throw new RepoException("Scene already exists!\n");
            }
        }
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, scene.getName());
            ps.setInt(2, scene.getCapacity());
            ps.setInt(3, scene.getID());
            ps.executeUpdate();
            logger.traceExit();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }






    @Override
    public Scene delete(Integer integer) throws RepoException {
        String sql = "delete from \"Scene\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Scene scene = findById(integer);
            ps.setInt(1, integer);
            ps.executeUpdate();
            logger.traceExit();
            return scene;

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        throw new RepoException("Scene doesn't exist!\n");
    }

    @Override
    public Scene findById(Integer integer) throws RepoException {
        String sql = "select * from \"Scene\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, integer);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    int capacity = resultSet.getInt("Capacity");

                    Scene scene = new Scene(id, name,capacity);
                    logger.traceExit();
                    return scene;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        throw new RepoException("Scene doesn't exist!\n");
    }


    @Override
    public Collection<Scene> findAll() {
        logger.traceEntry();
        Set<Scene> scenes = new HashSet<>();
        Connection connection= null;
        try {
            connection = dbUtils.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try (
                PreparedStatement statement = connection.prepareStatement("select *from \"Scene\";");
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                int capacity = resultSet.getInt("Capacity");

                Scene scene = new Scene(id, name,capacity);
                scenes.add(scene);
            }
            return scenes;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceEntry("Parameters: {}",scenes);
        return scenes;
    }
}
