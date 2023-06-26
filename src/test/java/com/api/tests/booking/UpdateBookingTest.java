package com.api.tests.booking;

import com.api.data.booking.BookingData;
import com.api.data.booking.BookingDataBuilder;
import com.api.data.booking.WrongBookingData;
import com.api.service.auth.CreateTokenService;
import com.api.service.booking.GetListBookingIdService;
import com.api.service.booking.UpdateBookingService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdateBookingTest extends BaseTest {

    @Test(description = "Should update booking successfully")
    public void shouldUpdateBookingSuccessfully() {
        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        BookingData updateBookingData = BookingDataBuilder.getBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = updateBookingService.getResponse(token, updateBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 200, "Response status code should be 200");

        JSONObject responseObject = new JSONObject(response.text());
        JSONObject bookingDatesObject = responseObject.getJSONObject("bookingdates");

        assertEquals(updateBookingData.getFirstname(), responseObject.get("firstname"));
        assertEquals(updateBookingData.getBookingdates()
                .getCheckout(), bookingDatesObject.get("checkout"));
    }

    @Test(description = "Should response status code 404 when update booking with wrong url")
    public void shouldUpdateBookingFailWithWrongUrl() {
        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        BookingData updateBookingData = BookingDataBuilder.getBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = updateBookingService.getResponseWithWrongUrl(token, updateBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 404, "Response status code should be 404 when update booking with wrong url");
    }

    @Test(description = "Should response status code 403 when update booking with wrong token")
    public void shouldUpdateBookingFailWithWrongToken() {
        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        BookingData updateBookingData = BookingDataBuilder.getBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = updateBookingService.getResponseWithWrongToken(token, updateBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 403, "Response status code should be 403 when update booking with wrong token");
    }

    @Test(description = "Should response status code 400 when update booking with wrong body")
    public void shouldUpdateBookingFailWithWrongBody() {
        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        WrongBookingData updateBookingDataWrong = BookingDataBuilder.getWrongBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = updateBookingService.getResponseWithWrongBody(token, updateBookingDataWrong, bookingId, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 400, "Response status code should be 400 when update booking with wrong body");
    }


}
