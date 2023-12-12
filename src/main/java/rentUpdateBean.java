import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.bson.Document;

@Stateless(name = "rentUpdateEJB")
public class rentUpdateBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

    public void rentUpdateBean(Document bookUpdate, Document bookFilter, Document loanCreate){
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection<Document> bookCollection = database.getCollection("Books");
        bookCollection.updateOne(bookFilter, bookUpdate);

        MongoCollection<Document> userCollection = database.getCollection("Loans");
        userCollection.insertOne(loanCreate);

        mongo.close();
    }
}
