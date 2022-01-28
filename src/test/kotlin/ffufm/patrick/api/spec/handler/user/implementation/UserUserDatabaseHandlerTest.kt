package ffufm.patrick.api.spec.handler.user.implementation

import ffufm.patrick.api.PassTestBase
import ffufm.patrick.api.repositories.user.UserUserRepository
import ffufm.patrick.api.spec.dbo.user.UserUser
import ffufm.patrick.api.spec.handler.user.UserUserDatabaseHandler
import ffufm.patrick.api.spec.handler.utils.EntityGenerator
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.server.ResponseStatusException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UserUserDatabaseHandlerTest : PassTestBase() {
    @Autowired
    lateinit var userUserRepository: UserUserRepository

    @Autowired
    lateinit var userUserDatabaseHandler: UserUserDatabaseHandler

    @Before
    @After
    fun cleanRepositories() {
        userUserRepository.deleteAll()
    }

    @Test
    fun `create should work given valid inputs`() = runBlocking {
        val body = EntityGenerator.user
        userUserDatabaseHandler.create(body.toDto())

        assertEquals(1, userUserRepository.findAll().count())
    }

    @Test
    fun `create should throw an error given duplicate user`() = runBlocking {
        val body = EntityGenerator.user
        userUserRepository.save(body)

        val exception = assertFailsWith<ResponseStatusException> {
            userUserDatabaseHandler.create(body.toDto())
        }

        val expectedException = "409 CONFLICT \"User already exist\""

        assertEquals(expectedException, exception.message)

    }

    @Test
    fun `create should throw an error given invalid email format`() = runBlocking {
        val body = EntityGenerator.user.copy(
            email = "invalid@com"
        )
        val exception = assertFailsWith<ResponseStatusException> {
            userUserDatabaseHandler.create(body.toDto())
        }

        val expectedException = "400 BAD_REQUEST \"Email: ${body.email} is invalid\""

        assertEquals(expectedException, exception.message)

    }

    @Test
    fun `test getAll`() = runBlocking {
        val maxResults: Int = 100
        val page: Int = 0
        userUserDatabaseHandler.getAll(maxResults, page)
        Unit
    }

    @Test
    fun `test getById`() = runBlocking {
        val id: Long = 0
        userUserDatabaseHandler.getById(id)
        Unit
    }

    @Test
    fun `test remove`() = runBlocking {
        val id: Long = 0
        userUserDatabaseHandler.remove(id)
        Unit
    }

    @Test
    fun `test update`() = runBlocking {
        val body: UserUser = UserUser()
        val id: Long = 0
//        userUserDatabaseHandler.update(body, id)
        Unit
    }
}
