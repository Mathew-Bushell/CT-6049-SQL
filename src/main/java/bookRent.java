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
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "bookRent", value = "/bookRent")
public class bookRent extends HttpServlet {
    @EJB
    loginBean loginBean;
    @EJB
    fetchBookBean fetchBookBean;
    @EJB
    rentUpdateBean rentUpdateBean;
    @EJB
    bookBean bookBean;
//    @EJB
//    userUpdateBean userUpdateBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String SNum = request.getParameter("SNum");
        out.println(SNum+"<br>");
        String ISBN = request.getParameter("ISBN");
        FindIterable<Document> bookResult = fetchBookBean.fetchBookBean(ISBN);
        MongoCursor<Document> bookCursor = bookResult.iterator();
        Integer NewQOut = 0;
        ObjectId bookID = new ObjectId();

        try{
           while(bookCursor.hasNext()){
                Document bookDoc = bookCursor.next();

                bookID = bookDoc.getObjectId("_id");
                Integer QOut = bookDoc.getInteger("QOut");
                NewQOut = (QOut + 1);



           }
        }finally {
            bookCursor.close();
        }

        Document bookUpdate = new Document("$set", new Document("QOut",NewQOut));
        Document bookFilter = new Document("_id", bookID);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Document loanDocument = new Document();
        String LOut = LocalDateTime.now().format(format);
        String ReturnDate =LocalDateTime.now().plusWeeks(2).format(format);
        loanDocument.append("ISBN", ISBN)
                .append("SNum", SNum)
                .append("LOut", LOut)
                .append("LReturn", ReturnDate)
                .append("Fine", 0)
                .append("Status","Rented");
//        Document userUpdate = new Document("$set", new Document("Loans",loanDocument));
//        FindIterable<Document> userResult = loginBean.loginUser(SNum);
//        MongoCursor<Document> userCursor = userResult.iterator();

//    ----made redundant with the creation of the loan collection----

//        ObjectId userID = new ObjectId();
//        try{
//            while(userCursor.hasNext()){
//                Document userDoc = userCursor.next();
//                userID = userDoc.getObjectId("_id");
//
////                userUpdateBean.userUpdateBean(userUpdate, userFilter);
//            }
//        }finally{
//                   userCursor.close();
//        }
//        Document userFilter = new Document("_id", userID);



        rentUpdateBean.rentUpdateBean(bookUpdate, bookFilter, loanDocument);
        request.setAttribute("SNum",SNum);
        request.setAttribute("ISBN",ISBN);
        request.getRequestDispatcher("/bookDetails").forward(request, response);
    }
}