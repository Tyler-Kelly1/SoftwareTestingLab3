package org.example;

import com.baarsch_bytes.Exceptions.GuestAgeReservationException;
import com.baarsch_bytes.Exceptions.NightReservationException;
import com.baarsch_bytes.Exceptions.ReservationException;

public class ReserveMyPark {

    private final double BASE_PRICE = 50;
    private final int MIN_STAY = 1;
    private final int MAX_STAY = 14;
    private final int CHILD = 12;
    private final double CHILD_DISCOUNT = .5;
    private final int SENIOR = 65;
    private final double SENIOR_DISCOUNT = .2;
    private final double RESIDENT_DISCOUNT = 10;
    private final double VETERAN_DISCOUNT = .1;


    public double calculateStayPrice(int nights, int guestAge,
                       boolean isArkansasResident,
                       boolean hasVeteranDiscount) throws ReservationException {
        if (nights < MIN_STAY || nights > MAX_STAY)
            throw new NightReservationException(nights);

        if (guestAge < 0)
            throw new GuestAgeReservationException(guestAge);

        // calculate base rate
        double price = nights * BASE_PRICE;

        // apply age-based discounts
        if (guestAge <= CHILD)
            price-= (price * CHILD_DISCOUNT);
        if (guestAge >= SENIOR)
            price-= (price * SENIOR_DISCOUNT);

        // apply status-based discounts
        if (isArkansasResident)
            price-=RESIDENT_DISCOUNT;
        if (hasVeteranDiscount)
            price-= (price * VETERAN_DISCOUNT);

        return price;
    }


}
