package com.keepa.api.backend.structs;

import com.google.gson.Gson;

import static com.keepa.api.backend.KeepaAPI.ResponseStatus;

/**
 * Represents the response of an API request
 * @deprecated Use {@link Response} instead.
 */
public final class KeepaProductResponse {

	/**
	 * Server time when response was sent.
	 */
	public long timestamp = System.currentTimeMillis();

	/**
	 * States how many ASINs may be requested before the assigned API contingent is depleted.
	 * If the contigent is depleted, HTTP status code 503 will be delivered with the message:
	 * "You are submitting requests too quickly and your requests are being throttled."
	 */
	public int tokensLeft = 0;

	/**
	 * Milliseconds till new tokens are generated. Use this if your contigent is depleted to wait before you try a new request. Tokens are generated every 5 minutes.
	 */
	public int refillIn = 0;

	/**
	 * Token refill rate per minute.
	 */
	public int refillRate = 0;

	/**
	 * time the request took, in milliseconds
	 */
	public long requestTime = 0;

	/**
	 * Status of the request.
	 */
	public ResponseStatus status = ResponseStatus.PENDING;

	/**
	 * Results of the product request
	 */
	public Product products[] = null; // Requested Data

	static public KeepaProductResponse REQUEST_FAILED = new KeepaProductResponse();
	static public KeepaProductResponse REQUEST_REJECTED = new KeepaProductResponse();
	private static KeepaProductResponse NOT_ENOUGH_TOKEN = new KeepaProductResponse();

	static {
		REQUEST_FAILED.status = ResponseStatus.FAIL;
		REQUEST_REJECTED.status = ResponseStatus.REQUEST_REJECTED;
		NOT_ENOUGH_TOKEN.status = ResponseStatus.NOT_ENOUGH_TOKEN;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
