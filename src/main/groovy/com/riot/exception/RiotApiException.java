package com.riot.exception;

public class RiotApiException extends Exception
{
	public RiotApiException(String errorMsg)
	{
		super(errorMsg);
	}
}
