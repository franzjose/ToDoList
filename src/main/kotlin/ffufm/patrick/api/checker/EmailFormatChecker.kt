package ffufm.patrick.api.checker

import ffufm.patrick.api.external.EmailValidatorService
import org.springframework.stereotype.Service

@Service
class EmailFormatChecker(
    private val emailValidatorApi: EmailValidatorService
) {
    suspend fun isValidEmail(email: String): Boolean {
        // Converts the response into Map to check the value of the attributes
        val response =  emailValidatorApi.verifyEmail(email).toMap()
        // Return the value of the format value of the response
        return response["format"] as Boolean
    }
}