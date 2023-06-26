package com.api.service.booking;

import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class DeleteBookingService {
    public APIResponse getResponse(String token, int bookingId, RequestManager requestManager) {
        return requestManager.deleteRequest("/booking/" + bookingId, RequestOptions.create()
                .setHeader("Cookie", "token=" + token));
    }

    public int getStatusCode(String token, int bookingId, RequestManager requestManager) {
        return getResponse(token, bookingId, requestManager).status();
    }

    public APIResponse getResponseWithWrongUrl(String token, int bookingId, RequestManager requestManager) {
        return requestManager.deleteRequest("/booking///" + bookingId, RequestOptions.create()
                .setHeader("Cookie", "token=" + token));
    }

    public APIResponse getResponseWithWrongToken(String token, int bookingId, RequestManager requestManager) {
        return requestManager.deleteRequest("/booking/" + bookingId, RequestOptions.create()
                .setHeader("Cookie", "token=" + token + "abcd123"));
    }

}
