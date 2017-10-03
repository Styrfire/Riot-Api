package com.riot.exception;

class ForbiddenException extends RiotApiException
{
	ForbiddenException()
	{
		super("Error 403: Forbidden. Check your api key!");
	}
}
