import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

public class MyWebClientServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private MyWebClientService myWebClientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        myWebClientService = new MyWebClientService(webClientBuilder);
    }

    @Test
    public void fetchDataShouldReturnData() {
        // Mock WebClient behavior
        when(webClientBuilder.baseUrl("https://example.com")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/api/data")).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Test data"));

        // Perform the test
        Mono<String> result = myWebClientService.fetchData();

        // Assertions
        StepVerifier.create(result)
            .expectNext("Test data")
            .expectComplete()
            .verify();
    }
}
