import java.util.*;

class Operations
{

        public static int add ( int op1, int op2) {
            return op1 + op2;
        }
        public static int sub ( int op1, int op2){
        return op1 - op2;
    }

        public static int mul ( int op1, int op2){
        return op1 * op2;
    }

        public static int div ( int op1, int op2){
            try {
                return op1 / op2;
            }
            catch(ArithmeticException e) {
            System.out.println(e.getMessage());
            return -1;
            }
    }

        public static int pow ( int op1, int op2){
        return (int) Math.pow(op1, op2);
    }

        public static int mod ( int op1, int op2){
            try {
                return op1 % op2;
            }catch(ArithmeticException e) {
                System.out.println(e.getMessage());
                return -1;
            }
    }

        public static int minimum ( int op1, int op2){
        if (op1 < op2)
            return op1;
        else
            return op2;
    }

        public static int maximum ( int op1, int op2){
        if (op1 > op2)
            return op1;
        else
            return op2;
    }

        public static int absolute ( int op){
        if (op < 0)
            return op * -1;
        else return op;
    }
}
