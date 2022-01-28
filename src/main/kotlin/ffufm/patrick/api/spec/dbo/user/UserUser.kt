package ffufm.patrick.api.spec.dbo.user

import de.ffuf.pass.common.models.PassDTOModel
import de.ffuf.pass.common.models.PassModel
import de.ffuf.pass.common.models.idDto
import de.ffuf.pass.common.security.SpringContext
import de.ffuf.pass.common.utilities.extensions.toEntities
import de.ffuf.pass.common.utilities.extensions.toSafeDtos
import java.util.TreeSet
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.SequenceGenerator
import javax.persistence.UniqueConstraint
import kotlin.Long
import kotlin.String
import kotlin.reflect.KClass
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.FetchMode
import org.springframework.beans.factory.getBeansOfType

/**
 * This holds the user model
 */
data class UserUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    /**
     * The first name of the user
     */
    val firstName: String? = null,
    /**
     * The last name of the user
     */
    val lastName: String? = null,
    /**
     * The email of the user
     */
    val email: String? = null
) : PassModel<UserUser, Long>() {
    override fun readId(): Long? = this.id

    override fun toString(): String = super.toString()
}
