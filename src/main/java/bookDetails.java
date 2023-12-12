import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name="bookDetails", value="/bookDetails")
public class bookDetails extends HttpServlet{
    @EJB
    bookDetailsBean bookDetailsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String ISBN = request.getParameter("ISBN");
        String SNum = request.getParameter("SNum");
        FindIterable<Document> result = bookDetailsBean.bookDetailsBean(ISBN);
        MongoCursor<Document> cursor = result.iterator();

        try{
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                JSONObject JSONBook = new JSONObject(doc);
                request.setAttribute("book", JSONBook);
                request.setAttribute("SNum", SNum);
                request.getRequestDispatcher("/booksDetails.jsp").forward(request,response);

            }
        }finally{
            cursor.close();
        }




    }



}