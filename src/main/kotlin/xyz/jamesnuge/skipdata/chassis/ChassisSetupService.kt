package xyz.jamesnuge.skipdata.chassis

import org.springframework.stereotype.Service
import xyz.jamesnuge.skipdata.repositories.ChassisSetupRepository
import java.lang.IllegalStateException

@Service
class ChassisSetupService(private val chassisSetupRepository: ChassisSetupRepository) {

    fun createChassisSetup(chassisSetup: ChassisSetup) {
        if (chassisSetup.id == null) {
            chassisSetupRepository.save(chassisSetup)
        } else {
            throw IllegalStateException("Unable to create chassis setup with an already existing id")
        }
    }

    fun updateChassisSetup(chassisSetup: ChassisSetup) {
        if (chassisSetup.id != null) {
            chassisSetupRepository.save(chassisSetup)
        } else {
            throw IllegalStateException("Unable to update chassis setup that doesn't exist")
        }
    }

    fun getAllChassisSetups(): List<ChassisSetup> {
        return chassisSetupRepository.findAll()
    }
}