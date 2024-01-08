import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless(name = "RegistrationEJB")
public class RegistrationBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

    public void createCustomer(String customer) {
        Statement stmt = null;
        try {
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            //execute the sql insert user statement
            stmt.executeUpdate(customer);
            stmt.close();
        } catch (SQLException e) {
//            System.out.println("Failure");
            e.printStackTrace();
        }
    }
}

