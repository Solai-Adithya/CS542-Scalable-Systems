package org.acme.microprofile.faulttolerance;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

/**
 * A JAX-RS resource that provides information about kinds of videos we have on store and numbers of packages
 * available.
 *
 * Demonstrates {@link Retry}, {@link CircuitBreaker}, {@link Timeout} and {@link Fallback} policies.
 */
@Path("/video")
public class VideoResource {

    private static final Logger LOGGER = Logger.getLogger(VideoResource.class);

    @Inject
    VideoRepositoryService videoRepository;

    private AtomicLong counter = new AtomicLong(0);

    private Float failRatio = 0.5f;

    @GET
    public Response availability() {
        final Long invocationNumber = counter.getAndIncrement();

        try {
            String availability = videoRepository.getAvailability();
            LOGGER.infof("videoResource#availability() invocation #%d returning successfully", invocationNumber);
            return Response.ok(availability).build();
        } catch (RuntimeException e) {
            String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            LOGGER.errorf("videoResource#availability() invocation #%d failed: %s", invocationNumber, message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }


    void setFailRatio(Float failRatio) {
        this.failRatio = failRatio;
    }

    void resetCounter() {
        this.counter.set(0);
    }

    Long getCounter() {
        return counter.get();
    }
}
