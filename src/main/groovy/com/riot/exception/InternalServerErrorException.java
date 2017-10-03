package com.riot.exception;

class InternalServerErrorException extends RiotApiException
{
	InternalServerErrorException()
	{
		super("Error 500: Internal Server Error");
	}
}
