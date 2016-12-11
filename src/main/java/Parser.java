import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.File;
import java.io.IOException;

/**
 * Created by evan on 12/8/16.
 */
public class Parser {

    public InputEnvelope parse() {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        mapper.registerModule(new JodaModule());

        try {
            final File file = new File(String.valueOf(getClass().getClassLoader().getResource("inputs.yml").getFile()));
            return mapper.readValue(file, InputEnvelope.class);
        } catch (final IOException e) {
            System.out.println("Parsing Error!");
            return null;
        }
    }

}
