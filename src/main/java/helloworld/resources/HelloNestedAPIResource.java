package helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import helloworld.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/hello-nested")
@Produces(MediaType.APPLICATION_JSON)
public class HelloNestedAPIResource {
    private final String template;
    private final String anotherVar;
    private final AtomicLong counter;

    public HelloNestedAPIResource(String template, String anotherVar) {
        this.template = template;
        this.anotherVar = anotherVar;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("var") Optional<String> var) {
        final String value = String.format(template, var.orElse(anotherVar));
        return new Saying(counter.incrementAndGet(), value);
    }
}