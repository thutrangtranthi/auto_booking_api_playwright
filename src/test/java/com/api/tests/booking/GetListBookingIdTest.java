package com.api.tests.booking;

import com.api.service.booking.GetListBookingIdService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class GetListBookingIdTest extends BaseTest {

    @Test(description = "Should get list booking id successfully")
    public void shouldGetListBookingIdSuccessfully() {
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        int responseStatusCode = getListBookingIdService.getStatusCode(requestManager);
        assertEquals(responseStatusCode, 200, "Status code response should be 200");
    }

    @Test(description = "Should response status code 404 when get list booking id with wrong url")
    public void shouldGetListBookingIdFailWithWrongUrl() {
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        APIResponse response = getListBookingIdService.getResponseWithWrongUrl(requestManager);
        assertEquals(response.status(), 404, "Status code response should be 404 when get list booking id with wrong url");
    }

}
