package com.baarsch_bytes.Exceptions;

public class GuestAgeReservationException extends ReservationException {
    public GuestAgeReservationException(int guestAge) {
        super("An invalid guestAge was selected: " + guestAge);
    }
}
