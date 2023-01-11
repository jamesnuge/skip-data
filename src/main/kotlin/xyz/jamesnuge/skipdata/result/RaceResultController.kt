package xyz.jamesnuge.skipdata.result

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/results", produces = ["application/json"])
class RaceResultController(private val raceResultService: RaceResultService) {

    @GetMapping
    @RequestMapping("/all")
    fun findAll(): List<RaceResult> {
        return raceResultService.findAll()
    }

    @GetMapping
    @RequestMapping("/match")
    fun findBestMatch(@RequestParam temperature: Long?, @RequestParam humidity: Long?, @RequestParam trackTemperature: Long?, @RequestParam trackmeter: Long?): List<RankedRaceResult> {
        return raceResultService.findBestResult(temperature, humidity, trackTemperature, trackmeter)
    }
}