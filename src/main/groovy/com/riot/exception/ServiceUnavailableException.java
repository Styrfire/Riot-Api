package com.riot.exception;

class ServiceUnavailableException extends Exception
{
	ServiceUnavailableException()
	{
		super("Error 503: Service Unavailable");
	}
}
