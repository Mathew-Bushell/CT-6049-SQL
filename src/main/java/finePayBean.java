import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

@Stateless(name = "finePayEJB")
public class finePayBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void finePayBean(Document loanUpdate, Document loanFilter){
//        MongoClient mongo = oracleClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> loanCollection = database.getCollection("Loans");
//        loanCollection.updateOne(loanFilter,loanUpdate);
//
//
//        mongo.close();

    }
}