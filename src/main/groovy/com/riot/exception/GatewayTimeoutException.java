package com.riot.exception;

class GatewayTimeoutException extends RiotApiException
{
	GatewayTimeoutException()
	{
		super("Error 504: Gateway Timeout");
	}
}
