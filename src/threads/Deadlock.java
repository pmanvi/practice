package threads;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/15/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
// Copied from http://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                    + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
            //bower.unSynchronizesbowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                    + " has bowed back to me!%n",
                    this.name, bower.getName());
        }

        public void unSynchronizesbowBack(Friend bower) {
            System.out.format("%s: %s"
                    + " has bowed back to me!%n",
                    this.name, bower.getName());
        }

    }

    public static void main(String[] args) {
        final Friend praveen =
                new Friend("Praveen");
        final Friend suresh =
                new Friend("Suresh");
        new Thread(new Runnable() {
            public void run() { praveen.bow(suresh); }
        }).start();
        //try{ Thread.sleep(500); }catch(Exception e) {}
        new Thread(new Runnable() {
            public void run() { suresh.bow(praveen); }
        }).start();


        new Thread(new Runnable() {
            public void run() { while(true) { try{ Thread.sleep(5000); }catch(Exception e) {}
                System.out.println(Thread.activeCount()+"  "+Thread.currentThread().getName());
            }}
        }).start();
    }
}