package com.api.data.booking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WrongPartialUpdateBookingData {
    private String high;
    private String abcd;
}
