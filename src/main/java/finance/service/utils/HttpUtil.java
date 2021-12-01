package finance.service.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {

    // TODO: Сделть одну утилиту отправки запросов

    public static String sendYahooTickerRequest(String apiUrl, String ticker, String params) throws IOException, InterruptedException {
        String url = apiUrl.concat(ticker).concat(params);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static String sendTinkoffPortfolioRequest(String apiUrl, String token) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(apiUrl)
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        return client.newCall(request).execute().body().string();
    }

}
