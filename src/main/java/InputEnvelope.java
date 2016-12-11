import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by evan on 12/8/16.
 */
public class InputEnvelope {
    @JsonProperty
    private ArrayList<Input> inputs;

    public InputEnvelope() {

    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public void setInputs(final ArrayList<Input> inputs) {
        this.inputs = inputs;
    }

    @Override
    public String toString() {
        return "InputEnvelope{" +
                "inputs=" + inputs +
                '}';
    }
}
