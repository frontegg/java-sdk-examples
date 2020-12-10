package com.frontegg.examples.spring;

import com.frontegg.sdk.middleware.context.FronteggContext;
import com.frontegg.sdk.middleware.context.FronteggContextResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyContextResolver implements FronteggContextResolver
{
	@Override
	public FronteggContext resolveContext(HttpServletRequest httpServletRequest)
	{
		return new FronteggContext("my-tenant", "my-user");
	}
}
