package com.foobartory.models;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class Bar implements IResource {
    @Expose
    private final String serialNumber;

    public Bar() {
        this.serialNumber = UUID.randomUUID().toString();
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
