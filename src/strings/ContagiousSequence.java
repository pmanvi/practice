package strings;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 1/10/13
 * Time: 1:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContagiousSequence {

    public static int getMaxSum(int[] a) {

        int maxSum=0;
        int sum=0;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(maxSum <sum){
                maxSum = sum;
            } else if(sum<0){
                sum=0;
            }
        }
        return maxSum;
    }


    public static String lcsRecursive(String a,String b) {
        String x;
        String y;


        int alen=a.length();
        int blen=b.length();
        if (alen==0 || blen==0) {
            return "";
        }
        else if (a.charAt(alen-1)==b.charAt(blen-1)){
            return lcs(a.substring(0,alen-1),b.substring(0,blen-1)) + a.charAt(alen-1);
        }
        else {
            x=lcs(a,b.substring(0,blen-1));
            y=lcs(a.substring(0,alen-1),b);
        }
        return (x.length()>y.length()) ? x : y;
    }


    public static String lcs(String a, String b) {

        HashMap map;
        ArrayList list;
        int[][] lengths = new int[a.length()+1][b.length()+1];
        // row 0 and column 0 are initialized to 0 already
        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }

    static int maxSubArraySum(int a[])  {
        int size = a.length;
        int max_so_far = 0, max_ending_here = 0;
        int i;
        for(i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if(max_ending_here < 0)
                max_ending_here = 0;

     /* Do not compare for all elements. Compare only
        when  max_ending_here > 0 */
            else if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
        }
        return max_so_far;
    }

    private static void prt(int[] vals){
        for(int i=0;i<vals.length;i++){
            System.out.print(vals[i]+" ");
        }
        "sfgsf".trim();
    }

    public static void main(String[] args) {

        System.out.println(lcsRecursive("fssasklasdasfjadskldf", "flkasjflk akslfjaklf alksfjlkasf asklfjadskldf "));
        int[] newVals = new int[] { 1,2,4,-4,8,11};
        prt(newVals);
        System.out.println();
        System.out.println(maxSubArraySum(newVals));

        String s ="Praveen";
        char[] ar = s.toCharArray();
        int j=ar.length-1;
        for(int i=0;i<s.length()>>1;i++){
            char temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;
            j-- ;

        }

        System.out.println(new String(ar));


    }
}
