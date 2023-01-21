package xyz.jamesnuge.skipdata.location

import org.springframework.stereotype.Service
import xyz.jamesnuge.skipdata.repositories.LocationRepository
import java.lang.IllegalStateException

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getLocation(id: Long): Location {
        return locationRepository.getById(id)
    }

    fun getLocations(): List<Location> {
        return locationRepository.findAll()
    }

    fun updateLocation(location: Location) {
        if (location.id == null) {
            throw IllegalStateException("Cannot update location ")
        } else {
            locationRepository.save(location)
        }
    }

    fun createLocation(location: Location): Location {
        if (location.id != null) {
            throw IllegalStateException("Cannot create location with an id")
        } else {
            locationRepository.save(location)
            return location
        }
    }
}