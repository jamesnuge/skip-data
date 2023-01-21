package xyz.jamesnuge.skipdata.repositories

import org.springframework.data.jpa.repository.JpaRepository
import xyz.jamesnuge.skipdata.chassis.ChassisSetup
import xyz.jamesnuge.skipdata.location.Location

interface ChassisSetupRepository: JpaRepository<ChassisSetup, Long>
