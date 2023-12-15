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

@WebServlet(name = "payFine", value = "/payFine")
public class payFine extends HttpServlet {

    @EJB
    loanFetchBean loanFetchBean;
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


        finePayBean.finePayBean(ISBN, LOut,SNum);

        request.setAttribute("SNum",SNum);
        request.getRequestDispatcher("/profile").forward(request, response);
    }
}