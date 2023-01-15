package xyz.jamesnuge.skipdata.result

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.mockito.InjectMocks
import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
import java.util.concurrent.ThreadLocalRandom
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneOffset

ExtendWith(MockitoExtension::class)
class RaceResultServiceTest {

    companion object {
        private val startDate = LocalDate.parse("2022-01-01").atStartOfDay().toInstant(ZoneOffset.UTC)
        private val endDate = LocalDate.parse("2023-01-01").atStartOfDay().toInstant(ZoneOffset.UTC)
    }

    @Mock
    private lateinit var raceResultRepository: RaceResultRepository

    @InjectMocks
    private lateinit var raceResultService: RaceResultService

    @Test
    fun `results should be sorted by rank`() {
        val raceResult = generateRandomRaceResult()
        val raceResult2 = generateRandomRaceResult()
        whenever(raceResultRepository.findAll()).thenReturn(listOf(raceResult, raceResult2))
        val rankedResults = raceResultService.findBestResult(raceResult.temperature, raceResult.humidity, raceResult.trackTemperature, raceResult.trackmeter)
        assertEquals(rankedResults, listOf())
    }

fun generateRandomRaceResult(): RaceResult =
        RaceResult(
                datetime = getRandomDate(),
                location = "location",
                temperature = getRandomTemperature(),
                humidity = getRandomHumidity(),
                altitude = 2001,
                trackmeter = ThreadLocalRandom.current().nextLong(0, 600),
                trackTemperature = ThreadLocalRandom.current().nextLong(50, 150),
                sixtyFeetTime = randomBigDecimal(0.080, 1.200),
                threeThirtyFeetTime = randomBigDecimal(2.300, 4.000),
                sixSixtyFeetTime = randomBigDecimal(3.300, 5.000),
                quarterMileTime = randomBigDecimal(5.300, 8.000),
                sixSixtyFeetSpeed = ThreadLocalRandom.current().nextLong(180, 220),
                quarterMileSpeed = ThreadLocalRandom.current().nextLong(220, 280),
        )


    private fun randomBigDecimal(origin: Double, bound: Double): BigDecimal {
        return BigDecimal(ThreadLocalRandom.current().nextDouble(origin, bound)).setScale(2, RoundingMode.HALF_EVEN)
    }

    private fun getRandomHumidity(): Long {
        return ThreadLocalRandom.current().nextLong(30, 100)

    }

    private fun getRandomDate(): LocalDate {
        return LocalDate.ofInstant(
            Instant.ofEpochMilli(ThreadLocalRandom.current().nextLong(startDate.toEpochMilli(), endDate.toEpochMilli())),
            ZoneOffset.UTC.normalized()
        )
    }

    private fun getRandomTemperature(): Long {
        return ThreadLocalRandom.current().nextLong(15, 35)
    }

}