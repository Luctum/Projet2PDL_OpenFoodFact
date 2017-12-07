package pdl.Fetcher;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ApiMain {

    private OkHttpClient client = new OkHttpClient();

    public ResponseBody run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = this.client.newCall(request).execute();
        return response.body();
    }

    public String displayJSON(ResponseBody responseBody) throws IOException {
        return responseBody.string();
    }

    public static void main(String[] args) throws IOException {
        ApiMain apiMain = new ApiMain();
        System.out.println(apiMain.run("https://world.openfoodfacts.org/cgi/search.pl?search_terms=banania&search_simple=1&action=process&json=1"));

    }
}