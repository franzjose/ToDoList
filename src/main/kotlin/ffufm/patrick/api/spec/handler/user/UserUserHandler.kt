package ffufm.patrick.api.spec.handler.user

import com.fasterxml.jackson.module.kotlin.readValue
import de.ffuf.pass.common.handlers.PassMvcHandler
import ffufm.patrick.api.spec.dbo.user.UserUserDTO
import kotlin.Int
import kotlin.Long
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.server.ResponseStatusException

interface UserUserDatabaseHandler {
    /**
     * Create User: Creates a new User object
     * HTTP Code 201: The created User
     */
    suspend fun create(body: UserUserDTO): UserUserDTO

    /**
     * Get all Users: Returns all Users from the system that the user has access to.
     * HTTP Code 200: List of Users
     */
    suspend fun getAll(maxResults: Int = 100, page: Int = 0): Page<UserUserDTO>

    /**
     * Finds Users by ID: Returns Users based on ID
     * HTTP Code 200: The User object
     * HTTP Code 404: A object with the submitted ID does not exist!
     */
    suspend fun getById(id: Long): UserUserDTO?

    /**
     * Delete User by id.: Deletes one specific User.
     */
    suspend fun remove(id: Long)

    /**
     * Update the User: Updates an existing User
     * HTTP Code 200: The updated model
     * HTTP Code 404: The requested object could not be found by the submitted id.
     * HTTP Code 422: On or many fields contains a invalid value.
     */
    suspend fun update(body: UserUserDTO, id: Long): UserUserDTO
}

@Controller("user.User")
class UserUserHandler : PassMvcHandler() {
    @Autowired
    lateinit var databaseHandler: UserUserDatabaseHandler

    /**
     * Create User: Creates a new User object
     * HTTP Code 201: The created User
     */
    @RequestMapping(value = ["/users/"], method = [RequestMethod.POST])
    suspend fun create(@RequestBody body: UserUserDTO): ResponseEntity<*> {
        body.validateOrThrow()
        return success { databaseHandler.create(body) }
    }

    /**
     * Get all Users: Returns all Users from the system that the user has access to.
     * HTTP Code 200: List of Users
     */
    @RequestMapping(value = ["/users/"], method = [RequestMethod.GET])
    suspend fun getAll(@RequestParam("maxResults") maxResults: Int? = 100, @RequestParam("page")
            page: Int? = 0): ResponseEntity<*> {

        return paging { databaseHandler.getAll(maxResults ?: 100, page ?: 0) }
    }

    /**
     * Finds Users by ID: Returns Users based on ID
     * HTTP Code 200: The User object
     * HTTP Code 404: A object with the submitted ID does not exist!
     */
    @RequestMapping(value = ["/users/{id:\\d+}/"], method = [RequestMethod.GET])
    suspend fun getById(@PathVariable("id") id: Long): ResponseEntity<*> {

        return success { databaseHandler.getById(id) ?: throw
                ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    /**
     * Delete User by id.: Deletes one specific User.
     */
    @RequestMapping(value = ["/users/{id:\\d+}/"], method = [RequestMethod.DELETE])
    suspend fun remove(@PathVariable("id") id: Long): ResponseEntity<*> {

        return success { databaseHandler.remove(id) }
    }

    /**
     * Update the User: Updates an existing User
     * HTTP Code 200: The updated model
     * HTTP Code 404: The requested object could not be found by the submitted id.
     * HTTP Code 422: On or many fields contains a invalid value.
     */
    @RequestMapping(value = ["/users/{id:\\d+}/"], method = [RequestMethod.PUT])
    suspend fun update(@RequestBody body: UserUserDTO, @PathVariable("id") id: Long):
            ResponseEntity<*> {
        body.validateOrThrow()
        return success { databaseHandler.update(body, id) }
    }
}
