package com.frontegg.examples.spring;

import com.frontegg.sdk.middleware.context.FronteggContext;
import com.frontegg.sdk.middleware.context.FronteggContextHolder;
import com.frontegg.sdk.middleware.context.FronteggContextResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyContextResolver implements FronteggContextResolver
{
	@Override
	public void resolveContext(HttpServletRequest httpServletRequest)
	{
		FronteggContext fronteggContext = new FronteggContext();
		fronteggContext.setTenantId("my-tenant");
		fronteggContext.setUserId("my-user");
		FronteggContextHolder.setContext(fronteggContext);
	}
}
