import java.io.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
//import com.mongodb.client.*;
//import org.bson.Document;

@WebServlet(name = "Return", value = "/Return")
public class Return extends HttpServlet {

    @EJB
    fetchBookBean fetchBookBean;
    @EJB
    loanFetchBean loanFetchBean;
    @EJB
    returnBean returnBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String ISBN = request.getParameter("ISBN");
        String SNum = request.getParameter("SNum");
        String LOut = request.getParameter("LOut");

        FindIterable<Document> bookResult = fetchBookBean.fetchBookBean(ISBN);
        MongoCursor<Document> bookCursor = bookResult.iterator();
        Integer NewQOut = 0;
        ObjectId bookID = new ObjectId();
        try{
            while(bookCursor.hasNext()){
                Document bookDoc = bookCursor.next();

                bookID = bookDoc.getObjectId("_id");
                Integer QOut = bookDoc.getInteger("QOut");
                NewQOut = (QOut - 1);
            }
        }finally {
            bookCursor.close();
        }

        FindIterable<Document> loanResult = loanFetchBean.loanFetchBean(ISBN, "ISBN", LOut, "LOut");
        MongoCursor<Document> loanCursor = loanResult.iterator();

        ObjectId loanID = new ObjectId();
        try{
            while(loanCursor.hasNext()){
                Document loanDoc = loanCursor.next();

                loanID = loanDoc.getObjectId("_id");

            }
        }finally {
            loanCursor.close();
        }

        Document bookUpdate = new Document("$set", new Document("QOut",NewQOut));
        Document bookFilter = new Document("_id", bookID);

        Document loanUpdate = new Document("$set", new Document("Status", "Returned"));
        Document loanFilter = new Document("_id", loanID);
        out.println(bookID+"<br>"+loanID);
        returnBean.returnBean(loanUpdate, loanFilter, bookUpdate, bookFilter);

    }
}