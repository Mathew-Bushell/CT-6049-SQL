import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

@Stateless(name = "returnEJB")
public class returnBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void returnBean(Document loanUpdate, Document loanFilter, Document bookUpdate, Document bookFilter){
//        MongoClient mongo = oracleClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> loanCollection = database.getCollection("Loans");
//        MongoCollection<Document> bookCollection = database.getCollection("Books");
//        loanCollection.updateOne(loanFilter,loanUpdate);
//        bookCollection.updateOne(bookFilter, bookUpdate);
//
//        mongo.close();
    }
}
