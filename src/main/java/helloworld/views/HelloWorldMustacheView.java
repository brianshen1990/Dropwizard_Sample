package helloworld.views;

import io.dropwizard.views.View;

public class HelloWorldMustacheView extends View {

    private String varName;

    public String getVarName() {
        return this.varName; // this must exist!
    }

    public HelloWorldMustacheView( String varName) {
        super("helloworld.mustache");
        this.varName = varName;
        // System.out.println(this.varName + "-------");
    }

}
