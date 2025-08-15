package com.example.service;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        if (!externalApi.isServiceAvailable()) {
            return "Service unavailable";
        }
        return externalApi.getData();
    }

    public String fetchDataById(int id) {
        if (id <= 0) {
            return "Invalid ID";
        }
        return externalApi.getDataById(id);
    }

    public String getServiceStatus() {
        return externalApi.isServiceAvailable() ? "Available" : "Unavailable";
    }
}