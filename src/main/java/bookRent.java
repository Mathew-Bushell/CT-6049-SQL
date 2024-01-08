import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(name = "bookRent", value = "/bookRent")
public class bookRent extends HttpServlet {
    @EJB
    rentUpdateBean rentUpdateBean;
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//      sets the current date and date two weeks from now
        String LOut = LocalDateTime.now().format(format);
        String ReturnDate =LocalDateTime.now().plusWeeks(2).format(format);
        //declares the update and insert statements
        String Statementone  = "INSERT INTO TBLLOANS (\"ISBN\",\"SNUM\",\"LOUT\",\"AFINE\",\"LRETURN\",\"STATUS\")" +
                " VALUES ('"+ISBN+"','"+SNum+"','"+LOut+"',"+0+",'"+ReturnDate+"','Rented')";
        String Statementtwo = "UPDATE TBLBOOKS SET QOUT = (QOUT + 1)WHERE ISBN= '"+ISBN+"'";
        rentUpdateBean.rentUpdateBean(Statementone, Statementtwo);
        request.setAttribute("SNum",SNum);
        request.setAttribute("ISBN",ISBN);
        request.getRequestDispatcher("/bookDetails").forward(request, response);
    }
}