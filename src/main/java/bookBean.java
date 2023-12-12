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

@Stateless(name = "BooksEJB")
public class bookBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

//    public bookFetch(){
//    }

    public FindIterable<Document> bookBean(){
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection<Document> collection = database.getCollection("Books");
        return collection.find();
    }
}