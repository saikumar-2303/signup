
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String number = request.getParameter("number");

        String url = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8777200";
        String user = "sql8777200";
        String dbPassword = "zAqZXHjs4E"; // Replace with your actual password

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, dbPassword);

            String query = "INSERT INTO customer (name, email, password, number) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, number);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                out.print("Signup successful!");
            } else {
                out.print("Signup failed.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
