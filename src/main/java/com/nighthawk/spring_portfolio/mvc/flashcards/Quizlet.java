import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.google.gson.Gson;

public class Quizlet {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static CompletableFuture<List<Object>> fetchQuizlet(String id) {
        return httpClient.sendAsync(
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(
                                "https://quizlet.com/webapi/3.4/studiable-item-documents?filters%5BstudiableContainerId%5D="
                                        + id
                                        + "&filters%5BstudiableContainerType%5D=1&perPage=5&page=1"))
                        .build(),
                HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Quizlet::extractTerms);
    }

    private static List<Object> extractTerms(String response) {
        Gson gson = new Gson();
        ResponseData res = gson.fromJson(response, ResponseData.class);
        List<Object> terms = res.responses.get(0).models.studiableItem;
    
        int currentLength = 5;
        String token = res.responses.get(0).paging.token;
        int page = 2;
        while (currentLength >= 5) {
            HttpResponse<String> httpResponse = httpClient.send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create(
                                    "https://quizlet.com/webapi/3.4/studiable-item-documents?filters%5BstudiableContainerId%5D="
                                            + id
                                            + "&filters%5BstudiableContainerType%5D=1&perPage=5&page="
                                            + page++
                                            + "&pagingToken="
                                            + token))
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            String newResponse = httpResponse.body();
            ResponseData newRes = gson.fromJson(newResponse, ResponseData.class);
            terms.addAll(newRes.responses.get(0).models.studiableItem);
            currentLength = newRes.responses.get(0).models.studiableItem.length;
            token = newRes.responses.get(0).paging.token;
        }
    
        return terms;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<List<Object>> future = fetchQuizlet("213648175");
        future.thenAccept(terms -> {
            // Implement code to log terms
        });
    }
    
    private static class ResponseData {
        List<Response> responses;
    }

    private static class Response {
        Paging paging;
        Models models;
    }

    private static class Paging {
        String token;
    }

    private static class Models {
        List<Object> studiableItem;
    }

}