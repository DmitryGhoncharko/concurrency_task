package ghoncharko.clevertec;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServerTest {

    @Test
    public void testClientServerInteraction() {
        Server server = new Server();
        Client client = new Client();

        client.sendRequests(server);

        List<Integer> requests = client.getRequestList();
        List<Integer> responses = client.getResponseList();

        assertEquals(requests.size(), responses.size());

        for (int i = 0; i < requests.size(); i++) {
            Integer req = requests.get(i);
            Integer resp = 100 - req;
            assertEquals(resp, responses.get(i));
        }
    }
}
