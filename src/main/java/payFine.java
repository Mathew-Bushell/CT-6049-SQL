import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "payFine", value = "/payFine")
public class payFine extends HttpServlet {

    @EJB
    finePayBean finePayBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String ISBN = request.getParameter("ISBN");
        String LOut = request.getParameter("LOut");
        String SNum = request.getParameter("SNum");
        out.println(ISBN+LOut+SNum);
        //takes the ISBN LOut and SNum and uses them to "pay the fine"
        finePayBean.finePayBean(ISBN, LOut,SNum);
        request.setAttribute("SNum",SNum);
        request.getRequestDispatcher("/profile").forward(request, response);
    }
}