import java.io.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
//import com.mongodb.client.*;
//import org.bson.Document;

@WebServlet(name = "Return", value = "/Return")
public class Return extends HttpServlet {

    @EJB
    fetchBookBean fetchBookBean;
    @EJB
    loanFetchBean loanFetchBean;
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

        returnBean.returnBean(ISBN, SNum, LOut);

        request.setAttribute("SNum",SNum);
        request.getRequestDispatcher("/profile").forward(request, response);
    }
}