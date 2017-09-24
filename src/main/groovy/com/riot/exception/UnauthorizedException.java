package com.riot.exception;

class UnauthorizedException extends Exception
{
	UnauthorizedException()
	{
		super("Error 401: Unauthorized");
	}
}
