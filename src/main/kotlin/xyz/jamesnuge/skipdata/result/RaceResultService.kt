package xyz.jamesnuge.skipdata.result

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
import kotlin.math.abs

@Service
class RaceResultService(
    private val raceResultRepository: RaceResultRepository
) {
    fun findAll(pageable: Pageable): Page<RaceResult> {
        return raceResultRepository.findAll(pageable)
    }

    fun findAll(): List<RaceResult> {
        return raceResultRepository.findAll()
    }

    fun findBestResult(temp: Long, humidity: Long): List<RankedRaceResult> {
        return raceResultRepository.findAll().map { it.withRank(temp, humidity) }
            .sortedBy { it.rank }
    }
}

fun RaceResult.withRank(temp: Long, humidity: Long): RankedRaceResult = RankedRaceResult(
        this.id!!,
        this.datetime,
        this.result,
        this.location,
        this.temperature,
        this.humidity,
        abs(temp - this.temperature) + abs(humidity - this.humidity)
    )
