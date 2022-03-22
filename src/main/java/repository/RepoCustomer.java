package repository;

import domain.Customer;
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

public class RepoCustomer implements Repository<Integer, Customer> {
    private JdbcUtils dbUtils;
    public RepoCustomer(Properties dbUtils) {
        this.dbUtils=new JdbcUtils(dbUtils);
    }
    private static final Logger logger= LogManager.getLogger();
    @Override
    public int size() {
        logger.traceEntry();
        String sql="SELECT COUNT(*) FROM \"Customer\"";
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
    public void add(Customer customer) throws Exception {
        logger.traceEntry("parameters: {}",customer);
        String sql = "insert into \"Customer\" (\"Name\", \"SceneID\")" +
                " values (?,?)";
        System.out.println(sql);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getSceneId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) throws Exception {
        String sql = "update \"Customer\" set \"Name\"=?, \"SceneID\"=? " +
                "where id=?";
        logger.traceEntry("parameters: {}",customer);
        Collection<Customer> customers = findAll();
        for (Customer cs : customers) {
            if (cs.equals(customer)) {
                throw new RepoException("Scene already exists!\n");
            }
        }
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setInt(2, customer.getSceneId());
            ps.setInt(3, customer.getId());
            logger.traceExit();
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }






    @Override
    public Customer delete(Integer integer) throws RepoException {
        String sql = "delete from \"Customer\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            Customer customer = findById(integer);
            ps.setInt(1, integer);
            ps.executeUpdate();
            logger.traceExit();
            return customer;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        throw new RepoException("Customer doesn't exist!\n");
    }

    @Override
    public Customer findById(Integer integer) throws RepoException {
        String sql = "select * from \"Customer\" where id=?";
        logger.traceEntry("Parameters: {}",integer);
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, integer);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    int sceneID = resultSet.getInt("SceneID");

                    Customer customer = new Customer(id, name,sceneID);
                    logger.traceExit();
                    return customer;
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
    public Collection<Customer> findAll() {
        Set<Customer> customers = new HashSet<>();
        logger.traceEntry();
        Connection connection= null;
        try {
            connection = dbUtils.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        try (
                PreparedStatement statement = connection.prepareStatement("select *from \"Customer\";");
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                int sceneID = resultSet.getInt("SceneID");

                Customer customer = new Customer(id, name,sceneID);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit();
        return customers;
    }
}
