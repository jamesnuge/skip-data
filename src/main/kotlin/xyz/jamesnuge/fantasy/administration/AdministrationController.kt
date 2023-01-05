package xyz.jamesnuge.fantasy.administration

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.jamesnuge.fantasy.user.UserAdministrationService

@RestController
@RequestMapping("/user/administration")
class AdministrationController(
    private val userAdministrationService: UserAdministrationService
) {
    @PostMapping
    @RequestMapping("/invite")
    fun inviteUser(@RequestBody email: String) {

    }
}