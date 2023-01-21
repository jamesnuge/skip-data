package xyz.jamesnuge.skipdata.location

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location", produces = ["application/json"])
class LocationController(private val locationService: LocationService) {

    @GetMapping
    @RequestMapping("/all")
    fun findAll(): List<Location> {
        return locationService.getLocations()
    }

    @PutMapping
    @RequestMapping("/create")
    fun createLocation(@RequestBody location: Location): Location {
        return locationService.createLocation(location)
    }

}