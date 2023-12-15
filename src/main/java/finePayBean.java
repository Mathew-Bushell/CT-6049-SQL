import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless(name = "finePayEJB")
public class finePayBean {
    @EJB
    OracleClientProviderBean oracleClientProvider;

    public void finePayBean(String ISBN, String LOut, String SNum){
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