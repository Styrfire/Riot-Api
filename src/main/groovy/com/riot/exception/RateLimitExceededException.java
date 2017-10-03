package com.riot.exception;

class RateLimitExceededException extends RiotApiException
{
	RateLimitExceededException()
	{
		super("Error 429: Rate Limit Exceeded");
	}
}
