package com.riot.exception;

class DataNotFoundException extends RiotApiException
{
	DataNotFoundException()
	{
		super("Error 404: Data Not Found");
	}
}
