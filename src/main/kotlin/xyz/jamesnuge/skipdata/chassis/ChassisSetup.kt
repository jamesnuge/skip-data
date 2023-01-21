package xyz.jamesnuge.skipdata.chassis

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ChassisSetup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val frontSpread: Long,
    val rearSpread: Long,
    val frontCrossmemberHeight: Long,
    val rearCrossmemberHeight:  Long,
    val rearSteer: Long,
    val preload: Long
)
