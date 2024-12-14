package com.guilherme.fiapfood.enums;


public enum OrderStatus {
    RECEIVED("RECEIVED"),
    IN_PREPARATION("IN_PREPARATION"),
    READY("READY"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");
    private String statusOrder;

    OrderStatus(String statusOrder) {
        this.statusOrder = statusOrder;
    }

}