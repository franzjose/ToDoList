package ffufm.patrick.api.spec.handler.user.implementation

import ffufm.patrick.api.PassTestBase
import ffufm.patrick.api.repositories.user.UserUserRepository
import ffufm.patrick.api.spec.dbo.user.UserUser
import ffufm.patrick.api.spec.handler.user.UserUserDatabaseHandler
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

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
    fun `test create`() = runBlocking {
        val body: UserUser = UserUser()
//        userUserDatabaseHandler.create(body)
        Unit
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
    fun `remove test should return ok`() = runBlocking {
        val createdUser = userUserRepository.save(UserUser(
            firstName = "Kim",
            lastName = "Ko",
            email = "Kim@gmail.com"
        ))
        assertEquals(1, userUserRepository.findAll().count())
        val id: Long = 0
        userUserDatabaseHandler.remove(createdUser.id!!)
        assertEquals(0, userUserRepository.findAll().count())
    }

    @Test
    fun `test update`() = runBlocking {
        val body: UserUser = UserUser()
        val id: Long = 0
//        userUserDatabaseHandler.update(body, id)
        Unit
    }
}
