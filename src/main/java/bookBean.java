import com.mongodb.client.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

@Stateless(name = "BooksEJB")
public class bookBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

//    public bookFetch(){
//    }

    public JSONArray bookBean() {
        String Statement = "SELECT * FROM TBLBOOKS";
        Statement stmt = null;
        try {

            Connection con = oracleClientProvider.getOracleClient();
            stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(Statement);
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



        } catch (SQLException e) {
            e.printStackTrace();

        }
        return (null);

    }
}