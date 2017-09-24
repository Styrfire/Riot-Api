package com.riot.exception;

class MethodNotAllowedException extends Exception
{
	MethodNotAllowedException()
	{
		super("Error 405: Method Not Allowed");
	}
}
