
import com.mongodb.client.*;
import jakarta.ejb.EJB;
import org.bson.Document;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

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

        String customer = "INSERT INTO tblStudents" +
                "(Student_ID,FName,SName,SNum,Password)" +
                "VALUES ("+request.getParameter("FName")+")";

//        registrationBean.createCustomer(customer);

    }

    public void createCustomer(Document customer) {


    }
}
