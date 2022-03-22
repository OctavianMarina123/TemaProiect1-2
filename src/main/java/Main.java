import domain.Artist;
import domain.Customer;
import domain.Scene;
import repository.JdbcUtils;
import repository.RepoArtists;
import repository.RepoCustomer;
import repository.RepoScene;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import service.ServiceACS;


import java.io.FileReader;
import java.io.IOException;
import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {


        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        /*
        RepoArtists repoArtists=new RepoArtists(serverProps);
        //repoCustomer.delete(10);
        repoArtists.size();
        */
        ServiceACS serviceACS=new ServiceACS(serverProps);
        System.out.println(serviceACS.findAllArtists());
        System.out.println(serviceACS.findAllCustomers());
        System.out.println(serviceACS.findAllScenes());
        System.out.println(serviceACS.artistFindById(2));
        System.out.println(serviceACS.customerFindById(2));
        System.out.println(serviceACS.sceneFindById(2));

    }
}
