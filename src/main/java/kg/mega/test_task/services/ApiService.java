package kg.mega.test_task.services;

import kg.mega.test_task.clients.ApiFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService implements CommandLineRunner {

    private final ApiFeignClient apiFeignClient;

    private void getAndLogApiObjects() {
        try {
            String response = apiFeignClient.getObjects();
            log.info("Received response from Feign client: {}", response);
        } catch (Exception e) {
            log.error("Error calling API with Feign client", e);
        }
    }

    @Override
    public void run(String... args) {
        log.info("**** START Calling API with Feign client ****");
        this.getAndLogApiObjects();
        log.info("**** END Calling API with Feign client ****");
    }
}
