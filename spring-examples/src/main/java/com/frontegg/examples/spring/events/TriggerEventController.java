package com.frontegg.examples.spring.events;


import com.frontegg.sdk.events.EventsClient;
import com.frontegg.sdk.events.model.EventResponse;
import com.frontegg.sdk.events.model.EventStatuses;
import com.frontegg.sdk.events.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class TriggerEventController {
    @Autowired
    private EventsClient eventsClient;

    @RequestMapping(value = "/trigger",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponse> triggerEvents(@RequestParam String eventKey,
                                                       @RequestParam String tenantId,
                                                       @RequestParam String title,
                                                       @RequestParam String description) {

        EventProperties eventProperties = new DefaultEventProperties(title, description);
        TriggerOptions<EventProperties> options = new TriggerOptions<>();
        MyCustomWebHookBody webHookBody = new MyCustomWebHookBody();
        webHookBody.setField("asdas");

        ChannelsConfiguration channelsConfiguration = ChannelsConfigurationBuilder.builder()
                .webhook(webHookBody)
                .build();

        options.setChannels(channelsConfiguration);
        options.setEventKey(eventKey);
        options.setTenantId(tenantId);
        options.setProperties(eventProperties);
        EventResponse response = this.eventsClient.trigger(options);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{eventId}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventStatuses> getEventStatus(@PathVariable String eventId) {
        EventStatuses statuses = eventsClient.getEventStatus(eventId);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }
}
