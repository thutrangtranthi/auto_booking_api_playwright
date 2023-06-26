package com.api.service.booking;

import com.api.data.booking.PartialBookingData;
import com.api.data.booking.WrongPartialUpdateBookingData;
import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class PartialUpdateBookingService {

    public APIResponse getResponse(String token,
                                   PartialBookingData partialUpdateBookingData,
                                   int bookingId,
                                   RequestManager requestManager) {
        return requestManager.patchRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(partialUpdateBookingData)
                .setHeader("Cookie", "token=" + token));
    }

    public APIResponse getResponseWithWrongBody(String token,
                                   WrongPartialUpdateBookingData partialUpdateBookingDataWrong,
                                   int bookingId,
                                   RequestManager requestManager) {
        return requestManager.patchRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(partialUpdateBookingDataWrong)
                .setHeader("Cookie", "token=" + token));
    }

    public APIResponse getResponseWithWrongToken(String token,
                                                 PartialBookingData partialUpdateBookingData,
                                                int bookingId,
                                                RequestManager requestManager) {
        return requestManager.patchRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(partialUpdateBookingData)
                .setHeader("Cookie", "token=" + token + "abcd123"));
    }

    public APIResponse getResponseWithWrongUrl(String token,
                                                 PartialBookingData partialUpdateBookingData,
                                                 int bookingId,
                                                 RequestManager requestManager) {
        return requestManager.patchRequest("/booking///" + bookingId, RequestOptions.create()
                .setData(partialUpdateBookingData)
                .setHeader("Cookie", "token=" + token));
    }

}
