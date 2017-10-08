package com.nordnet.batchsecuritoo.destination.entity;

public enum DataType {

    TECHNICAL_ID("technical_id"),
    PRODUCT("product"),
    PRODUCT_CODE("product_code"),
    RETAILER_ID("retailer_id");

    private String label;

    private DataType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isTechnicalId(){
        return this.equals(TECHNICAL_ID);
    }

    public boolean isProduct(){
        return this.equals(PRODUCT);
    }

    public boolean isProductCode(){
        return this.equals(PRODUCT_CODE);
    }

    public boolean isRetailerId(){
    return this.equals(RETAILER_ID);
    }
}
