package xyz.jamesnuge.skipdata.result

import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class RaceResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val datetime: LocalDate,
    val result: Long,
    val location: String,
    val temperature: Long,
    val humidity: Long
)

data class RankedRaceResult(
    val id: Long,
    val datetime: LocalDate,
    val result: Long,
    val location: String,
    val temperature: Long,
    val humidity: Long,
    val rank: Long
)

