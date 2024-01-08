import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
@Stateless(name = "ProfileEJB")
public class profileBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;
    public JSONArray profileBean(String target, String filter){
        //fetches a users loans and the titles of the loaned books
        String Statement = "SELECT TBLLOANS.*, TBLBOOKS.TITLE FROM TBLLOANS JOIN" +
                " TBLBOOKS ON TBLLOANS.ISBN=TBLBOOKS.ISBN WHERE " + filter+" = '"+target+"'";
        java.sql.Statement stmt = null;
        try {
            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(Statement);
            //converts the results into a JSON
            JSONArray JSON = new JSONArray();
            while (result.next()){
                int totalColumns = result.getMetaData().getColumnCount();
                JSONObject bookJSON = new JSONObject();

                for (int i = 1; i <= totalColumns; i++){
                    bookJSON.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
                }

                JSON.put(bookJSON);
            }
            stmt.close();
            return(JSON);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return (null);
    }
}