import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.bson.Document;

@Stateless(name = "userUpdateEJB")
public class userUpdateBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void userUpdateBean(Document update, Document filter){
//        MongoClient mongo = oracleClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> collection = database.getCollection("Students");
//        collection.updateOne(filter, update);
//        mongo.close();
    }
}
