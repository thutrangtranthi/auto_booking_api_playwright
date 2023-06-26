package com.api.tests.booking;

import com.api.data.booking.BookingDataBuilder;
import com.api.data.booking.PartialBookingData;
import com.api.data.booking.WrongPartialUpdateBookingData;
import com.api.service.auth.CreateTokenService;
import com.api.service.booking.GetListBookingIdService;
import com.api.service.booking.PartialUpdateBookingService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PartialUpdateBookingTest extends BaseTest {

    @Test(description = "Should partial update booking successfully")
    public void shouldUpdatePartialBookingSuccessfully() {
        PartialUpdateBookingService partialUpdateBookingService = new PartialUpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        PartialBookingData partialBookingData = BookingDataBuilder.getPartialBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = partialUpdateBookingService.getResponse(token, partialBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();

        assertEquals(responseStatusCode, 200, "Response status code should be 200");

        JSONObject responseObject = new JSONObject(response.text());

        assertEquals(partialBookingData.getFirstname(), responseObject.get("firstname"));
        assertEquals(partialBookingData.getTotalprice(), responseObject.get("totalprice"));
    }

    @Test(description = "Should response status code 400 when partial update booking with wrong body")
    public void shouldUpdatePartialBookingFailWithWrongBody() {
        PartialUpdateBookingService partialUpdateBookingService = new PartialUpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        WrongPartialUpdateBookingData partialBookingDataWrong = BookingDataBuilder.getWrongPartialUpdateBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = partialUpdateBookingService.getResponseWithWrongBody(token, partialBookingDataWrong, bookingId, requestManager);
        int responseStatusCode = response.status();

        assertEquals(responseStatusCode, 400, "Response status code should be 400 when partial update booking with wrong body");
    }

    @Test(description = "Should response status code 403 when partial  update booking with wrong token")
    public void shouldUpdatePartialBookingFailWithWrongToken() {
        PartialUpdateBookingService partialUpdateBookingService = new PartialUpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        PartialBookingData partialBookingData = BookingDataBuilder.getPartialBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = partialUpdateBookingService.getResponseWithWrongToken(token, partialBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();

        assertEquals(responseStatusCode, 403, "Response status code should be 403 when partial  update booking with wrong token");
    }

    @Test(description = "Should response status code 404 when partial update booking with wrong url")
    public void shouldUpdatePartialBookingFailWithWrongUrl() {
        PartialUpdateBookingService partialUpdateBookingService = new PartialUpdateBookingService();
        CreateTokenService createTokenService = new CreateTokenService();
        GetListBookingIdService getListBookingIdService = new GetListBookingIdService();
        PartialBookingData partialBookingData = BookingDataBuilder.getPartialBookingData();
        String token = createTokenService.getTokenValue(createTokenData, requestManager);
        int bookingId = getListBookingIdService.getBookingId(requestManager, 1);

        APIResponse response = partialUpdateBookingService.getResponseWithWrongUrl(token, partialBookingData, bookingId, requestManager);
        int responseStatusCode = response.status();

        assertEquals(responseStatusCode, 404, "Response status code should be 404 when partial update booking with wrong url");

    }
}
