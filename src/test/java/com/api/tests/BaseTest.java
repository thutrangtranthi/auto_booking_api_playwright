package com.api.tests;

import java.util.HashMap;
import java.util.Map;

import com.api.data.auth.CreateTokenData;
import com.api.data.booking.BookingData;
import com.api.data.booking.BookingDataBuilder;
import com.api.service.RequestManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.api.data.auth.CreateTokenBuilder.setTokenData;

public class BaseTest {

    protected RequestManager requestManager;
    protected BookingData bookingData;
    protected CreateTokenData createTokenData;

    @BeforeTest
    public void setupBase() {
        requestManager = new RequestManager();
        requestManager.createPlaywright();
        bookingData = BookingDataBuilder.getBookingData();
        createTokenData = setTokenData();
        final String baseUrl = "https://restful-booker.herokuapp.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("Accept", "application/json");
        requestManager.setApiRequestContext(baseUrl, headers);
    }

    @AfterTest
    public void tearDown() {
        requestManager.disposeAPIRequestContext();
        requestManager.closePlaywright();
    }
}
