package ghoncharko.clevertec;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private final Lock lock = new ReentrantLock();

    public Integer processRequest(Integer req) {

        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();

        Integer resp = 100 - req;

        lock.unlock();

        return resp;
    }
}
