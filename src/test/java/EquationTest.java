import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.Statement;


import static org.junit.Assert.*;

public class EquationTest {

    @Test
    public static void checkCreateConnection() {
        boolean result = false;

        try {
            MyUtils myUtils = new MyUtils();
            Connection actual = myUtils.createConnection();
            result = actual != null;
            myUtils.closeConnection();
            System.out.println("checkCreateConnection - " + result);
        } catch (Exception var3) {
            System.out.println("checkCreateConnection - " + result);
        }
    }
    @Test
    public static void checkCloseConnection() {
        boolean result = false;

        try {
            MyUtils myUtils = new MyUtils();
            Connection actual = myUtils.createConnection();
            result = actual != null;
            myUtils.closeConnection();
            result = result && actual.isClosed();
            System.out.println("checkCloseConnection - " + result);
        } catch (Exception var3) {
            System.out.println("checkCloseConnection - " + result);
        }
    }

    @Test
    public static void checkCreateStatement() {
        boolean result = false;

        try {
            MyUtils myUtils = new MyUtils();
            myUtils.createConnection();
            Statement actual = myUtils.createStatement();
            result = actual != null;
            myUtils.closeStatement();
            myUtils.closeConnection();
            System.out.println("checkCreateStatement - " + result);
        } catch (Exception e) {
            System.out.println("checkCreateStatement - " + result);
        }
    }

    @Test
    public static void checkCloseStatement() {
        boolean result = false;

        try {
            MyUtils myUtils = new MyUtils();
            myUtils.createConnection();
            Statement actual = myUtils.createStatement();
            result = actual != null;
            myUtils.closeStatement();
            actual.close();
            result = result && actual.isClosed();
            myUtils.closeConnection();
            System.out.println("checkCloseStatement - " + result);
        } catch (Exception var3) {
            System.out.println("checkCloseStatement - " + result);
        }
    }

    @Test
    public static void checkIsValidEquation(){
        String[] equations = {"2*x+5=17", "-1.3*5/x=1.2", "2*x*x=10", "2*(x+5+x)+5=10", "17=2*x+5"};
        for(String equation : equations){
            assertTrue(equation + "should be a valid equation", Equation.isValidEquation(equation));
        }
    }

    @Test
    public static void checkIsNotValidEquation(){
        String[] equations = { "-1.3**5/x=1.2", "2*((x*x=10"};
        for(String equation : equations){
            assertFalse(equation + "should be a valid equation", Equation.isValidEquation(equation));
        }
    }

    @Test
    public static void checkCorrectRoot(){
        String equation = "2*x+5=17";
        String root = "6";
        assertTrue(root + "isn't a root of the equation " + equation, Equation.checkRoot(equation, root));
    }

    @Test
    public static void checkIncorrectRoot(){
        String equation = "2*x+5=17";
        String root = "9";
        assertFalse(root + "isn't a root of the equation " + equation, Equation.checkRoot(equation, root));
    }

    @Test
    public void testInsertEquation() {
        try {
            MyUtils myUtils = new MyUtils();
            myUtils.insertEquation("2*x+5=17");
            assertEquals("insertion failed", 1, myUtils.getEquationId("2*x+5=17"));
        } catch (Exception e) {
            System.out.println("Insertion failed");
        }
    }
    @Test
    public void testInsertRoot() {
        try {
            MyUtils myUtils = new MyUtils();
            myUtils.insertRoot("2*x+5=17", "6");
            assertEquals("insertion failed", 1, myUtils.getEquationId("2*x+5=17"));
        } catch (Exception e) {
            System.out.println("Insertion failed");
        }
    }
    @Test
    public static void checkGetAllEquationsByRoot() {
        String[] expected = {"1+(2*x)=5", "1+((1+1)*x)=5"};
        String[] actual = null;

        try {
            MyUtils myUtils = new MyUtils();
            actual = myUtils.getAllEquationsByRoot("2").toArray(new String[0]);
            assertArrayEquals(expected, actual);
        } catch (Exception e) {

        }
    }
}
