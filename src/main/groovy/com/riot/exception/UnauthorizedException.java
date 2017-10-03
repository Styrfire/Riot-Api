package com.riot.exception;

class UnauthorizedException extends RiotApiException
{
	UnauthorizedException()
	{
		super("Error 401: Unauthorized");
	}
}
