package com.riot.exception;

class ForbiddenException extends Exception
{
	ForbiddenException()
	{
		super("Error 403: Forbidden");
	}
}
