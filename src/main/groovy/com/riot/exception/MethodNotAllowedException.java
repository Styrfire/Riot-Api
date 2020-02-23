package com.riot.exception;

class MethodNotAllowedException extends RiotApiException
{
	MethodNotAllowedException()
	{
		super("Error 405: Method Not Allowed");
	}
}
