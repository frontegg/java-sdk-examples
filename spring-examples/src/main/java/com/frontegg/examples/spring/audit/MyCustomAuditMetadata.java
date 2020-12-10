package com.frontegg.examples.spring.audit;

import com.frontegg.sdk.audit.model.MetadataObject;

public class MyCustomAuditMetadata implements MetadataObject {
    private String entityName;
    private String field1;
    private String field2;

    @Override
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}