package xyz.jamesnuge.skipdata.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class RaceResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val date: LocalDate,
    val result: Long,
    val location: String,
    val temperature: Long,
    val humidity: Long
)
