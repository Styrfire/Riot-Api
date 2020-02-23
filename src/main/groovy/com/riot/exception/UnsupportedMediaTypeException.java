package com.riot.exception;

class UnsupportedMediaTypeException extends RiotApiException
{
	UnsupportedMediaTypeException()
	{
		super("Error 415: Unsupported Media Type");
	}
}
