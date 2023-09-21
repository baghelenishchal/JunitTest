import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MyWebClientService {
    private final WebClient webClient;

    public MyWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://example.com").build();
    }

    public Mono<String> fetchData() {
        return webClient
            .get()
            .uri("/api/data")
            .retrieve()
            .bodyToMono(String.class);
    }
}
