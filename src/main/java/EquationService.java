import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class EquationService {
    public static void main(String[] args) throws IOException, SQLException {//1+(2*x)=5 1+((1+1)*x)=5
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyUtils myUtils = new MyUtils();
        myUtils.createConnection();
        myUtils.createStatement();

        System.out.println("Print your equation");

        String equation = reader.readLine();

        myUtils.insertEquation(equation);

        System.out.println("Print root of the equation");
        String root = reader.readLine();

        myUtils.insertRoot(equation, root);

        System.out.println(myUtils.getAllEquationsByRoot("2"));
        myUtils.closeStatement();
        myUtils.closeConnection();

    }
}
