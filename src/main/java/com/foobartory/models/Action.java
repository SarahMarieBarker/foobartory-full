package com.foobartory.models;

import com.google.gson.annotations.Expose;

public enum Action {
    MAKEFOO(ActionType.MAKEFOO, 1000, 1000),
    MAKEBAR(ActionType.MAKEBAR, 500, 2000),
    MAKEFOOBAR(ActionType.MAKEFOOBAR, 2000, 2000),
    SELLFOOBAR(ActionType.SELLFOOBAR, 10000, 10000),
    BUY(ActionType.BUY, 0, 0);

    @Expose
    private final ActionType actionType;
    private final int timeMin;
    private final int timeMax;
    private FooBar resultingFooBar = null;

    Action(ActionType actionType, int timeMin, int timeMax) {
        this.actionType = actionType;
        this.timeMin = timeMin;
        this.timeMax = timeMax;
    }

    public FooBar getResultingFooBar() {
        return this.resultingFooBar;
    }

    public void setResultingFooBar(FooBar fooBar) {
        this.resultingFooBar = fooBar;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public int getTimeMax() {
        return timeMax;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public int getActionTime() {
        if (getTimeMax() != getTimeMin()) {
            return getTimeMin() + (int) (Math.random() * ((getTimeMax() - getTimeMin()) + 1));
        } else {
            return getTimeMin();
        }
    }

    public enum ActionType {
        MAKEFOO,
        MAKEBAR,
        MAKEFOOBAR,
        SELLFOOBAR,
        BUY
    }
}