package com.riot.exception;

class InternalServerErrorException extends Exception
{
	InternalServerErrorException()
	{
		super("Error 500: Internal Server Error");
	}
}
