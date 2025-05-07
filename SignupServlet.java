
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    String number = req.getParameter("number");

	    String host = System.getenv("DB_HOST");
	    String port = System.getenv("DB_PORT");
	    String dbName = System.getenv("DB_NAME");
	    String user = System.getenv("DB_USER");
	    String dbPassword = System.getenv("DB_PASSWORD");

	    String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

	    resp.setContentType("text/plain");
	    PrintWriter out = resp.getWriter();

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
	            out.print("Signup successful");
	        } else {
	            out.print("Signup failed");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(out);
	    }
	}
}
