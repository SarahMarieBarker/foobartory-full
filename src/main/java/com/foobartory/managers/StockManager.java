package com.foobartory.managers;

import com.foobartory.events.EventHandler;
import com.foobartory.models.Action;
import com.foobartory.models.Bar;
import com.foobartory.models.Foo;
import com.foobartory.models.FooBar;
import com.google.gson.annotations.Expose;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockManager {

    @Expose
    private final List<Foo> foo;
    @Expose
    private final List<Bar> bar;
    @Expose
    private final List<FooBar> foobar;
    @Expose
    private int euros;

    final private EventHandler eventHandler;

    public StockManager(EventHandler eventHandler) {
        this.foo = new ArrayList<>();
        this.bar = new ArrayList<>();
        this.foobar = new ArrayList<>();
        this.euros = 0;
        this.eventHandler = eventHandler;
        eventHandler.sendStock(this);
    }

    private static boolean isFooBarAssemblingSuccessful() {
        return (new Random().nextInt(10) + 1) <= 6;
    }

    public void addFoo(int nbFoo) {
        for (int i = 0; i < nbFoo; i++) {
            Foo foo = new Foo();
            System.out.println(MessageFormat.format("[StockManager] Adding Foo {0}", foo.getSerialNumber()));
            this.foo.add(foo);
        }
    }

    public void addBar(int nbBar) {
        for (int i = 0; i < nbBar; i++) {
            Bar bar = new Bar();
            System.out.println(MessageFormat.format("[StockManager] Adding Bar {0}", bar.getSerialNumber()));
            this.bar.add(bar);
        }
    }

    public void addBar(Bar bar) {
        this.bar.add(bar);
    }

    public void addFooBar(FooBar fooBar) {
        System.out.println(MessageFormat.format("[StockManager] Adding FooBar {0}", fooBar.getSerialNumber()));
        this.foobar.add(fooBar);
    }

    public synchronized FooBar assembleFoobar() {
        System.out.println(MessageFormat.format("[StockManager] Removing Foo {0}", this.foo.get(0).getSerialNumber()));
        Foo foo = this.foo.remove(0);

        System.out.println(MessageFormat.format("[StockManager] Removing Bar {0}", this.bar.get(0).getSerialNumber()));
        Bar bar = this.bar.remove(0);

        FooBar fooBar = new FooBar(foo, bar);
        return fooBar;
    }

    public void removeFoo(int nbFoo) {
        for (int i = 0; i < nbFoo; i++) {
            System.out.println(MessageFormat.format("[StockManager] Removing Foo {0}", this.foo.get(0).getSerialNumber()));
            this.foo.remove(0);
        }
    }

    public void removeFooBar(int nbFooBar) {
        for (int i = 0; i < nbFooBar; i++) {
            System.out.println(MessageFormat.format("[StockManager] Selling FooBar {0}", this.foobar.get(0).getSerialNumber()));
            this.foobar.remove(0);
        }
    }

    public int getFooSize() {
        return foo.size();
    }

    public int getBarSize() {
        return bar.size();
    }

    public int getFoobarSize() {
        return foobar.size();
    }

    public int getEuros() {
        return euros;
    }

    public synchronized void updateCostResources(Action action) {
        if (action.getActionType().equals(Action.ActionType.MAKEFOOBAR)) {
            action.setResultingFooBar(this.assembleFoobar());
        }
        if (action.getActionType().equals(Action.ActionType.SELLFOOBAR)) {
            this.removeFooBar(5);
        }
        if (action.getActionType().equals(Action.ActionType.BUY)) {
            this.removeFoo(6);
            System.out.println(MessageFormat.format("[StockManager] Paying Euros {0}", 3));
            this.euros -= 3;
        }
        eventHandler.sendStock(this);
    }

    public synchronized void updateResultingResources(Action action) {
        if (action.getActionType().equals(Action.ActionType.MAKEFOOBAR)) {
            if (isFooBarAssemblingSuccessful()) {
                System.out.println("[StockManager] FooBar success !");
                this.addFooBar(action.getResultingFooBar());
            } else {
                System.out.println("[StockManager] FooBar failed ...");
                Bar bar = action.getResultingFooBar().getBar();
                System.out.println(MessageFormat.format("[StockManager] Getting Bar back {0}", bar.getSerialNumber()));
                this.addBar(bar);
            }
        }
        if (action.getActionType().equals(Action.ActionType.MAKEFOO)) {
            this.addFoo(1);
        }
        if (action.getActionType().equals(Action.ActionType.MAKEBAR)) {
            this.addBar(1);
        }
        if (action.getActionType().equals(Action.ActionType.SELLFOOBAR)) {
            System.out.println(MessageFormat.format("[StockManager] Getting Euros {0}", 5));
            this.euros += 5;
        }
        eventHandler.sendStock(this);
    }

}