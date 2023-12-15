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

@WebServlet(name = "Report", value = "/Report")
public class Report extends HttpServlet {
    @EJB
    profileBean profileBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String SNum = request.getParameter("SNum");
        String Month = request.getParameter("Month");

        JSONArray loanResult = profileBean.profileBean(SNum, "SNum");
        Integer TFine = 0;
        Integer TPFine = 0;
        Integer TBRented = 0;
        Integer TBReturned = 0;
        Integer TBOverdue = 0;
        if (loanResult != null) {
            if (!loanResult.isEmpty()) {
                for (int i = 0; i < loanResult.length(); i++) {
                    JSONObject loanJSON = loanResult.getJSONObject(i);
                    String Date = loanJSON.getString("LOUT");
                    String[] SplitDate = Date.split("-");
//                    out.println(SplitDate[1] + "<br>");
                    if (SplitDate[1].equals(Month)) {
                        String Status = loanJSON.getString("STATUS");
//                        out.println(Status + "<br>");
                        if (Status.equals("Returned")) {
                            TPFine = TPFine + loanJSON.getInt("AFINE");
                            TFine = TFine + loanJSON.getInt("AFINE");
                            TBReturned = TBReturned + 1;
                            TBRented = TBRented + 1;
                        } else if (Status.equals("Paid")) {
                            TPFine = TPFine + loanJSON.getInt("AFINE");
                            TFine = TFine + loanJSON.getInt("AFINE");
                            TBRented = TBRented + 1;
                        } else if (Status.equals("Overdue")) {
                            TFine = TFine + loanJSON.getInt("AFINE");
                            TBOverdue = TBOverdue + 1;
                            TBRented = TBRented + 1;
                        } else {
                            TBRented = TBRented + 1;
                        }
                    }
                }
            }


            out.println("<h3>Your report for month " + Month + "</h3><br>" +
                    "You rented " + TBRented + " books, returned " + TBReturned + " books and have " + TBOverdue + " books overdue. <br>" +
                    "You accrued a total fine of £" + TFine + " and have paid £" + TPFine + " of it leaving £" + (TFine - TPFine) + " left to be paid.");


        }
    }
}
