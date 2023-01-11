package xyz.jamesnuge.skipdata.result

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.ChoiceFormat.nextDouble
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

@Component
@Profile(value = ["dev", "ci"])
class RaceResultSeeder(
    private val raceResultRepository: RaceResultRepository
): ApplicationRunner {

    companion object {
        private val startDate = LocalDate.parse("2022-01-01").atStartOfDay().toInstant(ZoneOffset.UTC)
        private val endDate = LocalDate.parse("2023-01-01").atStartOfDay().toInstant(ZoneOffset.UTC)
    }

    override fun run(args: ApplicationArguments?) {
        if (raceResultRepository.count() == 0L) {
            mapOf(
                Pair("Sydney Dragway", 239L),
                Pair("Willowbank Raceway QLD", 180L),
                Pair("Perth Motorplex", 2203L),
                Pair("Hidden Valley Darwin", 39L),
                Pair("The Bend Motorsport Park", 71L),
                Pair("Heathcote Park Raceway Melbourne", 596L),
                Pair("Adelaide International Raceway", 2008L)
            ).forEach {
                for (i in 1..10) {
                    raceResultRepository.save(
                        RaceResult(
                            datetime = getRandomDate(),
                            location = it.key,
                            temperature = getRandomTemperature(),
                            humidity = getRandomHumidity(),
                            altitude = it.value,
                            trackmeter = ThreadLocalRandom.current().nextLong(0, 600),
                            trackTemperature = ThreadLocalRandom.current().nextLong(50, 150),
                            sixtyFeetTime = randomBigDecimal(0.080, 1.200),
                            threeThirtyFeetTime = randomBigDecimal(2.300, 4.000),
                            sixSixtyFeetTime = randomBigDecimal(3.300, 5.000),
                            quarterMileTime = randomBigDecimal(5.300, 8.000),
                            sixSixtyFeetSpeed = ThreadLocalRandom.current().nextLong(180, 220),
                            quarterMileSpeed = ThreadLocalRandom.current().nextLong(220, 280),
                        )
                    )
                }
            }
        }
    }

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
