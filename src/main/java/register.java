import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import jakarta.ejb.EJB;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {

    @EJB
    RegistrationBean registrationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    out.println("Processing user registration ........");
    Document customer = new Document()
            .append("FName", request.getParameter("fname"))
            .append("LName", request.getParameter("lname"))
            .append("SNum", request.getParameter("snum"))
            .append("Password", request.getParameter("pword"))
            .append("OFine", (0))
            .append("PFine", (0));
    registrationBean.createCustomer(customer);
}
//public void createCustomer(Document student) {
//    MongoClient mongo = MongoClients.create();
//    MongoDatabase database = mongo.getDatabase("LibraryDB");
//    MongoCollection collection = database.getCollection("Students");
//    collection.insertOne(student);
//}
//
    }