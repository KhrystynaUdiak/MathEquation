import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    private Connection connection;
    private Statement statement;

    public Connection createConnection() {
        try{
            DriverManager.registerDriver(new org.h2.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_equations", "root", "root");
            if(!connection.isClosed()) {
                System.out.println("Connected to DB");
            }
        } catch(SQLException e){
            System.out.println("Problems with driver downloading");
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }

    public void closeStatement() throws SQLException {
        statement.close();
    }

    public void insertEquation(String equation)  {
        if (Equation.isValidEquation(equation)) {
            try {
                statement.execute("INSERT INTO equations (equation) VALUES ('" + equation + "');");
            } catch (SQLException e) {
                System.out.println("Failed to insert data");
                e.printStackTrace();
            }
        }
    }

    public void insertRoot(String equation, String root)  {
        if (Equation.checkRoot(equation, root)) {
            try {

                String sql =
                        "UPDATE Equations " +
                                "  SET root = ? " +
                                "WHERE equation = ?";

                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, root);
                pstmt.setString(2, equation);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to insert  root data");
            }
        }
    }

    public List<String> getAllEquationsByRoot(String root)  {
        List<String> allEquations = new ArrayList<>();
        try{
            ResultSet result = statement.executeQuery("SELECT equation FROM Equations where root = '"+ root + "';");
            while (result.next()) {
             allEquations.add(result.getString("equation"));
            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Failed to get data");
        }
        return allEquations;
    }

    public int getEquationId(String equation) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT id FROM equations WHERE equation = '"+ equation +"';");
        int id = -1;
        while (result.next()){
            id = result.getInt("id");
        }
        return id;
    }
}
