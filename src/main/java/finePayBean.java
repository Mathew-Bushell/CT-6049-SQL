import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
@Stateless(name = "finePayEJB")
public class finePayBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

    public void finePayBean(String ISBN, String LOut, String SNum){
        //updates loan status to paid
        String Statement = "UPDATE TBLLOANS SET STATUS = 'Paid' WHERE SNUM = '"+SNum+"' AND ISBN = '"+ISBN+"' AND LOUT = '"+LOut+"'";
        java.sql.Statement stmt = null;
        try{
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            stmt.executeUpdate(Statement);

            stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}