package helloworld;

import helloworld.health.TemplateHealthCheck;
import helloworld.resources.APIHelloNestedResource;
import helloworld.resources.APIHelloWorldResource;
import helloworld.resources.ViewHelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.assets.AssetsBundle;

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
        // Enable Views
        bootstrap.addBundle(new ViewBundle<HelloWorldConfiguration>());
        // Enable Static files
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(final HelloWorldConfiguration configuration,
                    final Environment environment) {

        // API and Views
        final APIHelloWorldResource apiResource= new APIHelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final APIHelloNestedResource apiResourceNested = new APIHelloNestedResource(
                configuration.getTemplate(),
                configuration.getBlock().getAnotherVar()
        );
        final ViewHelloWorldResource resourceViewHelloWorld =
                new ViewHelloWorldResource(configuration.getBlock().getAnotherVar());

        environment.jersey().register(apiResource);
        environment.jersey().register(apiResourceNested);
        environment.jersey().register(resourceViewHelloWorld);

        // Health check
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
