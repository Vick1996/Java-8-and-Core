import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer
{
    public static void main(String[] args)
    {
        int size = 10;
        Object key = new Object();
        Queue<Integer> plate = new LinkedList<>();
        Thread producer = new Thread(new Runnable() {

            @Override
            public void run()
            {
                int cnt = 0;
                while(true) {
                    synchronized (key)
                    {
                        try
                        {
                            while(plate.size()==size)
                                key.wait();

                            plate.offer(cnt++);
                            key.notifyAll();

                            Thread.sleep(900);
                            System.out.println("MOMO produced, plate size: "+plate.size());
                        }
                        catch(InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        Thread consumer = new Thread(new Runnable() {

            @Override
            public void run()
            {
                while(true)
                {
                    synchronized (key)
                    {
                        try
                        {
                            while(plate.size()==0)
                                key.wait();

                            plate.poll();
                            key.notifyAll();

                            Thread.sleep(700);
                            System.out.println("MOMO consumed, plate size: "+plate.size());
                        }
                        catch (InterruptedException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
