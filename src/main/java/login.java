import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    PrintWriter out = response.getWriter();
    out.println("you shouldnt be here"+"<br>");
    String SNum = request.getParameter("snum");
    String PWord = request.getParameter("pword");
    //returns the users SNUM if the password is incorrect
    String result = loginBean.loginBean(SNum, PWord);
    if (result.equals(SNum)){
                JSONArray JSON = bookBean.bookBean();
                //fetches book data and moves to books.jsp
                request.setAttribute("books",JSON);
                request.setAttribute("snum",SNum);
                out.println(JSON);
                request.getRequestDispatcher("/books.jsp").forward(request, response);
            out.println(result);
    } else {
        request.setAttribute("reply","Incorrect username or password");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
}