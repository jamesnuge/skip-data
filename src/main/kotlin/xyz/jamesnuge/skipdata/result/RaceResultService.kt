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

    fun findBestResult(temp: Long?, humidity: Long?, trackTemperature: Long?, trackmeter: Long?): List<RankedRaceResult> {
        return raceResultRepository.findAll().map { it.withRank(temp, humidity, trackTemperature, trackmeter) }
            .sortedBy { it.rank }
    }
}

fun RaceResult.withRank(temp: Long?, humidity: Long?, trackTemperature: Long?, trackmeter: Long?): RankedRaceResult {
    val tempRank = if (temp == null) 0 else abs(this.temperature - temp)
    val humidityRank = if (humidity == null) 0 else abs(this.humidity - humidity)
    val trackTempRank = if (trackTemperature == null) 0 else abs(this.trackTemperature - trackTemperature)
    val trackmeterRank = if (trackmeter == null) 0 else abs(this.trackmeter - trackmeter)
    return RankedRaceResult(
        this.id!!,
        this.datetime,
        this.location,
        this.temperature,
        this.humidity,
        this.altitude,
        this.trackTemperature,
        this.trackmeter,
        this.sixtyFeetTime,
        this.threeThirtyFeetTime,
        this.sixSixtyFeetTime,
        this.sixSixtyFeetSpeed,
        this.quarterMileTime,
        this.quarterMileSpeed,
        trackTempRank + tempRank + humidityRank + trackmeterRank
    )
}
