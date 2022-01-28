package ffufm.patrick.api.repositories.user

import de.ffuf.pass.common.repositories.PassRepository
import ffufm.patrick.api.spec.dbo.user.UserUser
import org.springframework.data.jpa.repository.Query
import kotlin.Long
import org.springframework.stereotype.Repository

@Repository
interface UserUserRepository : PassRepository<UserUser, Long>{
    @Query(
        """
            SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END 
            FROM UserUser u WHERE u.firstName = :firstName AND 
            u.lastName = :lastName
        """
    )
    fun doesUserExist(firstName: String, lastName: String): Boolean
}
