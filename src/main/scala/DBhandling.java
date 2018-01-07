import java.sql.*;
import java.util.Date;
import java.util.*;

class DBhandling
{
    private static DBhandling dbInstance = null;
    static String query;
    static String username;
    static String password;
    static Connection con;
    static Statement stmt;
    static ResultSet rs;


    private DBhandling()
    {
        createDb();
        createTable();
    }

    public static DBhandling getInstance()
    {
        if(dbInstance == null)
        {
            dbInstance = new DBhandling();
        }
        return dbInstance;
    }

    private void createDb()
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306",username, password);
            stmt = con.createStatement();
            stmt.executeUpdate("Create database NancyJain");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            //System.out.println("Exception: "+e.getMessage());
            //e.printStackTrace();
            System.out.print("Unable to connect");
        }
    }
    private void createTable()
    {
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NancyJain",username, password);
            stmt = con.createStatement();
            //query = "DROP TABLE IF EXISTS calculator;";
            //stmt.execute(query);
            query = "Create table calculator (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + " timestamp   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "left_operand int , right_operand int,"
                    + "operator varchar(1) , result int); ";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.print("Unable to connect");
            // TODO Auto-generated catch block
            //e.printStackTrace();
          //  System.out.println("Exception: "+e.getMessage());
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Exception: "+e.getMessage());
        }
    }
    public void insert(int a, int b,char op, int result)
    {
        query = "insert into calculator(left_operand, right_operand, operator, result) values(" +a+ "," +b+ ",'" + op+"'" + "," +result+ ");";
        //System.out.println(query);
        try {
            stmt = con.createStatement();
            stmt.execute(query);
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Exception: "+e.getMessage());
        }
    }
    public void display()
    {
        try
        {

            stmt = con.createStatement();
            query = "select * from calculator";
            rs = stmt.executeQuery(query);
            System.out.format("\n%10S %30S %30S %15S %15S %10S \n", "ID","TIMESTAMP","RIGHT_OPERAND","LEFT_OPERAND","OPERATOR","RESULT" );
            while(rs.next())
            {
                System.out.format("%10S %40S %15S %15S %15S %15S \n", rs.getInt(1)+ "",rs.getTimestamp(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getString(5)+"",rs.getInt(6)+"" );
               // System.out.println("\n" +rs.getInt(1) + " \t " + rs.getTimestamp(2) +" \t " + rs.getInt(3) + " \t " + rs.getInt(4) + " \t " + rs.getString(5) + " \t " + rs.getInt(6));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        try {
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void displaySpecific()
    {

        Scanner scan = new Scanner(System.in);
        System.out.println("Symbols for operators are:\n Add -> + \n Subtract -> -\n Multiplication -> * \n Division -> / \n Power -> ^ \n Absolute -> A \n Modulus -> % \n Maximum of two -> M \n Minimum of two -> m");
        System.out.print("Enter symbol for operator : ");
        char opr = scan.next().charAt(0);
        try
        {
            stmt = con.createStatement();
            query = "select * from calculator where operator = '" + opr + "';";
            System.out.println(query);
            rs = stmt.executeQuery(query);
           // System.out.println("ID \t TIMESTAMP \t LEFT_OPERAND \t RIGHT_OPERAND \t OPERATOR \t RESULT");
            System.out.format("\n%10S %30S %30S %15S %15S %10S \n", "ID","TIMESTAMP","RIGHT_OPERAND","LEFT_OPERAND","OPERATOR","RESULT" );
            while(rs.next())
            {
                System.out.format("%10S %40S %15S %15S %15S %15S \n", rs.getInt(1)+ "",rs.getTimestamp(2)+"",rs.getInt(3)+"",rs.getInt(4)+"",rs.getString(5)+"",rs.getInt(6)+"" );

                // System.out.println("\n" +rs.getInt(1) + " \t " + rs.getTimestamp(2) +" \t " + rs.getInt(3) + " \t " + rs.getInt(4) + " \t " + rs.getString(5) + " \t " + rs.getInt(6));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        try {
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        con.close();
    }
}