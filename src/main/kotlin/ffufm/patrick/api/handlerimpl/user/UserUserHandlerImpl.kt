package ffufm.patrick.api.handlerimpl.user

import de.ffuf.pass.common.utilities.extensions.orElseThrow404
import ffufm.patrick.api.spec.dbo.user.UserUser
import ffufm.patrick.api.spec.handler.user.UserUserDatabaseHandler
import kotlin.Int
import kotlin.Long
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component("user.UserUserHandler")
class UserUserHandlerImpl : UserUserDatabaseHandler {
    /**
     * Create User: Creates a new User object
     * HTTP Code 201: The created User
     */
    override suspend fun create(body: UserUser): UserUser {
        TODO("implement")
    }

    /**
     * Get all Users: Returns all Users from the system that the user has access to.
     * HTTP Code 200: List of Users
     */
    override suspend fun getAll(maxResults: Int, page: Int): Page<UserUser> {
        TODO("implement")
    }

    /**
     * Finds Users by ID: Returns Users based on ID
     * HTTP Code 200: The User object
     * HTTP Code 404: A object with the submitted ID does not exist!
     */
    override suspend fun getById(id: Long): UserUser? {
        TODO("implement")
    }

    /**
     * Delete User by id.: Deletes one specific User.
     */
    override suspend fun remove(id: Long) {
        TODO("implement")
    }

    /**
     * Update the User: Updates an existing User
     * HTTP Code 200: The updated model
     * HTTP Code 404: The requested object could not be found by the submitted id.
     * HTTP Code 422: On or many fields contains a invalid value.
     */
    override suspend fun update(body: UserUser, id: Long): UserUser {
        TODO("implement")
    }
}
