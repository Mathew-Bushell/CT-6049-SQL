import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
@Stateless(name = "returnEJB")
public class returnBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

    public void returnBean(String ISBN, String SNum, String LOut) {
//Updates the relevant loan item to be returned and book item to change the out value
    String StatementOne = "UPDATE TBLLOANS SET STATUS = 'Returned' WHERE SNUM = '"+SNum+"' AND ISBN = '"+ISBN+"' AND LOUT = '"+LOut+"'";
    String StatementTwo = "UPDATE TBLBOOKS SET QOUT = (QOUT - 1)WHERE ISBN = '"+ISBN+"'";
        Statement stmt = null;
        try{
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            stmt.executeUpdate(StatementOne);
            stmt.executeUpdate(StatementTwo);
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
