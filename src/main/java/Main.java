import com.oracle.javafx.jmx.json.JSONException;
import models.ConfigEnvelope;
import models.Input;
import models.InputEnvelope;
import org.json.simple.JSONArray;

import java.io.IOException;

/**
 * Created by evan on 12/8/16.
 */
public class Main {

    private static String filepath;

    public static void main(final String[] args) throws IOException {
        setArgs(args);

        final InputEnvelope inputs = new Parser().parse();
        final ConfigEnvelope configs = new Parser().parseConfigs();

        JSONArray responses = new JSONArray();

        for (final Input input : inputs.getInputs()) {
            final JSONArray response =  Client.request(input.getName(), configs.getConfigs().get(0));
            responses = concatArray(response, responses);
        }

        new JsonWriter().writeArray(responses, filepath);


        System.out.println("----------------------------------------");
        System.out.println("Finished exporting all data.");
    }

    private static void setArgs(final String[] args) {
        if (args[0] == null) {
        //TODO throw something
//            throw new UnprocessableEntityException("Error with program inputs: inputs may not be null");
        }

        filepath = args[0];
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
