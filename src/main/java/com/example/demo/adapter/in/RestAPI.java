package com.example.demo.adapter.in;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.location.Location;
import com.example.demo.domain.location.LocationId;
import com.example.demo.domain.location.LocationRepository;
import com.example.demo.domain.person.PersonId;
import com.example.demo.domain.person.Person;
import com.example.demo.domain.person.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Collection;


@RestController
public class RestAPI {
    private final LocationRepository locationRepository;
    private final PersonRepository personRepository;
    
    public RestAPI(LocationRepository aLocationRepository, PersonRepository aPersonRepository) {
        this.locationRepository = aLocationRepository;
        this.personRepository = aPersonRepository;
    }

    @PostMapping("locations")    
    public void newLocations() {
        var id1 = LocationId.from("0001");
        var location1 = Location.from(id1);
        locationRepository.persist(location1);

        var id2 = LocationId.from("0002");
        var location2 = Location.from(id2);
        locationRepository.persist(location2);

        var id3 = LocationId.from("0003");
        var location3 = Location.from(id3);

        locationRepository.persist(location3);
    }

    @PostMapping("persons")    
    public void newPerson() {        
        var id = PersonId.from("0001");
        var location1 = locationRepository.findByIdOrThrow(LocationId.from("0001"));
        var location2 = locationRepository.findByIdOrThrow(LocationId.from("0002"));
        Collection<Location> locations = List.of(location1, location2);        
        var person = Person.from(id, locations);

        personRepository.persist(person);
    }
}

