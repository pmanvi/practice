package designpatterns;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/16/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public final class LazyLoadedThreadSafeSingleton {

    /**
     The 'volatile keyword ensures that multiple threads
     handle the uniqueInstance variable correctly when it
     is being initialized to the LazyLoadedThreadSafeSingleton instance
     */
    private volatile static LazyLoadedThreadSafeSingleton onlyOne;
    private LazyLoadedThreadSafeSingleton() {}

    public static LazyLoadedThreadSafeSingleton getInstance(){
        if(onlyOne==null){
            synchronized (LazyLoadedThreadSafeSingleton.class){
                 if(onlyOne==null){
                     onlyOne=new LazyLoadedThreadSafeSingleton();
                 }
            }
        }
        return onlyOne;
    }
}
