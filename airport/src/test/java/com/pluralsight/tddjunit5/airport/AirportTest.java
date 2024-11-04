package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassengerTest {
            @Test
            @DisplayName("Then you can add and remove him from an economy flight")
            public void testEconomyFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersSet().size())
                );
            }

            @RepeatedTest(5)
            @DisplayName("Then you cannot add him to an economy flight more than once")
            public void testEconomyFlightUsualPassengerAddedOnlyOnce() {
                for (int i =0; i < 5; i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll("Verify a usual passenger can be added to an economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mike"))
                );
            }

        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassengerTest {
            @Test
            @DisplayName("Then you can add but cannot remove him from an economy flight")
            public void testEconomyFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP Passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                        () -> assertEquals(false, economyFlight.removePassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size())
                );
            }
        }

        @RepeatedTest(5)
        @DisplayName("Then you cannot add him to an economy flight more than once")
        public void testEconomyFlightVipPassengerAddedOnlyOnce() {
            verifyVipPassengerAddedOnlyOnce(economyFlight, john);
        }
    }


    @DisplayName("Given there is a business flight")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassengerTest {
            @Test
            @DisplayName("Then you cannot add or remove him from a business flight")
            public void testBusinessFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and a business flight",
                        () -> assertEquals("2", businessFlight.getId()),
                        () -> assertEquals(false, businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassengerTest {
            @Test
            @DisplayName("Then you can add but cannot remove him from business flight")
            public void testBusinessFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a business flight",
                        () -> assertEquals("2", businessFlight.getId()),
                        () -> assertEquals(true, businessFlight.addPassenger(john)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(john)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size())
                );
            }

            @RepeatedTest(5)
            @DisplayName("Then you cannot add him to an economy flight more than once")
            public void testBusinessFlightVipPassengerAddedOnlyOnce() {
                verifyVipPassengerAddedOnlyOnce(businessFlight, john);
            }
        }
    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassengerTest {
            @Test
            @DisplayName("Then you cannot add or remove him from a premium flight")
            public void testPremiumFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and a premium flight",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP Passenger")
        class VipPassengerTest {
            @Test
            @DisplayName("Then you can add and remove him from a premium flight")
            public void testPremiumFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a premium flight",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(true, premiumFlight.addPassenger(john)),
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(john)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }

            @RepeatedTest(5)
            @DisplayName("Then you cannot add him to an economy flight more than once")
            public void testPremiumFlightVipPassengerAddedOnlyOnce() {
                verifyVipPassengerAddedOnlyOnce(premiumFlight, john);
            }
        }
    }


    public void verifyVipPassengerAddedOnlyOnce(Flight flight, Passenger passenger) {
        for (int i = 0; i < 5; i++) {
            flight.addPassenger(passenger);
        }
        assertAll("Verify a VIP passenger can be added to flight only once",
                () -> assertEquals(1, flight.getPassengersSet().size()),
                () -> assertTrue(flight.getPassengersSet().contains(passenger)),
                () -> assertTrue(new ArrayList<>(flight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }

}


