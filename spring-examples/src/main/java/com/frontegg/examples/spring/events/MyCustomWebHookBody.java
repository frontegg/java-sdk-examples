package com.frontegg.examples.spring.events;

import com.frontegg.sdk.events.channels.WebhookBody;

public class MyCustomWebHookBody implements WebhookBody
{
	private String field;

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}
}
