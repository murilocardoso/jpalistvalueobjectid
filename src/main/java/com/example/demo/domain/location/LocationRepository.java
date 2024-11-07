package com.example.demo.domain.location;

public interface LocationRepository {
    void persist(Location aLocation);
    Location findByIdOrThrow(LocationId aLocationId);
}

