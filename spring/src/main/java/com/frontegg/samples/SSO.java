package com.frontegg.samples;

import com.frontegg.sdk.api.client.ApiClient;
import com.frontegg.sdk.config.FronteggConfig;
import com.frontegg.sdk.middleware.authenticator.FronteggAuthenticator;
import com.frontegg.sdk.sso.SamlResponse;
import com.frontegg.sdk.sso.SsoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SSO
{
	private final FronteggConfig config;
	private final ApiClient apiClient;
	private final FronteggAuthenticator authenticator;

	private final SsoClient ssoClient;

	@Autowired
	public SSO(ApiClient apiClient, FronteggAuthenticator authenticator, FronteggConfig config)
	{
		this.apiClient = apiClient;
		this.authenticator = authenticator;
		this.config = config;

		this.ssoClient = new SsoClient(this.authenticator, this.apiClient, this.config);
	}

	@RequestMapping(value = "/login",
					method = RequestMethod.POST)
	public void login(@RequestBody SamlRequest request, HttpServletResponse response) throws IOException
	{
		String location = this.ssoClient.preLogin(request.getPayload());
		response.sendRedirect(location);
	}

	@RequestMapping(value = "/auth/saml/callback",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Object samlCallback(SamlResponse samlResponse)
	{
		return this.ssoClient.postLogin(samlResponse);
	}

}
