import com.mongodb.client.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

@Stateless(name = "finePayEJB")
public class finePayBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

    public void finePayBean(Document loanUpdate, Document loanFilter){
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection<Document> loanCollection = database.getCollection("Loans");
        loanCollection.updateOne(loanFilter,loanUpdate);


        mongo.close();
    }
}