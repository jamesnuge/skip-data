package xyz.jamesnuge.skipdata.repositories

import org.springframework.data.jpa.repository.JpaRepository
import xyz.jamesnuge.skipdata.result.RaceResult

interface RaceResultRepository: JpaRepository<RaceResult, Long> {
}