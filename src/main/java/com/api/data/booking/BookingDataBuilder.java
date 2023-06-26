package com.api.data.booking;

import lombok.Builder;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

@Builder
public class BookingDataBuilder {

    private static final Faker FAKER = new Faker();

    public static BookingData getBookingData() {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        return BookingData.builder()
                .firstname(FAKER.name()
                        .firstName())
                .lastname(FAKER.name()
                        .lastName())
                .totalprice(FAKER.number()
                        .numberBetween(1, 2000))
                .depositpaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin(formatter.format(FAKER.date()
                                .past(20, TimeUnit.DAYS)))
                        .checkout(formatter.format(FAKER.date()
                                .future(5, TimeUnit.DAYS)))
                        .build())
                .additionalneeds("Breakfast")
                .build();

    }

    public static PartialBookingData getPartialBookingData() {
        return PartialBookingData.builder()
                .firstname(FAKER.name()
                        .firstName())
                .totalprice(FAKER.number()
                        .numberBetween(100, 5000))
                .build();
    }

    public static WrongBookingData getWrongBookingData() {
        return WrongBookingData.builder()
                .firstname(FAKER.name()
                        .firstName())
                .abcd(FAKER.name()
                        .fullName())
                .build();
    }

    public static WrongPartialUpdateBookingData getWrongPartialUpdateBookingData() {
        return WrongPartialUpdateBookingData.builder()
                .high(FAKER.name()
                        .firstName())
                .abcd(FAKER.name()
                        .fullName())
                .build();
    }
}
