import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(value = "/table-view")
public class Endpoints extends HttpServlet {
  private static final String TABLE_HEAD =
      "<table width=\"100%\" border=\"1\" align=\"center\">"
          + "<tr bgcolor=\"#949494\">"
          + "<th>Param Name</th>"
          + "<th>Param Value(s)</th>"
          + "</tr>";

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    PrintWriter out = response.getWriter();

    out.println(createFirstPartWithList(request));
    out.println(TABLE_HEAD);

    request.getParameterMap()
        .forEach((paramName, paramValues) -> {
          out.print("<tr><td>" + paramName + "</td><td>");
          out.println(String.join("<li>", paramValues));
          out.println("</ul>");
        });

    out.println("</tr></table></body></html>");
  }

  private String createFirstPartWithList(HttpServletRequest request) {
    return
        "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">"
            + "<html>"
            + "<head><title>Using " + request.getMethod() + " Method to Read Form Data</title></head>"
            + "<body bgcolor=\"#f0f0f0\">"
            + "<h1 align=\"center\">Using " + request.getMethod() + " Method to Read Form Data</h1>"
            + "<ul>"
            + " <li><b>First Name</b>: " + request.getParameter("first_name")
            + " <li><b>Last Name</b>: " + request.getParameter("last_name")
            + "<li><b>Gender:</b> " + request.getParameter("gender") + "</li>"
            + " <li><b>Student : </b> " + handleCheckBox(request.getParameter("student"))
            + " <li><b>Employee: </b> " + handleCheckBox(request.getParameter("employee"))
            + " <li><b>Other: </b> " + handleCheckBox(request.getParameter("other"))
            + "</ul>";
  }

  private String handleCheckBox(String value) {
    return Optional.ofNullable(value)
        .map(v -> "yes")
        .orElse("no");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.doGet(request, response);
  }
}
