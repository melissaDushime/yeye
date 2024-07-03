package students;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/index") // Map to URL pattern "/index"
public class studentregservlet extends HttpServlet {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studentsmanagement";
    private static final String DB_USER = "postgres"; // Replace with your actual username
    private static final String DB_PASSWORD = "Patience123@"; // Replace with your actual password

    @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    Credentials credentials = new Credentials();
    credentials.setId(25708);
    credentials.setFirstName("Ishimwe");
    credentials.setLastName("Patience");

    request.setAttribute("credentials", credentials);

    request.getRequestDispatcher("/index.jsp").forward(request, response);
}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String submitAction = request.getParameter("submitAction");

        if (submitAction != null && submitAction.equals("insert")) {
            
            String id = request.getParameter("id");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            try (Connection conn = getConnection()) {
                String sql = "INSERT INTO credentials (id, name1, name2) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(id));
                stmt.setString(2, firstName);
                stmt.setString(3, lastName);
                stmt.executeUpdate();

                // Handle successful insertion (e.g., redirect to success page or display confirmation message)
                // Consider redirecting to display.jsp to show updated data
                // response.sendRedirect("success.jsp"); // or another approach

                request.setAttribute("insertionSuccess", true); // Flag for JSP
            } catch (SQLException e) {
                e.printStackTrace(); // Implement proper error handling (logging, user feedback)
                request.setAttribute("insertionError", true); // Flag for JSP
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(studentregservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // After processing (GET or POST), forward to JSP again
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // Load the driver explicitly
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // /*Basic*/ connection for now
    }
}