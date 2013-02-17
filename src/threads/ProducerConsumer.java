package threads; /**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 1/10/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Collections;
import java.util.concurrent.*;

public class ProducerConsumer {

    public static void main(String args[]) {
        try {
            InterAgent broker = new InterAgent();
            ExecutorService threadPool = Executors.newFixedThreadPool(3);

            threadPool.execute(new Consumer("1", broker));
            threadPool.execute(new Consumer("2", broker));
            Future producerStatus = threadPool.submit(new Producer(broker));

            // this will wait for the producer to finish its execution.
            producerStatus.get();


            threadPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// Common Broker, needs to be thread safe if it has mutable state
class InterAgent {
    public ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
    public Boolean continueProducing = Boolean.TRUE;

    public void put(Integer data) throws InterruptedException {
        this.queue.put(data);
    }

    public Integer get() throws InterruptedException {
        //this.queue.take()
        return this.queue.poll(1, TimeUnit.SECONDS);
    }
}


class Consumer implements Runnable {

    private String name;
    private InterAgent broker;


    public Consumer(String name, InterAgent broker) {
        this.name = name;
        this.broker = broker;
    }


    @Override
    public void run() {
        try {
            Integer data = broker.get();

            while (broker.continueProducing || data != null) {
//                Thread.sleep(1000);
                System.out.println("threads.Consumer " + this.name + " processed data from broker: " + data);

                data = broker.get();
            }


            System.out.println("Comsumer " + this.name + " finished its job; terminating.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


}

class Producer implements Runnable {
    private InterAgent broker;

    public Producer(InterAgent broker) {
        this.broker = broker;
    }


    @Override
    public void run() {
        try {
            for (Integer i = 1; i < 5 + 1; ++i) {
                System.out.println("threads.Producer produced: " + i);
//                Thread.sleep(100);
                broker.put(i);
            }

            this.broker.continueProducing = Boolean.FALSE;
            System.out.println("threads.Producer finished its job; terminating.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}