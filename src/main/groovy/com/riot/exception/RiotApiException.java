package com.riot.exception;

public class RiotApiException extends Exception
{
	private final String errorMsg;

	public RiotApiException(String errorMsg)
	{
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}
}
