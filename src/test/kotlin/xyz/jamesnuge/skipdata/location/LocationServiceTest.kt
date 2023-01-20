package xyz.jamesnuge.skipdata.location

import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import xyz.jamesnuge.skipdata.repositories.LocationRepository
import java.lang.IllegalStateException


@ExtendWith(MockitoExtension::class)
class LocationServiceTest {

    @Mock
    private lateinit var locationRepository: LocationRepository

    @InjectMocks
    private lateinit var locationService: LocationService

    @Test
    fun `throw exception when creating location with an id`() {
        shouldThrow<IllegalStateException> { locationService.createLocation(Location(1L, "test", 180L)) }
    }

    @Test
    fun `throw exception when updating location without an id`() {
        shouldThrow<IllegalStateException> { locationService.updateLocation(Location(null, "test", 180L)) }
    }

    @Test
    fun `should save a new location to the repository`() {
        val location = Location(null, "test", 180L)
        locationService.createLocation(location)
        verify(locationRepository).save(location)
    }

    @Test
    fun `should save an updated location to the repository`() {
        val location = Location(1L, "test", 180L)
        locationService.updateLocation(location)
        verify(locationRepository).save(location)
    }

}