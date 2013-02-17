package sorts;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/15/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */

public class SortAlgoTester {

    static List<Integer> randomList(int total){
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<total;i++){
            list.add(new Random().nextInt());
        }
        return list;
    }

    static List<Integer> randomListSorted(int total){
        List<Integer> list = randomList(total);
        Collections.sort(list);
        return list;
    }


    public static void main(String[] args) {
           Integer[] vals = new Integer[] { 1,2,3,4};
           List<Integer> list = Arrays.asList(vals);
           SortMethod.sizeBased(vals.length).sort(vals);

           SortMethod[] values = SortMethod.values();
           int[] inputs = new int[]{5,10,50,500,1000,10000};
           for(int i:inputs){
               List<Integer> input = randomList(i);
               //input = randomListSorted(i);
               //System.out.println(input.size()+"  "+input.toArray(new Integer[input.size()]).length);
               Map<SortMethod,Long> stats = new EnumMap<SortMethod,Long>(SortMethod.class);
               for(SortMethod s:values){
                   stats.put(s, s.sort(input.toArray(new Integer[input.size()])));
               }
               System.out.println(input.size()+" : "+stats);
               int n = input.size();
               //System.out.println(n+"*LOG("+n+") ="+(int)(n*Math.log(n)));
    }      }


    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i <= a.length; i++)
            if (a[i].compareTo(a[i-1]) > 0) return false;
        return true;
    }
}


