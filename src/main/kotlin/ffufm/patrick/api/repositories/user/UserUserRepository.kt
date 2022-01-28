package ffufm.patrick.api.repositories.user

import de.ffuf.pass.common.repositories.PassRepository
import ffufm.patrick.api.spec.dbo.user.UserUser
import kotlin.Long
import org.springframework.stereotype.Repository

@Repository
interface UserUserRepository : PassRepository<UserUser, Long>
