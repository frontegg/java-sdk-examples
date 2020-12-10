package com.frontegg.examples.spring.audit;

import com.frontegg.sdk.audit.model.Auditable;

public class AuditModel implements Auditable {
    private String tenantId;
    private String field1;
    private String field2;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
