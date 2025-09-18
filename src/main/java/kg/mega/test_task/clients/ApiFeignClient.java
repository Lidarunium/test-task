package kg.mega.test_task.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "restful-api", url = "https://api.restful-api.dev/objects")
public interface ApiFeignClient {

    @GetMapping
    String getObjects();
}
