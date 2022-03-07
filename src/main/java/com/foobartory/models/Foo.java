package com.foobartory.models;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class Foo implements IResource {
    @Expose
    private final String serialNumber;

    public Foo() {
        this.serialNumber = UUID.randomUUID().toString();
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
