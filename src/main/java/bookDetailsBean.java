import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.json.JSONObject;
@Stateless(name = "DetailsEJB")
public class bookDetailsBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;
    public JSONObject bookDetailsBean(String ISBN){
        //fetches specific book details and converts to JSON to return
        String Statement = "SELECT * FROM TBLBOOKS WHERE ISBN = '"+ISBN+"'";
        Statement stmt = null;
        try{
            JSONObject bookJSON = new JSONObject();
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(Statement);
            while (result.next()){
                int totalColumns = result.getMetaData().getColumnCount();
                for (int i = 1; i <= totalColumns; i++){
                    bookJSON.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
                }
            }
            stmt.close();
            return(bookJSON);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return(null);
    }
}
