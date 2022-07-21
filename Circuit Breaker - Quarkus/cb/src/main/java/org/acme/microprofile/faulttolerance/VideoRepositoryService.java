package org.acme.microprofile.faulttolerance;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Random;

@ApplicationScoped
public class VideoRepositoryService {

    private static final Logger LOGGER = Logger.getLogger(VideoRepositoryService.class);

    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.6, delay = 10000)
    public String getAvailability() {
        return maybeFail();
    }

    private String maybeFail() {
        // introduce some artificial failures
        String connectionOutput = getOutPut();
        System.out.println("ConnectionOutput:\t " + connectionOutput);

        if (connectionOutput.equals("poor")) { // alternate 2 successful and 2 failing invocations
            LOGGER.errorf("Invocation #%d failing");
            throw new RuntimeException("Poor Connection.");
        }

        LOGGER.infof("Invocation #%d OK");

        return connectionOutput;
    }

    private String getOutPut() {
        ArrayList<String> outputs = new ArrayList<>();

        outputs.add("good");
        outputs.add("verygood");
        outputs.add("poor");

        Random random = new Random();
        double prob = random.nextDouble();

        if (prob < 0.01) {
            return outputs.get(1);
        } else if (prob < 0.4) {
            return outputs.get(0);
        } else {
            return outputs.get(2);
        }
    }
}
