package ghoncharko.clevertec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Client {

    private final List<Integer> requestList;
    private final List<Integer> responseList;

    public Client() {
        requestList = new ArrayList<>();
        responseList = new ArrayList<>();
    }

    public void sendRequests(Server server) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int req = new Random().nextInt(100);
            requestList.add(req);
            Callable<Integer> task = () -> server.processRequest(req);
            futures.add(executorService.submit(task));
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futures.forEach(f -> {
            try {
                responseList.add(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public List<Integer> getRequestList() {
        return requestList;
    }

    public List<Integer> getResponseList() {
        return responseList;
    }
}
