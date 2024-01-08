import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {
    @EJB
    RegistrationBean RegistrationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    // Code responsible for adding a new customer to the database
    PrintWriter out = response.getWriter();
    out.println("Processing user registration ........");
        //declares the sql responsible for inserting the new user
        String customer = "INSERT INTO \"TBLSTUDENTS\"" +
                "(\"FNAME\",\"LNAME\",\"SNUM\",\"PWORD\")" +
                "VALUES (" +
                "'"+request.getParameter("fname")+"',"+
                "'"+request.getParameter("lname")+"',"+
                "'"+request.getParameter("snum")+"',"+
                "'"+request.getParameter("pword")+"')";
        RegistrationBean.createCustomer(customer);

}

    }