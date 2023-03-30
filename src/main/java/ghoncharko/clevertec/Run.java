package ghoncharko.clevertec;

public class Run {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        new Thread(() -> client.sendRequests(server)).start();
    }
}
