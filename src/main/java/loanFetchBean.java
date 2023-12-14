import com.mongodb.client.*;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import static com.mongodb.client.model.Filters.*;


@Stateless(name = "loanFetchEJB")
public class loanFetchBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public FindIterable<Document> loanFetchBean(String targetOne, String filterOne, String targetTwo, String filterTwo){
//        MongoClient mongo = oracleClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> collection = database.getCollection("Loans");
//        return collection.find(and(eq(filterOne,targetOne),eq(filterTwo, targetTwo)));
        return(null);

    }

//    public FindIterable<Document> loanFetchBean(String target, String filter){
//        MongoClient mongo = mongoClientProviderBean.getMongoClient();
//        MongoDatabase database = mongo.getDatabase("LibraryDB");
//        MongoCollection<Document> collection = database.getCollection("Loans");
//        return collection.find(eq(filter,target));
//
//
//    }


}