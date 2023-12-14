import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.el.StandardELContext;
import org.bson.Document;

@Stateless(name = "rentUpdateEJB")
public class rentUpdateBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

    public void rentUpdateBean(String Statementone, String Statementtwo){
        Statement stmt = null;
        try {
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();

            stmt.executeUpdate(Statementone);

            stmt.executeUpdate(Statementtwo);
            stmt.close();
        }catch (SQLException e){

        }

    }
}
