
import com.mongodb.client.*;
import jakarta.ejb.EJB;
import org.bson.Document;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Registration", value = "/registration")
public class Registration extends HttpServlet {
    @EJB
    RegistrationBean registrationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("Processing user registration ........");

        Document customer = new Document()
                .append("FName", request.getParameter("fname"))
                .append("LName", request.getParameter("lname"));


        registrationBean.createCustomer(customer);

    }

    public void createCustomer(Document customer) {


    }
}
