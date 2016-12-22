import models.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by evan on 12/8/16.
 */
public class Client {

    public static JSONArray request(final String input, final Config configs) {
        final JSONObject response;

        final String url = buildUrl(input, configs);

        try {
            response = (JSONObject) new JSONParser().parse(makeRequest(url));
        } catch (final ParseException e) {
            System.out.println("Error making call");
            return null;
        }

        //TODO generalize this with different responses
        return (JSONArray) new JSONObject((JSONObject) response.get("response")).get("legislator");
    }

    private static String makeRequest(final String url) {
        final DefaultHttpClient httpClient = new DefaultHttpClient();
        final HttpGet httpGetRequest = new HttpGet(url);

        try {
            final HttpResponse response = httpClient.execute(httpGetRequest);
            System.out.println("Response code: " + response.getStatusLine());

            final String result = InputConverter.convertStreamToString(response.getEntity().getContent());
            httpClient.getConnectionManager().shutdown();

            return result;
        } catch (final IOException e) {
            //TODO throw exception
            System.out.println("Error making call");
            return null;
        }
    }

    private static String buildUrl(final String input, final Config configs) {
        String url = configs.getUrl() + "?";

        for (final String header : configs.getHeaders()) {

            if (header.contains("{INPUT}")) {
                url += header.replace("{INPUT}", input);
                url += "&";
                continue;
            }

            if (header.contains("{AUTH_TOKEN}")) {
                url += header.replace("{AUTH_TOKEN}", configs.getAuthToken());
                url += "&";
                continue;
            }

            url += header;
            url += "&";
        }

        // Strip final & sign
        if (configs.getHeaders().size() > 0) {
            return url.substring(0, url.length() - 1);
        }

        return url;
    }

}
