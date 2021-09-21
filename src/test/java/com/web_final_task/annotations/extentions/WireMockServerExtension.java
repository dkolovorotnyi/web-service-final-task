package com.web_final_task.annotations.extentions;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class WireMockServerExtension implements AfterAllCallback, BeforeAllCallback {

    private final int PORT = 7878;
    private final String HOST = "localhost";
    private WireMockServer mockServer;

    @Override
    public void beforeAll(ExtensionContext context) {
        this.mockServer = new WireMockServer(PORT);
        this.mockServer.start();
        WireMock.configureFor(HOST, PORT);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (mockServer.isRunning()) {
            this.mockServer.stop();
            this.mockServer.shutdown();
        }
    }
}
