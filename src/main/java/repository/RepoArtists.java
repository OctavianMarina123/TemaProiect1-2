package repository;

import domain.Artist;
import domain.Scene;
import exceptions.RepoException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class RepoArtists implements Repository<Integer, Artist> {
    private JdbcUtils dbUtils;
    public RepoArtists(Properties dbUtils) {
        this.dbUtils=new JdbcUtils(dbUtils);
    }
    private static final Logger logger= LogManager.getLogger();
    @Override
    public int size() {
        logger.traceEntry();
        String sql="SELECT COUNT(*) FROM \"Artists\"";
        int count=0;
        logger.info("trying to connect to database ... ");
        logger.traceEntry("Parameters {}",count);
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
    public void add(Artist artist) throws Exception {
        String sql = "insert into \"Artists\" (\"Name\",\"SceneID\",\"date\")" +
                " values (?,?,?)";
        System.out.println(sql);
        logger.traceEntry("parameters: {}",artist);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.setInt(2, artist.getSceneId());
            ps.setTimestamp(3, Timestamp.valueOf(artist.getDate()));
            logger.traceExit();
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();

        }
    }

    @Override
    public void update(Artist artist) throws Exception {
        String sql = "update \"Artists\" set \"Name\"=?, \"SceneID\"=?, \"date\"=? " +
                "where id=?";
        logger.traceEntry("parameters: {}",artist);
        Collection<Artist> artists = findAll();
        for (Artist ar : artists) {
            if (ar.equals(artists)) {
                throw new RepoException("Artist already exists!\n");
            }
        }
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, artist.getName());
            ps.setInt(2, artist.getSceneId());
            ps.setTimestamp(3, Timestamp.valueOf(artist.getDate()));
            ps.setInt(4,artist.getID());
            ps.executeUpdate();
            logger.traceExit();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }






    @Override
    public Artist delete(Integer integer) throws RepoException {
        String sql = "delete from \"Artists\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Artist artist = findById(integer);
            ps.setInt(1, integer);
            ps.executeUpdate();
            logger.traceExit();
            return artist;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        throw new RepoException("Scene doesn't exist!\n");
    }

    @Override
    public Artist findById(Integer integer) throws RepoException {
        String sql = "select * from \"Artists\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, integer);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    Integer sceneId= resultSet.getInt("SceneID");
                    Timestamp date = resultSet.getTimestamp("date");

                    Artist artist = new Artist(id, name,sceneId,date.toLocalDateTime());
                    return artist;
                }
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }

        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        throw new RepoException("Scene doesn't exist!\n");
    }


    @Override
    public Collection<Artist> findAll() {
        Set<Artist> artists = new HashSet<>();
        logger.traceEntry();
        Connection connection= null;
        try {
            connection = dbUtils.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try (
                PreparedStatement statement = connection.prepareStatement("select *from \"Artists\";");
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                Integer sceneId= resultSet.getInt("SceneID");
                Timestamp date = resultSet.getTimestamp("date");

                Artist artist = new Artist(id, name,sceneId,date.toLocalDateTime());
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();
        return artists;
    }
}
