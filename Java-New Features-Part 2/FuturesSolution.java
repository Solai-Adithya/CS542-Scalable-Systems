import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.*;

public class FuturesSolution {
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public Future<String> callAPI() {
        return executor.submit(()-> {
            try {
                System.setProperty("https.proxyHost", "10.8.0.1");
                System.setProperty("https.proxyPort", "8080");
                URL API_URL = new URL("https://api.openweathermap.org/data/2.5/weather?q=Mandi&APPID=2729fc4e4e87cd4039a11cfa38d4847d");
                HttpURLConnection con = (HttpURLConnection) API_URL.openConnection();
                con.setRequestMethod("GET");

                //get result
                BufferedReader br = new BufferedReader(new InputStreamReader(con
                        .getInputStream()));
                String l = br.readLine();
                br.close();
                return l;
            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "api call failed";
        });
    }

    public Future<String> cancelAPIIfDelayed(Future<String> future) {
        return executor.submit(()-> {
            int waitingTime = 0, maxWaitingTime = 2400, waitInterval = 300;
            while (!future.isDone()) {
                System.out.println("Waiting... " + waitingTime + " ms elapsed");
                waitingTime += waitInterval;
                if (waitingTime > maxWaitingTime) {
                    future.cancel(true);
                    System.out.println("API Response has not been returned before " + maxWaitingTime + " ms and hence has been cancelled");
                    return "Cancelled";
                }
                Thread.sleep(waitInterval);
            }
            return "API Call Finished";
        });
    }

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        FuturesSolution instance = new FuturesSolution();
        Future<String> future = instance.callAPI();

        Future<String> cancellationStatus = instance.cancelAPIIfDelayed(future);

        System.out.println("Main execution continues...");
        //can do something else in the main


        //Now blocking for the result
        if (cancellationStatus.get()!="Cancelled") {
                System.out.println("Result received: " + future.get());
        }

        instance.executor.shutdown();
    }
}
