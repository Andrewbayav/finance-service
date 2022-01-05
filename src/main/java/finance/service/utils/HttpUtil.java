package finance.service.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {

    public static String sendYahooTickerRequest(String apiUrl, String ticker, String params) {
        String url = apiUrl.concat(ticker).concat(params);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        String result;

        try {
            result = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e){
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public static String sendTinkoffPortfolioRequest(String apiUrl, String token) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(apiUrl)
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        String result;
        try {
            result = client.newCall(request).execute().body().string();
        } catch (Exception e){
            e.printStackTrace();
            result = "";
        }
        return result;
    }

}
