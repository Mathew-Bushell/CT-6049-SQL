import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Stateless(name = "LoginEJB")
public class loginBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;
    public String loginBean(String snum, String pword) {
        //searches for a user row based on the provided SNUM and PWORD
        String Statement = "SELECT SNUM FROM TBLSTUDENTS WHERE " +
                "SNUM = '" + snum + "' AND PWORD = '" + pword +"'";
        Statement stmt = null;
        try {
            String SNum = null;
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(Statement);

            while (result.next()){
                SNum = result.getString("SNUM");
            }
            stmt.close();
            //returns SNUM as confirmation
            return(SNum);
        } catch(SQLException e){
            e.printStackTrace();
//            return (e.toString());
        }

        return("Invalid username or password");
    }
}
