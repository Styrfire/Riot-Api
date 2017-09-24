package com.riot.exception;

class BadRequestException extends Exception
{
	BadRequestException()
	{
		super("Error 400: Bad Request");
	}
}
