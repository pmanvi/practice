package strings;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/8/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringProblems {

    public static void main(String[] args) {

        //System.out.println(isAnagram("teacher","cheater"));
        System.out.println(getCombinations("123"));
    }

    public static boolean isRotation(String s1,String s2) {
        return (s1.length() == s2.length()) && (s1+s1).contains(s2);
    }

    static Set<String> set1 = new HashSet<String>();


    public static Set<String> getCombinations(String s){

        Set<String> set = new HashSet<String>();
        char[] array = s.toCharArray();

        for(int i=0;i<array.length;i++){

        }


        return set;

    }



    public static boolean isAnagram(String s1,String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return (s1.length() == s2.length()) && Arrays.equals(ch1,ch2);
    }



}
