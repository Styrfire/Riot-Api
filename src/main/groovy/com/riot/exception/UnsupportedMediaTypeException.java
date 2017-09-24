package com.riot.exception;

class UnsupportedMediaTypeException extends Exception
{
	UnsupportedMediaTypeException()
	{
		super("Error 415: Unsupported Media Type");
	}
}
