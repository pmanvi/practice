package queue;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/14/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayQueue<T> {

    protected T[] array;
    protected int start,end;
    protected boolean full;

    public ArrayQueue(int maxsize){
        array = (T[]) new Object[maxsize];
        start = end = 0;
        full = false;

    }

    public boolean isEmpty(){
        return ((start == end) && !full);
    }

    public void enQueue(T o){
        if(!full)
            array[start = (++start % array.length)] = o;
        if(start == end)
            full = true;
    }

    public Object deQueue(){
        if(full)
            full = false;
        else if(isEmpty())
            return null;
        return array[end = (++end % array.length)];
    }
}
