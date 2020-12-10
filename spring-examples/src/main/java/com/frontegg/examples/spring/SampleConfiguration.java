package com.frontegg.examples.spring;

import com.frontegg.sdk.api.client.ApiClient;
import com.frontegg.sdk.config.FronteggConfig;
import com.frontegg.sdk.events.EventsClient;
import com.frontegg.sdk.middleware.authenticator.FronteggAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfiguration
{
	@Bean
	public EventsClient eventsClient(
			FronteggAuthenticator fronteggAuthenticator, ApiClient apiClient, FronteggConfig config
	)
	{
		return new EventsClient(fronteggAuthenticator, apiClient, config);
	}
}
