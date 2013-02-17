package sorts;
import java.lang.reflect.Method;

/**
 * Provides factory method to sort comparable objects using various algorithms.
 * Good for leaning various algorithms & to know when to apply.
 *
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/17/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public enum SortMethod {

    BUBBLE,MERGE,HEAP,QUICK,SELECTION,INSERTION,OPTIMIZED_BUBBLE;

    /**
     * Sorts the arrays provided containing objects of comparable.
     * @param objects
     * @return  time taken in nano seconds to sort
     */
    public long sort(final Comparable[] objects) {
        long startTime = System.nanoTime();
        switch(this){
            case QUICK:
                quick(objects);
                break;
            case  BUBBLE:
                bubble(objects);
                break;
            case MERGE:
                merge(objects);
                break;
            case HEAP:
                heap(objects);
                break;
            case SELECTION:
                selection(objects);
                break;
            case INSERTION:
                insertion(objects);
                break;
            case OPTIMIZED_BUBBLE:
                optimized_bubble(objects);
                break;
            default:
                throw new RuntimeException("No implementation available for  "+this.name());

        }
        long endTime = System.nanoTime();
        return  endTime-startTime;

    }
    //Factory methods to get strategies based on context
    public static SortMethod sizeBased(int size){
        if(size<100) return SortMethod.INSERTION;
        return SortMethod.MERGE;
    }
    //Found this pretty slow (10X), reflection should be avoided although its pretty tempting
    private long reflectionsort(final Comparable[] objects) {
     // hack avoid switch case.
     Method[] methods = this.getClass().getDeclaredMethods();
     for(Method m:methods){
        if(m.getName().equals(this.name().toLowerCase())){
            try {
                long startTime = System.nanoTime();
                    m.invoke(this,new Object[] { objects });
                long endTime = System.nanoTime();
                return  endTime-startTime;
            } catch (Exception e) {
                throw new RuntimeException("No implementation available for  "+this.name());
            }
        }
     }
        return -1;
    }
    // optimized buble
    void optimized_bubble(Comparable[] a){
        boolean switched = true;
        for(int i=0;i<a.length-1 && switched;i++){
            switched = false;
            for(int j=0;j<a.length-i-1;j++)
                if(a[j].compareTo(a[j+1]) > 0){
                    switched = true;
                    /*
                    Comparable hold = a[j];
                    a[j] = a[j+1];
                    a[j+1] = hold;
                    */
                    swap(a,j,j+1);
                }
        }
    }

    // replace all swap routines with this.
    private static void swap(Comparable[] x, int a, int b) {
        Comparable t = x[a];
        x[a] = x[b];
        x[b] = t;
    }


    void bubble(Comparable[] a){
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++)
                if(a[j].compareTo(a[j+1]) > 0){
                    swap(a,j,j+1);
                }
        }
    }

    void selection(Comparable[] a){
        for(int i = a.length-1;i>0;i--){
            Comparable large = a[0];
            int index = 0;
            for(int j = 1;j <= i;j++)
                if(a[j].compareTo(large) > 0){
                    large = a[j];
                    index = j;
                }
            a[index] = a[i];
            a[i] = large;
        }
    }

    void insertion(Comparable[] a){
        int i,j;
        Comparable e;
        for(i=1;i<a.length;i++){
            e = a[i];
            for(j=i-1;j>=0 && a[j].compareTo(e) > 0;j--)
                a[j+1] = a[j];
            a[j+1] = e;
        }
    }

    void heap(Comparable[] a){
        int i,f,s;
        for(i=1;i<a.length;i++){
            Comparable e = a[i];
            s = i;
            f = (s-1)/2;
            while(s > 0 && a[f].compareTo(e) < 0){
                a[s] = a[f];
                s = f;
                f = (s-1)/2;
            }
            a[s] = e;
        }
        for(i=a.length-1;i>0;i--){
            Comparable value = a[i];
            a[i] = a[0];
            f = 0;
            if(i == 1)
                s = -1;
            else
                s = 1;
            if(i > 2 && a[2].compareTo(a[1]) > 0)
                s = 2;
            while(s >= 0 && value.compareTo(a[s]) < 0){
                a[f] = a[s];
                f = s;
                s = 2*f+1;
                if(s+1 <= i-1 && a[s].compareTo(a[s+1]) < 0)
                    s = s+1;
                if(s > i-1)
                    s = -1;
            }
            a[f] = value;
        }
    }

    void merge(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        int i,j,k,l1,l2,size,u1,u2;
        size = 1;
        while(size < a.length){
            l1 = k = 0;
            while((l1 + size) < a.length){
                l2 = l1 + size;
                u1 = l2 - 1;
                u2 = (l2+size-1 < a.length) ?
                        l2 + size-1:a.length-1;
                for(i=l1,j=l2;i <= u1 && j <= u2;k++)
                    if(a[i].compareTo(a[j]) <= 0)
                        aux[k] = a[i++];
                    else
                        aux[k] = a[j++];
                for(;i <= u1;k++)
                    aux[k] = a[i++];
                for(;j <= u2;k++)
                    aux[k] = a[j++];
                l1 = u2 + 1;
            }
            for(i=l1;k < a.length;i++)
                aux[k++] = a[i];
            for(i=0;i < a.length;i++)
                a[i] = aux[i];
            size *= 2;
        }
    }

     void quick(Comparable[] c){
         //I can't think of any way that i can make this recursive method to called from only this method  without this hack
         class I {
             //recusive quick sort
             void qsort(Comparable[] c,int start,int end){
                 if(end <= start) return;
                 Comparable comp = c[start];
                 int i = start,j = end + 1;
                 for(;;){
                     do i++; while(i<end && c[i].compareTo(comp)<0);
                     do j--; while(j>start && c[j].compareTo(comp)>0);
                     if(j <= i)   break;
                     Comparable tmp = c[i];
                     c[i] = c[j];
                     c[j] = tmp;
                 }
                 c[start] = c[j];
                 c[j] = comp;
                 qsort(c,start,j-1);
                 qsort(c,j+1,end);
             }
         }
        new I().qsort(c, 0, c.length - 1);
     }

}
