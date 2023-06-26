package com.api.tests.booking;

import com.api.service.booking.GetBookingService;
import com.api.service.booking.GetListBookingIdService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class GetBookingTest extends BaseTest {

    @Test(description = "Should get booking info successfully")
    public void shouldGetBookingSuccessfully() {
        GetBookingService getBookingService = new GetBookingService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        int bookingId = getListBookingIdService.getBookingId(requestManager, 0);
        APIResponse response = getBookingService.getResponse(bookingId, requestManager);
        assertEquals(response.status(), 200, "Response status code should be 200");
        log.info("Response body: " + new JSONObject(response.text()));
    }

    @Test(description = "Should response status code 404 when get booking info with wrong url")
    public void shouldGetBookingFailWithWrongUrl() {
        GetBookingService getBookingService = new GetBookingService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        int bookingId = getListBookingIdService.getBookingId(requestManager, 0);
        APIResponse response = getBookingService.getResponseWithWrongUrl(bookingId, requestManager);
        assertEquals(response.status(), 404, "Response status code should be 404 when get booking info with wrong url");
    }

}
