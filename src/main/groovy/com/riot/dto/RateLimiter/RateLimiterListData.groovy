package com.riot.dto.RateLimiter

import com.google.common.util.concurrent.RateLimiter

class RateLimiterListData
{
	RateLimiter rateLimiter
	int numOfCalls
	int maxNumOfCalls
	int timeWindowInSeconds
}
