package repository;

import domain.Scene;
import exceptions.RepoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/*
public class abcd implements Repository<Integer, Scene> {
    private JdbcUtils dbUtils;
    @Override
    public int size() {
        return 0;
    }


    @Override
    public void add(Scene scene) throws Exception {
        String sql = "insert into Scene (Name, Capacity)" +
                " values (?,?)";
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, scene.getName());
            ps.setInt(2, scene.getCapacity());
            ps.setInt(3, scene.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Scene scene) throws Exception {
        String sql = "update Scene set Name=?, Capacity=? " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public Scene delete(Integer integer) throws RepoException {
        String sql = "delete from Scene where id=?";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Scene scene = findById(integer);
            ps.setInt(1, integer);
            ps.executeUpdate();
            return scene;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RepoException("Scene doesn't exist!\n");
    }

    @Override
    public Scene findById(Integer integer) throws RepoException {
        String sql = "select * from Scene where id=?";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, integer);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    int capacity = resultSet.getInt("Capacity");

                    Scene scene = new Scene(id, name,capacity);
                    return scene;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RepoException("Scene doesn't exist!\n");
    }


    @Override
    public Collection<Scene> findAll() {
        Set<Scene> scenes = new HashSet<>();
        Connection connection=dbUtils.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * from Scene");
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
            e.printStackTrace();
        }
        return scenes;
    }
}
*/