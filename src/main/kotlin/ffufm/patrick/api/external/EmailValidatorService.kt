package ffufm.patrick.api.external

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class EmailValidatorService {
    fun emailApiClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://www.disify.com")
            .filter { request, next -> next.exchange(request)}
            .build()
    }

    // Query on External API
    suspend fun verifyEmail(email: String): Map<Any, Any> {
        val client = emailApiClient()

        return client.get()
            .uri { builder -> builder
                .apply { path("/api/email/$email") }
                .build()}
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .awaitBody()
    }

}