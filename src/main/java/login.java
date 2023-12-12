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

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @EJB
    loginBean loginBean;
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
    out.println("you shouldnt be here"+"<br>");
    String SNum = request.getParameter("snum");
    FindIterable<Document> result = loginBean.loginBean(SNum);

    MongoCursor<Document> cursor = result.iterator();
    out.println("help");
    try {
        while (cursor.hasNext()){
            Document doc = cursor.next();
            if (request.getParameter("pword").equals(doc.getString("Password"))) {

                //sets attribute for global remember
                request.setAttribute("snum", SNum);
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
                out.println(JSON);
                cursor.close();
                request.getRequestDispatcher("/books.jsp").forward(request, response);

            } else {
                cursor.close();
                request.setAttribute("reply","Invalid username or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    } finally {
        cursor.close();
    }
}



    }