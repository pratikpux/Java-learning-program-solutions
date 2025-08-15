package com.example.service;

public interface ExternalApi {
    String getData();
    String getDataById(int id);
    boolean isServiceAvailable();
}