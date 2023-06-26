package com.api.service.booking;

import com.api.data.booking.BookingData;
import com.api.data.booking.WrongBookingData;
import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class UpdateBookingService {
    public APIResponse getResponse(String token,
                                   BookingData updateBookingData,
                                   int bookingId,
                                   RequestManager requestManager) {
        return requestManager.putRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(updateBookingData)
                .setHeader("Cookie", "token=" + token));
    }

    public APIResponse getResponseWithWrongUrl(String token,
                                   BookingData updateBookingData,
                                   int bookingId,
                                   RequestManager requestManager) {
        return requestManager.putRequest("/booking/" + bookingId + "/abcd", RequestOptions.create()
                .setData(updateBookingData)
                .setHeader("Cookie", "token=" + token));
    }

    public APIResponse getResponseWithWrongToken(String token,
                                               BookingData updateBookingData,
                                               int bookingId,
                                               RequestManager requestManager) {
        return requestManager.putRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(updateBookingData)
                .setHeader("Cookie", "token=" + token + "abcdef"));
    }

    public APIResponse getResponseWithWrongBody(String token,
                                                 WrongBookingData updateBookingDataWrong,
                                                 int bookingId,
                                                 RequestManager requestManager) {
        return requestManager.putRequest("/booking/" + bookingId, RequestOptions.create()
                .setData(updateBookingDataWrong)
                .setHeader("Cookie", "token=" + token));
    }



}
