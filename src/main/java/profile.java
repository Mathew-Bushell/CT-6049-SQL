import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "profile", value = "/profile")
public class profile extends HttpServlet{
    @EJB
    profileBean profileBean;
    @EJB
    userFetchBean userFetchBean;
    @EJB
    bookBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("you shouldnt be here"+"<br>");
        String SNum = request.getParameter("SNum");
        out.println(SNum);
        JSONArray loanResult = profileBean.profileBean(SNum,"SNUM");
        out.println(loanResult);
        request.setAttribute("loans",loanResult);

        JSONObject userResult = userFetchBean.userFetchBean(SNum);



        request.setAttribute("SNum",userResult.getString("SNUM"));
        request.setAttribute("FName",userResult.getString("FNAME"));
        request.setAttribute("LName",userResult.getString("LNAME"));
        request.setAttribute("Password",userResult.getString("PWORD"));



        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
