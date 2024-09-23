package Gwollya.inc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpClientAndJSON {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apiUrl = "https://httpbin.org/ip";

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            System.out.println("Ответ сервера: " + responseBody);

            JSONObject json = new JSONObject(responseBody);
            String ipAddress = json.getString("origin");

            System.out.println("IP-адрес: " + ipAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
