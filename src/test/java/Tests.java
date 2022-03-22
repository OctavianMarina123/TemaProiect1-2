
import domain.Scene;
import exceptions.RepoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import service.ServiceACS;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    @Test
    @DisplayName("First Test")
    public void testExample() throws IOException, RepoException {
        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        ServiceACS service=new ServiceACS(serverProps);
        assertEquals("Dimitri vegas",service.artistFindById(2).getName());
        assertEquals("Alex Alexandrescu",service.customerFindById(1).getName());
    }

    @Test
    @DisplayName("SecondTest")
    public void testExemple2() throws IOException {
        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        ServiceACS service=new ServiceACS(serverProps);
        assertEquals(1,service.artistsSize(),"Numerele ar trebui sa fie egale!");
        assertEquals(9,service.customersSize(),"Numerele ar trebui sa fie egale!");
    }

    @Test
    @DisplayName("Test3")
    public void testExample3() throws Exception {
        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        ServiceACS service=new ServiceACS(serverProps);
        Scene scene=new Scene("scene",120);
        service.sceneAdd(scene);

    }

    @Test
    @DisplayName("Test4")
    public void testExample4() throws Exception {
        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        ServiceACS service=new ServiceACS(serverProps);
        Scene scene=new Scene("scene",120);
        service.sceneDelete(19);
    }

    @Test
    @DisplayName("Test5")
    public void testExample5() throws Exception {
        Properties serverProps=new Properties();
        serverProps.load(new FileReader("bd.config"));
        ServiceACS service=new ServiceACS(serverProps);
        Scene scene=new Scene("scenee",120);
        service.sceneAdd(scene);
    }

}
