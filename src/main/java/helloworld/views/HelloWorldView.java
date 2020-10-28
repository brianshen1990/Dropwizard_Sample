package helloworld.views;

import io.dropwizard.views.View;

public class HelloWorldView extends View {

    public HelloWorldView() {
        super("helloworld.ftl");
    }

}
