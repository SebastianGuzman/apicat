package com.cbastian.apicat.resources.adapter.in.util;

public enum GenericCustomerDocumentsType {
    CC,
    CE,
    NT,
    PA,
    CX,
    TI;

    public static GenericCustomerDocumentsType fromValue(String v) {
        return valueOf(v);
    }

    public String getName() {
        return name();
    }

}
