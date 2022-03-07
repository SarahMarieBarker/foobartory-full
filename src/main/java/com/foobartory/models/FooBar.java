package com.foobartory.models;

import com.google.gson.annotations.Expose;

public class FooBar implements IResource {
    @Expose
    private final String serialNumber;

    @Expose
    private final Bar bar;

    public FooBar(Foo foo, Bar bar) {
        this.serialNumber = foo.getSerialNumber() + "-" + bar.getSerialNumber();
        this.bar = bar;
    }

    public Bar getBar() {
        return bar;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
