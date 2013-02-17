package algos;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/9/13
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class StandardUtils {
    public static void main(String[] args) {
        fibonacci(4);
        Runtime.getRuntime();
    }
    public static int fibonacci(int i) {
        //System.out.println(i);
        if (i < 0)
            return 0;
        switch(i) {
            case 0:
                return 1;
            case 1:
                return 1;
            default:
                return fibonacci(i-1) + fibonacci(i - 2);
        }
    }
}
