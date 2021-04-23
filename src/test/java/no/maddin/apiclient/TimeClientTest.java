package no.maddin.apiclient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TimeControllerApi;
import org.openapitools.client.model.TimeResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.startsWith;

class TimeClientTest {

    private static Process process;
    private static int port;

    @BeforeAll
    static void startApp() throws IOException, InterruptedException {
        String javaBin = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        port = findAvailableSocket();
        process = new ProcessBuilder().command(
            javaBin,
            "-jar",
            System.getProperty("spring.jar"),
            "--server.port=" + port)
            .inheritIO()
            .start();

        process.waitFor(10, TimeUnit.SECONDS);
    }

    private static int findAvailableSocket() throws IOException{
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }

    @AfterAll
    static void stopApp() {
        if (process != null) {
            process.destroyForcibly();
        }
    }

    @Test
    void clientTest() throws ApiException {
        ApiClient client = new ApiClient();
        client.updateBaseUri("http://localhost:" + port);

        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        TimeResponse nowUTC = new TimeControllerApi(client).nowUTC();

        assertThat(nowUTC, hasProperty("timeString", startsWith(today)));
    }
}
