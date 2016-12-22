package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by evan on 12/21/16.
 */
public class ConfigEnvelope {
    @JsonProperty
    private ArrayList<Config> configs;

    public ConfigEnvelope() {

    }

    public ArrayList<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(final ArrayList<Config> configs) {
        this.configs = configs;
    }

    @Override
    public String toString() {
        return "models.JsonProperty{" +
                "configs=" + configs +
                '}';
    }
}
