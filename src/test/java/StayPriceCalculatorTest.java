import com.baarsch_bytes.Exceptions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;


public class StayPriceCalculatorTest {

    private final org.example.ReserveMyPark reserveMyPark = new org.example.ReserveMyPark();


    @ParameterizedTest(name = "Case {index} => nights={0}, age={1}, resident={2}, vet={3} -> ${4}")
    @CsvFileSource(resources = "/test-data/valid-test.csv", numLinesToSkip = 1)
    void testCalculateStayPriceValid(


            int nights,
            int guestAge,
            boolean isArResident,
            boolean hasVetDiscount,
            double expectedPrice) {

        try{
            double actualPrice = reserveMyPark.calculateStayPrice(nights, guestAge, isArResident, hasVetDiscount);
            assertEquals(expectedPrice, actualPrice, 0.001);
        }
        catch (com.baarsch_bytes.Exceptions.ReservationException e) {
            fail();
        }

    }

    @ParameterizedTest(name = "Exception Case {index} => nights={0}, age={1} -> throws {4}")
    @CsvFileSource(resources = "/test-data/exception-test.csv", numLinesToSkip = 1)
    void testCalculateStayPriceExceptions(
            int nights,
            int guestAge,
            boolean isArResident,
            boolean hasVetDiscount,
            String expectedException) {

        if ("NightReservationException".equals(expectedException)) {
            assertThrows(NightReservationException.class, () ->
                    reserveMyPark.calculateStayPrice(nights, guestAge, isArResident, hasVetDiscount)
            );
        } else if ("GuestAgeReservationException".equals(expectedException)) {
            assertThrows(GuestAgeReservationException.class, () ->
                    reserveMyPark.calculateStayPrice(nights, guestAge, isArResident, hasVetDiscount)
            );
        } else {
            fail("Unhandled exception type specified in CSV: " + expectedException);
        }
    }






}
