import java.sql.*;
import java.util.Date;
import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        Scanner scan = new Scanner(System.in);
        String cont;
        System.out.print("Enter your MySql details \nUsername : ");
        DBhandling.username = scan.nextLine();
        System.out.print("\nPassword : ");
        DBhandling.password = scan.nextLine();

        DBhandling obj = DBhandling.getInstance();

        do {
            System.out.println("Options : ");
            System.out.println("1...Calculate");
            System.out.println("2...Read all operations from table");
            System.out.println("3...Read all operations of specific operator");
            System.out.println("4...Exit");
            System.out.println("Enter your choice:");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("\nEnter expression (Space seperated input! e.g. 2 + 3 ) : ");
                    String str = scan.nextLine();
                    String[] ch = str.split(" ");
                    int result = 0, a = 0, b = 0;
                    char operand = '\0';
                    if (ch.length == 1) {
                        a = Integer.parseInt(ch[0]);
                        result = Operations.absolute(a);
                        operand = 'A';
                    }
                    else if (ch.length == 3)
                    {
                        a = Integer.parseInt(ch[0]);
                        b = Integer.parseInt(ch[2]);
                        operand = ch[1].charAt(0);
                        if (operand == '+')
                            result = Operations.add(a, b);
                        else if (operand == '-')
                            result = Operations.sub(a, b);
                        else if (operand == '*')
                            result = Operations.mul(a, b);
                        else if (operand == '/')
                            result = Operations.div(a, b);
                        else if (operand == '^')
                            result = Operations.pow(a, b);
                        else if (operand == '%')
                            result = Operations.mod(a, b);
                        else if (operand == 'M')
                            result = Operations.maximum(a, b);
                        else if (operand == 'm')
                            result = Operations.minimum(a, b);
                        else
                            System.out.println("Invalid operand!!");
                    }
                    System.out.println("Result = " + result);

                    try {
                        obj.insert(a, b, operand, result);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    obj.display();
                    break;

                case 3:
                    obj.displaySpecific();
                    break;
                case 4:
                    return;
                default:
                System.out.println("Invalid choice!");
                break;
            }
            System.out.println("Enter \"yes\" if you want to continue ");
            cont = scan.nextLine();
        }while(cont.equalsIgnoreCase("yes"));
    }
}