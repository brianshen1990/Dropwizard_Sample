package helloworld.resources.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

public class BlockNestedConfiguration extends Configuration  {
    @NotEmpty
    private String anotherVar;

    @JsonProperty()
    public void setAnotherVar(String var) {
        this.anotherVar = var;
    }

    @JsonProperty()
    public String getAnotherVar() {
        return anotherVar;
    }
}
