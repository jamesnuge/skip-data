package xyz.jamesnuge.skipdata.result

import xyz.jamesnuge.skipdata.location.Location
import xyz.jamesnuge.skipdata.repositories.RaceResultRepository
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
data class RaceResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val datetime: LocalDate,
    @OneToOne
    @JoinColumn(name = "location_id")
    val location: Location,
    val temperature: Long,
    val humidity: Long,
    val trackTemperature: Long,
    val trackmeter: Long,
    val sixtyFeetTime: BigDecimal,
    val threeThirtyFeetTime: BigDecimal,
    val sixSixtyFeetTime: BigDecimal,
    val sixSixtyFeetSpeed: Long,
    val quarterMileTime: BigDecimal,
    val quarterMileSpeed: Long,
)

data class RankedRaceResult(
    val id: Long,
    val datetime: LocalDate,
    val location: Location,
    val temperature: Long,
    val humidity: Long,
    val trackTemperature: Long,
    val trackmeter: Long,
    val sixtyFeetTime: BigDecimal,
    val threeThirtyFeetTime: BigDecimal,
    val sixSixtyFeetTime: BigDecimal,
    val sixSixtyFeetSpeed: Long,
    val quarterMileTime: BigDecimal,
    val quarterMileSpeed: Long,
    val rank: Long
)

