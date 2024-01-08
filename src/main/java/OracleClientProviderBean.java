import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Singleton(name = "OracleClientProviderEJB")
public class OracleClientProviderBean {
    public OracleClientProviderBean() {
    }
    private Connection oracleClient = null;
    @Lock(LockType.READ)
    public Connection getOracleClient(){
        return oracleClient;
    }
    @PostConstruct
    public void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver now?");
            e.printStackTrace();
        }
        try {
            oracleClient = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//oracle.glos.ac.uk:1521/orclpdb.chelt.local", "s4005098",
                    "M4th3w");
            if (oracleClient != null) {
                System.out.println("You are now connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }
}
