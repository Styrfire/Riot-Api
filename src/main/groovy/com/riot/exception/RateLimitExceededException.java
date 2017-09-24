package com.riot.exception;

class RateLimitExceededException extends Exception
{
	RateLimitExceededException()
	{
		super("Error 429: Rate Limit Exceeded");
	}
}
