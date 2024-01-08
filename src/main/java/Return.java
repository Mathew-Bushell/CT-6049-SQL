import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "Return", value = "/Return")
public class Return extends HttpServlet {
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
        //takes the ISBN LOut and SNum and uses them to "return the book"
        returnBean.returnBean(ISBN, SNum, LOut);
        request.setAttribute("SNum",SNum);
        request.getRequestDispatcher("/profile").forward(request, response);
    }
}