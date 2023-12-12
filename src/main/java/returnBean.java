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

@Stateless(name = "returnEJB")
public class returnBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

    public void returnBean(Document loanUpdate, Document loanFilter, Document bookUpdate, Document bookFilter){
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection<Document> loanCollection = database.getCollection("Loans");
        MongoCollection<Document> bookCollection = database.getCollection("Books");
        loanCollection.updateOne(loanFilter,loanUpdate);
        bookCollection.updateOne(bookFilter, bookUpdate);

        mongo.close();
    }
}
