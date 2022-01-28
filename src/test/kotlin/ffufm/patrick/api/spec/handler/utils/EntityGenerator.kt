package ffufm.patrick.api.spec.handler.utils

import ffufm.patrick.api.spec.dbo.user.UserUser

object EntityGenerator {
    val user = UserUser(
        firstName = "Brandon",
        lastName = "Cruz",
        email = "brandon@email.com"
    )
}