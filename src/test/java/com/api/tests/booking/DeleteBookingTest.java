package com.api.tests.booking;

import com.api.service.auth.CreateTokenService;
import com.api.service.booking.DeleteBookingService;
import com.api.service.booking.GetListBookingIdService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeleteBookingTest extends BaseTest {

    @Test(description = "Should delete booking successfully")
    public void shouldDeleteBookingSuccessfully() {
        DeleteBookingService deleteBookingService = new DeleteBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();

        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 0);
        int responseStatusCode = deleteBookingService.getStatusCode(token, bookingId, requestManager);
        assertEquals(responseStatusCode, 200, "Response status code for delete successfully should be 200");
    }

    @Test(description = "Should response status code 404 when delete booking with wrong url")
    public void shouldDeleteBookingFailWithWrongUrl() {
        DeleteBookingService deleteBookingService = new DeleteBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();

        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 0);
        APIResponse response = deleteBookingService.getResponseWithWrongUrl(token, bookingId, requestManager);
        assertEquals(response.status(), 404, "Response status code should be 404 when delete booking with wrong url");

    }

    @Test(description = "Should response status code 403 when delete booking with wrong token")
    public void shouldDeleteBookingFailWithWrongToken() {
        DeleteBookingService deleteBookingService = new DeleteBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();

        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 0);
        APIResponse response = deleteBookingService.getResponseWithWrongToken(token, bookingId, requestManager);
        assertEquals(response.status(), 403, "Response status code should be 403 when delete booking with wrong token");
    }

}
