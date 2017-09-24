package com.riot.exception;

class BadGatewayException extends Exception
{
	BadGatewayException()
	{
		super("Error 502: Bad Gateway");
	}
}
