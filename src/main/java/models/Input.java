package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by evan on 12/8/16.
 */
public class Input {
    @JsonProperty
    private String name;

    public Input() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "models.Input{" +
                "name='" + name + '\'' +
                '}';
    }
}
