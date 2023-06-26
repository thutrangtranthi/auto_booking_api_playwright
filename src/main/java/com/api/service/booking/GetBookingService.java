package com.api.service.booking;

import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;

public class GetBookingService {
    public APIResponse getResponse(int bookingId, RequestManager requestManager) {
        return requestManager.getRequest("/booking/" + bookingId);
    }

    public APIResponse getResponseWithWrongUrl(int bookingId, RequestManager requestManager) {
        return requestManager.getRequest("/booking/" + bookingId + "/abcd");
    }

}
