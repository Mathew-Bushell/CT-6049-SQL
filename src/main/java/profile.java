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
    loginBean loginBean;
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
        FindIterable<Document> loanResult = profileBean.profileBean(SNum,"SNum");
        FindIterable<Document> bookResult = bookBean.bookBean();
        FindIterable<Document> userResult = loginBean.loginBean(SNum);
        MongoCursor<Document> userCursor = userResult.iterator();

        try{
            while(userCursor.hasNext()){
                Document userDoc = userCursor.next();

                request.setAttribute("SNum",userDoc.getString("SNum"));
                request.setAttribute("FName",userDoc.getString("FName"));
                request.setAttribute("LName",userDoc.getString("LName"));
                request.setAttribute("Password",userDoc.getString("Password"));

            }
        }finally{userCursor.close();}
        MongoCursor<Document> loanCursor = loanResult.iterator();
        JSONArray loanJSON = new JSONArray();

        ArrayList<String> bookTitles = new ArrayList<String>();
        try{
            while (loanCursor.hasNext()){
                Document loanDoc = loanCursor.next();

                JSONObject docjson = new JSONObject(loanDoc);
                loanJSON.put(docjson);
                out.println(docjson+"<br>");
                String LoanISBN = docjson.getString("ISBN");
                MongoCursor<Document> bookCursor = bookResult.cursor();
                try{
                    while(bookCursor.hasNext()) {
                        Document bookDoc = bookCursor.next();

                        if (bookDoc.getString("ISBN").equals(LoanISBN)){
                            bookTitles.add(bookDoc.getString("Title"));
                        }
                    }
                }finally {
                    bookCursor.close();
                }


            }
        }finally{
            loanCursor.close();
        }
        request.setAttribute("loans",loanJSON);
        request.setAttribute("titles",bookTitles);
        out.println(loanJSON+"<br>");
        out.println(SNum+"<br>");



        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
