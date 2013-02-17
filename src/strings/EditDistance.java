package strings;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 1/31/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditDistance {

    private static Map<String, ArrayList<Integer>> locations = new HashMap<String, ArrayList<Integer>>();

    private static void storeLocations(String[] text) {
        for (int i = 0; i < text.length; ++i) {
            String word = text[i];
            if (locations.keySet().contains(word)) {
                ArrayList<Integer> location = locations.get(word);
                location.add(i);
                Collections.sort(location);
                locations.put(word, location);
            } else {
                ArrayList<Integer> location = new ArrayList<Integer>();
                location.add(i);
                locations.put(word, location);
            }
        }
    }

    private static int modified_binary_search(ArrayList<Integer> array,
                                              int target) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target == array.get(mid))
                return target;
            if (target < array.get(mid))
                high = mid;
            else
                low = mid + 1;
        }
        if (low >= 0 && low < array.size())
            return array.get(low);
        else
            return array.get(array.size() - 1);
    }

    private static int shortest_distance(String a, String b) {
        int min = Integer.MAX_VALUE;
        for (int index_a : locations.get(a)) {
            ArrayList<Integer> array = locations.get(b);
            int nearest_index_b = modified_binary_search(array, index_a);
            int aVal= Collections.binarySearch(array,index_a);
            System.out.println(aVal);

            int distance = Math.abs(nearest_index_b - index_a);
            if (distance < min)
                min = distance;
        }
        return min;
    }

    public static void main(String[] args) {
       String[] vals = Arrays.asList("praveen","manvi","krishna","guru","anusha","sahana","amma").toArray(new String[]{});
       EditDistance.storeLocations(vals);

        System.out.println(EditDistance.shortest_distance("praveen","amma"));
        System.out.println(EditDistance.shortest_distance("praveen","krishna"));
       // System.out.println(strings.EditDistance.shortest_distance("praveen","krisha"));


    }
}
