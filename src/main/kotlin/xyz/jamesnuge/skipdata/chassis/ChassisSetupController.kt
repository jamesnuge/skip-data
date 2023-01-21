package xyz.jamesnuge.skipdata.chassis

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chassis", produces = ["application/json"])
class ChassisSetupController(private val chassisSetupService: ChassisSetupService) {

    @GetMapping
    @RequestMapping("/all")
    fun findAll(): List<ChassisSetup> {
        return chassisSetupService.getAllChassisSetups()
    }

    @PutMapping
    @RequestMapping("/create")
    fun createLocation(@RequestBody chassisSetup: ChassisSetup): ChassisSetup {
        chassisSetupService.createChassisSetup(chassisSetup)
        return chassisSetup
    }

    @PostMapping
    @RequestMapping("/update")
    fun updateSetup(@RequestBody chassisSetup: ChassisSetup): ChassisSetup {
        chassisSetupService.updateChassisSetup(chassisSetup)
        return chassisSetup
    }

}