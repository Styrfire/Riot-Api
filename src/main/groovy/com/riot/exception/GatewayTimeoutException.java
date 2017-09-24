package com.riot.exception;

class GatewayTimeoutException extends Exception
{
	GatewayTimeoutException()
	{
		super("Error 504: Gateway Timeout");
	}
}
