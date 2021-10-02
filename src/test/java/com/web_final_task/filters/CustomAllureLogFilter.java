package com.web_final_task.filters;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureLogFilter {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomAllureLogFilter() {
    }

    public static CustomAllureLogFilter allureLogFilter() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplate() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }

    private static class InitLogFilter {
        private static final CustomAllureLogFilter logFilter = new CustomAllureLogFilter();
    }
}
