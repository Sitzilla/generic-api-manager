import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by evan on 7/3/16.
 */
public class JsonWriter {

    public void writeArray(final JSONArray jsonArray, final String filepath) throws IOException {
        FileWriter file = null;
        //TODO Check if jsonArray is null

        try {
            file = new FileWriter(filepath + ".json");
            file.write(jsonArray.toString());
        } catch (final IOException e) {
            System.out.println("Error writing array");
        } finally {
            file.flush();
            file.close();
        }

        System.out.println("Successfully Copied JSON Object to File...");
    }
}
