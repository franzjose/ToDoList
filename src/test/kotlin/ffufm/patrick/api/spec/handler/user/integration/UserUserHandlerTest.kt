package ffufm.patrick.api.spec.handler.user.integration

import com.fasterxml.jackson.databind.ObjectMapper
import ffufm.patrick.api.PassTestBase
import ffufm.patrick.api.repositories.user.UserUserRepository
import ffufm.patrick.api.spec.dbo.user.UserUser
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

class UserUserHandlerTest : PassTestBase() {
    @Autowired
    private lateinit var userUserRepository: UserUserRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Before
    @After
    fun cleanRepositories() {
        userUserRepository.deleteAll()
    }

    @Test
    @WithMockUser
    fun `test create`() {
        val body: UserUser = UserUser()
                mockMvc.post("/users/") {
                    accept(MediaType.APPLICATION_JSON)
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isOk() }
                    
                }
    }

    @Test
    @WithMockUser
    fun `test getAll`() {
                mockMvc.get("/users/") {
                    accept(MediaType.APPLICATION_JSON)
                    contentType = MediaType.APPLICATION_JSON
                    
                }.andExpect {
                    status { isOk() }
                    
                }
    }

    @Test
    @WithMockUser
    fun `test getById`() {
        val id: Long = 0
                mockMvc.get("/users/{id}/", id) {
                    accept(MediaType.APPLICATION_JSON)
                    contentType = MediaType.APPLICATION_JSON
                    
                }.andExpect {
                    status { isOk() }
                    
                }
    }

    @Test
    @WithMockUser
    fun `remove should return 200`() {
        val createdUser = userUserRepository.save(UserUser(
            firstName = "Kim",
            lastName = "Ko",
            email = "Kim@gmail.com"
        ))
        mockMvc.delete("/users/{id}/", createdUser.id) {
            accept(MediaType.APPLICATION_JSON)
            contentType = MediaType.APPLICATION_JSON

        }.asyncDispatch().andExpect {
            status { isOk() }

        }
    }

    @Test
    @WithMockUser
    fun `test update`() {
        val body: UserUser = UserUser()
        val id: Long = 0
                mockMvc.put("/users/{id}/", id) {
                    accept(MediaType.APPLICATION_JSON)
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(body)
                }.andExpect {
                    status { isOk() }
                    
                }
    }
}
