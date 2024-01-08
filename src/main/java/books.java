import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
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

        // used to go to books from nave bar
        PrintWriter out = response.getWriter();
        JSONArray JSON = bookBean.bookBean();

        request.setAttribute("books",JSON);
        out.println(JSON);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
