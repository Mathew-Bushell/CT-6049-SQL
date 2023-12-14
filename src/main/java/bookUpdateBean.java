import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

@Stateless(name = "bookUpdateEJB")
public class bookUpdateBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void bookUpdateBean(Document update, Document filter){
//        MongoClient mongo = oracleClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> collection = database.getCollection("Books");
//        collection.updateOne(filter, update);
//        mongo.close();
    }
}
