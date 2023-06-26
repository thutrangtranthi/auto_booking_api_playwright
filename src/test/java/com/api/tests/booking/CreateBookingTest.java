package com.api.tests.booking;

import com.api.data.booking.BookingDataBuilder;
import com.api.data.booking.WrongBookingData;
import com.api.service.booking.CreateBookingService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateBookingTest extends BaseTest {

    @Test(description = "Should create booking successfully")
    public void shouldCreateBookingSuccessfully() {
        CreateBookingService createBookingService = new CreateBookingService();
        APIResponse response = createBookingService.getResponse(bookingData, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 200, "Response status code should be 200");
        JSONObject responseObject = new JSONObject(response.text());
        assertNotNull(responseObject.get("bookingid"));
        JSONObject bookingObject = responseObject.getJSONObject("booking");
        JSONObject bookingDatesObject = bookingObject.getJSONObject("bookingdates");
        assertEquals(bookingData.getFirstname(), bookingObject.get("firstname"));
        assertEquals(bookingData.getBookingdates()
                .getCheckin(), bookingDatesObject.get("checkin"));

    }

    @Test(description = "Should response status code 404 when create booking with wrong url")
    public void shouldCreateBookingFailWithWrongUrl() {
        CreateBookingService createBookingService = new CreateBookingService();
        APIResponse response = createBookingService.getResponseWithWrongUrl(bookingData, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 404, "Response status code should be 404 when create booking with wrong url");
    }

    @Test(description = "Should response status code 400 when create booking with wrong body")
    public void shouldCreateBookingFailWithWrongBody() {
        WrongBookingData wrongBookingData = BookingDataBuilder.getWrongBookingData();
        CreateBookingService createBookingService = new CreateBookingService();
        APIResponse response = createBookingService.getResponseWithWrongBody(wrongBookingData, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 400, "Response status code should be 400 when create booking with wrong body");
    }

}
