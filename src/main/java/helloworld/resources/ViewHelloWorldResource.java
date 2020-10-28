package helloworld.resources;

import helloworld.views.HelloWorldMustacheView;
import helloworld.views.HelloWorldView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Path("/")
public class ViewHelloWorldResource {

    private final String anotherVar;

    public ViewHelloWorldResource(String varName){
        this.anotherVar = varName;
    }

    @GET
    @Produces("text/html;charset=UTF-8")
    @Path("/hello-world.html")
    public HelloWorldView helloWorldHTML() {
        return new HelloWorldView();
    }

    @GET
    @Produces("text/html;charset=UTF-8")
    @Path("/hello-world")
    public HelloWorldView helloWorld() {
        return new HelloWorldView();
    }

    @GET
    @Produces("text/html;charset=UTF-8")
    @Path("/hello-world-mustache.html")
    public HelloWorldMustacheView helloWorldMustache( @QueryParam("name") Optional<String> name ) {
        final String value = name.orElse(anotherVar);
        return new HelloWorldMustacheView(value);
    }



}
