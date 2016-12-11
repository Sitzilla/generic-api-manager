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

    //TODO move to configs
    private static final String AUTH_TOKEN = "b71e1cea1fb0c0725ef2ad0e8127ebd2";
    private static final String URL = "http://www.opensecrets.org/api";

    public static JSONArray request(final String input) {
        final JSONObject response;

        try {
            response = (JSONObject) new JSONParser().parse(makeRequest(input));
        } catch (final ParseException e) {
            System.out.println("Error making call");
            return null;
        }

        //TODO generalize this with different responses
        return (JSONArray) new JSONObject((JSONObject) response.get("response")).get("legislator");
    }

    private static String makeRequest(final String input) {
        final DefaultHttpClient httpClient = new DefaultHttpClient();
        //TODO move to configs
        final HttpGet httpGetRequest = new HttpGet("http://www.opensecrets.org/api?method=getLegislators&id=" + input + "&apikey= " + AUTH_TOKEN + "&output=json");
//        httpGetRequest.addHeader("apikey", AUTH_TOKEN);
//        httpGetRequest.addHeader("method", "getLegislators");
//        httpGetRequest.addHeader("id", input);
//        httpGetRequest.addHeader("output", "json");

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
}
