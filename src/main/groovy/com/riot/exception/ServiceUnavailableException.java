package com.riot.exception;

class ServiceUnavailableException extends RiotApiException
{
	ServiceUnavailableException()
	{
		super("Error 503: Service Unavailable");
	}
}
