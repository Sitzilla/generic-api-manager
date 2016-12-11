import com.oracle.javafx.jmx.json.JSONException;
import org.json.simple.JSONArray;

import java.io.IOException;

/**
 * Created by evan on 12/8/16.
 */
public class Main {

    public static void main(final String[] args) throws IOException {

        final InputEnvelope inputs = new Parser().parse();

        JSONArray responses = new JSONArray();

        for (final Input input : inputs.getInputs()) {

            JSONArray response =  Client.request(input.getName());
            responses = concatArray(response, responses);

        }

        // TODO move arguments to configs
        new JsonWriter().writeArray(responses, "/Users/evan/Documents", "state_reps");


        System.out.println("----------------------------------------");
        System.out.println("Finished exporting all data.");
    }

    private static JSONArray concatArray(final JSONArray... arrs)
            throws JSONException {
        final JSONArray result = new JSONArray();

        for (final JSONArray arr : arrs) {
            for (int i = 0; i < arr.size(); i++) {
                result.add(arr.get(i));
            }
        }

        return result;
    }
}
