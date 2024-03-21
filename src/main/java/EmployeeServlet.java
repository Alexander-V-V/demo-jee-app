import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(value = "/employees")
public class EmployeeServlet extends HttpServlet {

  public static final String PLACEHOLDER = "__PLACEHOLDER__";
  public static final String TEMPLATE = "<!DOCTYPE html>"
      + "<html lang=\"en\">"
      + "<head>"
      + "    <meta charset=\"UTF-8\">"
      + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
      + "    <title>Emplayees</title>"
      + "    <style>"
      + "        table {"
      + "            border-collapse: collapse;"
      + "            width: 100%;"
      + "        }"
      + "        th, td {"
      + "            border: 1px solid #dddddd;"
      + "            text-align: left;"
      + "            padding: 8px;"
      + "        }"
      + "        th {"
      + "            background-color: #f2f2f2;"
      + "        }"
      + "    </style>"
      + "</head>"
      + "<body>"
      + "    <h2>Employees</h2>"
      + "    <table>"
      + "        <thead>"
      + "            <tr>"
      + "                <th>Name</th>"
      + "                <th>Last Name</th>"
      + "                <th>Age</th>"
      + "                <th>Gender</th>"
      + "            </tr>"
      + "        </thead>"
      + "        <tbody>"
      + PLACEHOLDER
      + "        </tbody>"
      + "    </table>"
      + "</body>"
      + "</html>";


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String employeesRows = EmployeeDao.findByLastName(request.getParameter("last_name"))
        .stream()
        .map(Employee::toString)
        .collect(Collectors.joining());

    PrintWriter out = response.getWriter();
    out.println((TEMPLATE.replace(PLACEHOLDER, employeesRows)));
  }

}
