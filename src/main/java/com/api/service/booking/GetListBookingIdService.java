package com.api.service.booking;

import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import org.json.JSONArray;

public class GetListBookingIdService {
    public APIResponse getResponse(RequestManager requestManager) {
        return requestManager.getRequest("/booking");
    }

    public int getStatusCode(RequestManager requestManager) {
        return getResponse(requestManager).status();
    }

    public int getBookingId(RequestManager requestManager, int indexOfBooking) {
        JSONArray jsonArray = new JSONArray(getResponse(requestManager).text());
        return jsonArray
                .getJSONObject(indexOfBooking)
                .getInt("bookingid");
    }

    public APIResponse getResponseWithWrongUrl(RequestManager requestManager) {
        return requestManager.getRequest("/booking//");
    }

}
