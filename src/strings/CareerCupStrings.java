package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/15/13
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class CareerCupStrings {

    public static void main(String[] args) {
        System.out.println(permutations("cat"));
        assert(permutations("cat").toString().equals("[cat, act, atc, cta, tca, tac]"));
        System.out.println(permutations("aaa"));
    }

    public static Set<String> permutations(String s) {
        Set<String> permutations = new HashSet<String>();
        if (s == null) { // error case
            return null;
        } else if (s.length() == 0) { // base case
            permutations.add("");
            return permutations;
        }
        char first = s.charAt(0); // get the first character
        String remainder = s.substring(1); // remove the first character
        Set<String> words = permutations(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                permutations.add(insertCharAt(word, first, j));
            }
        }
        return permutations;
    }

    public static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }



    public static void printPar(int l, int r, char[] str, int count) {
        if (l < 0 || r < l) return; // invalid state
        if (l == 0 && r == 0) {
            System.out.println(str); // found one, so print it
        } else {
            if (l > 0) { // try a left paren, if there are some available
                str[count] = '(';
                printPar(l - 1, r, str, count + 1);
            }
            if (r > l) { // try a right paren, if there’s a matching left
                str[count] = ')';
                printPar(l, r - 1, str, count + 1);
            }
        }
    }

    public static void printPar(int count) {
        char[] str = new char[count*2];
        printPar(count, count, str, 0);
    }

}
