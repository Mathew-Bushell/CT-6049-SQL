import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.bson.Document;

@Stateless(name = "RegistrationEJB")
public class RegistrationBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

    public void createCustomer(Document customer) {
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection collection = database.getCollection("Students");
        collection.insertOne(customer);
        mongo.close();
    }
}

