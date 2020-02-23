package com.riot.exception;

class BadGatewayException extends RiotApiException
{
	BadGatewayException()
	{
		super("Error 502: Bad Gateway");
	}
}
