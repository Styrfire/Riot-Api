package com.riot.exception;

class DataNotFoundException extends Exception
{
	DataNotFoundException()
	{
		super("Error 404: Data Not Found");
	}
}
