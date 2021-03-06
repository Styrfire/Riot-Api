package com.riot.exception;

public class RiotExceptionCreator
{
	public static void throwException(int responseCode) throws RiotApiException
	{
		switch (responseCode)
		{
			case 400:
				throw new BadRequestException();
			case 401:
				throw new UnauthorizedException();
			case 403:
				throw new ForbiddenException();
			case 404:
				throw new DataNotFoundException();
			case 405:
				throw new MethodNotAllowedException();
			case 415:
				throw new UnsupportedMediaTypeException();
			case 429:
				throw new RateLimitExceededException();
			case 500:
				throw new InternalServerErrorException();
			case 502:
				throw new BadGatewayException();
			case 503:
				throw new ServiceUnavailableException();
			case 504:
				throw new GatewayTimeoutException();
			default:
				throw new RiotApiException("Oops... Something went wrong...");
		}
	}
}
