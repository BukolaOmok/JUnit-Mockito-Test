package com.pluralsight.tddjunit5.database;

import com.pluralsight.tddjunit5.airport.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private Map<String, String> registeredUsers = new HashMap<>();
    List<List<String>> queriedData = new ArrayList<>();

    public boolean login(Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        return registeredUsers.keySet().contains(username) &&
                registeredUsers.get(username).equals(password);
    }

    public List<List<String>> queryFlightsDatabase() {
        return queriedData;
    }

    public double averageDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToDouble(Flight::getDistance)
                .average()
                .getAsDouble();
    }

    public int minimumDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToInt(Flight::getDistance)
                .min()
                .getAsInt();
    }

    public int maximumDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToInt(Flight::getDistance)
                .max()
                .getAsInt();
    }
}
