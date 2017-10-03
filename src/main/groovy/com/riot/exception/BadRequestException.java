package com.riot.exception;

class BadRequestException extends RiotApiException
{
	BadRequestException()
	{
		super("Error 400: Bad Request");
	}
}
