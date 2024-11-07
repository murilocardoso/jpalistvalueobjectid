package com.example.demo.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.location.Location;
import com.example.demo.domain.location.LocationId;
import com.example.demo.domain.location.LocationRepository;


@Repository
public interface LocationJPARepository extends JpaRepository<Location, LocationId>, LocationRepository {
    default void persist(Location aLocation) {
		this.save(aLocation);
	}

	default Location findByIdOrThrow(LocationId aLocationId) {
		return this.findById(aLocationId).orElseThrow();
	}
}

