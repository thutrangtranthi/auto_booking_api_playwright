package com.api.service.booking;

import com.api.data.booking.BookingData;
import com.api.data.booking.WrongBookingData;
import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

public class CreateBookingService {

    public APIResponse getResponse(BookingData bookingData, RequestManager requestManager) {
        return requestManager.postRequest("/booking", RequestOptions.create()
                .setData(bookingData));
    }

    public APIResponse getResponseWithWrongUrl(BookingData bookingData, RequestManager requestManager) {
        return requestManager.postRequest("/booking123", RequestOptions.create()
                .setData(bookingData));
    }

    public APIResponse getResponseWithWrongBody(WrongBookingData wrongBookingData, RequestManager requestManager) {
        return requestManager.postRequest("/booking", RequestOptions.create()
                .setData(wrongBookingData));
    }


}
