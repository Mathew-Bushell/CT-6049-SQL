import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        //takes the books ISBN and uses it to retrive the books details
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String ISBN = request.getParameter("ISBN");
        String SNum = request.getParameter("SNum");
        JSONObject  result = bookDetailsBean.bookDetailsBean(ISBN);
        request.setAttribute("book", result);
        request.setAttribute("SNum", SNum);
        request.getRequestDispatcher("/booksDetails.jsp").forward(request,response);
    }
}