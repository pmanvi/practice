package linkedlists;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 1/25/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListProblems {

    public static void main(String[] args) {

        LinkedList<String> l = new LinkedList<String>();
        for(int i=0;i<10;i++){
             l.add(String.valueOf(i));
        }
        System.out.println(l);
        System.out.println(reverse(l));

        Collections.reverse(l);
        System.out.println(l);

        List<Integer> l1 = new LinkedList<Integer>();
        List<Integer> l2 = new LinkedList<Integer>();

        for(int i=0;i<10;i++){
           // l1.add(Math.abs(new Random().nextInt(i)));
           // l1.add(Math.abs(new Random().nextInt(i+1)));
        }
        System.out.println(l1);
        System.out.println(l2);
        //List<Integer> r = longestCommonSubseq(l1,l2);
        //System.out.println(r);
        System.out.println(fib(3)+"  "+fib(4));



    }

    static LinkedList<String> reverse(LinkedList<String> l) {
        int size = l.size();
        for(int i = 0; i < size; i++) {
            l.add(i, l.removeLast());
        }
        return l;
    }


    class ReversableStack<E> extends Stack<E> {
        public void reverse() {
            if (isEmpty()) {
                return;
            } else {
                E a = pop();
                if (a instanceof Integer){
                  // do this
                }
                reverse();
                appendStack(a);
            }
        }
        void appendStack(E a) {
            if (isEmpty()) {
                push(a);
                return;
            } else {
                E o = pop();
                appendStack(o);
                push(o);
            }
        }

    }

    public static void revertStack(Stack<Integer> s)
    {
        if (s.isEmpty()) {
            return;
        } else {

            Integer a = s.pop();
            revertStack(s);
            appendStack(s, a);
        }
    }

    public static void appendStack(Stack<Integer> s, Integer a)
    {
        if (s.isEmpty()) {
            s.push(a);
            return;
        } else {
            Integer o = s.pop();
            appendStack(s, a);
            s.push(o);
        }
    }

    class Node {
        Node next;
        Node prev;
        String data;
    }

    static boolean hasLoop(Node first) {
        if(first == null) // list does not exist..so no loop either.
                return false;
            Node slow, fast; // create two references.
            slow = fast = first; // make both refer to the start of the list.
            while(true) {
                slow = slow.next;          // 1 hop.
                if(fast.next != null)
                    fast = fast.next.next; // 2 hops.
                else
                    return false;          // next node null => no loop.
                if(slow == null || fast == null) // if either hits null..no loop.
                    return false;
                if(slow == fast) // if the two ever meet...we must have a loop.
                    return true;
        }
    }


    private static <T> List<T> longestCommonSubseq(List<T> a, int indA, List<T> b, int indB){
        if (indA == a.size() || indB == b.size())
            return Collections.emptyList();

        T itemA = a.get(indA);
        T itemB = b.get(indB);

        List<T> res;
        if (itemA.equals(itemB)){
            res = new ArrayList<T>();
            res.add(itemA);
            res.addAll(longestCommonSubseq(a, indA+1, b, indB+1));
        }else{
            List<T> opt1 = longestCommonSubseq(a, indA+1, b, indB);
            List<T> opt2 = longestCommonSubseq(a, indA, b, indB+1);
            if (opt1.size()>opt2.size())
                res = opt1;
            else
                res = opt2;
        }
        return res;
    }

    public static <T> List<T> longestCommonSubseq(List<T> a, List<T> b){
        return longestCommonSubseq(a,0,b,0);
    }


    //procedural reverse

    public void   reverse(Node head) {
        Node n3 = head;
        Node n2 = null;
        Node n1 = null;
        StringBuilder sb;
        while (n3!= null) {
            n1 = n2;
            n2 = n3;
            n3 = n2.next;
            n2.next = n1;
        }
        head = n2;
    }

    public static int fib(int n) {
        if (n < 2) {
            return n; // basis
        }
        return fib(n-1) + fib(n-2); // recursive part
    }


    public static List<Integer> getIntersection(int[] array1, int[] array2) {
        Set<Integer> inputs = new TreeSet<Integer>();
        List<Integer> common = new ArrayList<Integer>();

        for(int i=0;i<array1.length; i++){
            inputs.add(array1[i]);
        }

        for (int i=0;i<array2.length;i++){
            if (!inputs.add(array2[i]))
                common.add(array2[i]);
        }
        return common;
    }


}
