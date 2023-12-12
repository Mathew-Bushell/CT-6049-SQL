import com.mongodb.client.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;


@Stateless(name = "ProfileEJB")
public class profileBean {
    @EJB
    MongoClientProviderBean mongoClientProviderBean;

    public FindIterable<Document> profileBean(String target, String filter){
        MongoClient mongo = mongoClientProviderBean.getMongoClient();
        MongoDatabase database = mongo.getDatabase("LibraryDB");
        MongoCollection<Document> collection = database.getCollection("Loans");
        return collection.find(eq(filter,target));


    }


}