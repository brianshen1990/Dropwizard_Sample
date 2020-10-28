package helloworld;

import helloworld.health.TemplateHealthCheck;
import helloworld.resources.HelloNestedAPIResource;
import helloworld.resources.HelloWorldAPIResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "main";
    }

    @Override
    public void initialize(final Bootstrap<HelloWorldConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HelloWorldConfiguration configuration,
                    final Environment environment) {

        final HelloWorldAPIResource resource= new HelloWorldAPIResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final HelloNestedAPIResource resourceNested= new HelloNestedAPIResource(
                configuration.getTemplate(),
                configuration.getBlock().getAnotherVar()
        );
        environment.jersey().register(resource);
        environment.jersey().register(resourceNested);


        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
