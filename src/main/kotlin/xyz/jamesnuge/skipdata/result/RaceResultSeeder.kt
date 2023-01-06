package xyz.jamesnuge.skipdata.result

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
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
        listOf("QLD", "NSW", "ACT", "SA", "VIC", "NT", "TAS", "WA").forEach {
            for (i in 1..10) {
                raceResultRepository.save(RaceResult(
                    datetime = getRandomDate(),
                    result = getRandomResultTime(),
                    location = it,
                    temperature = getRandomTemperature(),
                    humidity = getRandomHumidity()
                ))
            }
        }
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

    private fun getRandomResultTime(): Long {
        return ThreadLocalRandom.current().nextLong(100, 200)
    }

    private fun getRandomTemperature(): Long {
        return ThreadLocalRandom.current().nextLong(15, 35)
    }
}
