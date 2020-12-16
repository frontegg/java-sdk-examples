package com.frontegg.examples.spring.audit;

import com.frontegg.sdk.audit.AuditsClient;
import com.frontegg.sdk.audit.model.AuditsFilter;
import com.frontegg.sdk.audit.response.AuditResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/audit")
public class TriggerAuditController {

    @Autowired
    private AuditsClient auditClient;

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> sendAudit(@RequestBody AuditModel auditModel) {

        auditClient.sendAudit(auditModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/metadata",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> setMetadata(@RequestBody MyCustomAuditMetadata metadata) {

        auditClient.setAuditsMetadata(metadata);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/metadata",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getMetadata() {
        return new ResponseEntity<>(auditClient.getAuditsMetadata(), HttpStatus.OK);
    }

    @RequestMapping(value = "/stats/{tenantId}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getStats(@PathVariable String tenantId) {
        return new ResponseEntity<>(auditClient.getAuditsStats(tenantId), HttpStatus.OK);
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AuditResponse> search(@RequestParam String tenantId,
                                                @RequestParam String filter,
                                                @RequestParam String sortBy,
                                                @RequestParam String sortDirection,
                                                @RequestParam int offset,
                                                @RequestParam int count) {
        AuditsFilter auditsFilter = new AuditsFilter();
        auditsFilter.setTenantId(tenantId);
        auditsFilter.setCount(count);
        auditsFilter.setOffset(offset);
        auditsFilter.setFilter(filter);
        auditsFilter.setSortBy(sortBy);
        auditsFilter.setSortDirection(sortDirection);
        AuditResponse<AuditModel> response = auditClient.getAudits(auditsFilter, AuditModel.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
