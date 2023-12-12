import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Books", value = "/books")
public class books extends HttpServlet {
    @EJB
    bookBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
//        out.println("Book servlet, if you see me there is a problem");
        FindIterable<Document> books = bookBean.bookBean();
        MongoCursor<Document> bookCursor = books.iterator();
        JSONArray JSON = new JSONArray();
        try{
            while (bookCursor.hasNext()){
                Document bookDoc = bookCursor.next();

                JSONObject docjson = new JSONObject(bookDoc);
                JSON.put(docjson);

            }
        }finally{
            bookCursor.close();
        }
        request.setAttribute("books",JSON);
        request.getRequestDispatcher("/books.jsp").forward(request, response);


    }


}
