import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.json.JSONObject;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Stateless(name = "UFEJB")
public class userFetchBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;
    public JSONObject userFetchBean(String snum) {
        String Statement = "SELECT * FROM TBLSTUDENTS WHERE " +
                "SNUM = '" + snum + "'";
        Statement stmt = null;
        //fetches user details and converts to json before returning
        try {
            JSONObject JSON = new JSONObject();
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(Statement);

            while (result.next()){
                int totalColumns = result.getMetaData().getColumnCount();
                for (int i = 1; i <= totalColumns; i++){
                    JSON.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
                }
            }
            return(JSON);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return(null);
    }
}