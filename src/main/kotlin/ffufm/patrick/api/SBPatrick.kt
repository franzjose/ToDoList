package ffufm.patrick.api

import de.ffuf.pass.common.PassBase
import de.ffuf.pass.common.security.PassApplication
import de.ffuf.pass.common.security.PassDataConfiguration
import de.ffuf.pass.common.utilities.LoggerDelegate
import ffufm.patrick.api.spec.SBPatrickSpec
import javax.annotation.PostConstruct
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import org.slf4j.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@PassApplication
@PassDataConfiguration(basePackageClasses = [SBPatrick::class, SBPatrickSpec::class])
@SpringBootApplication(scanBasePackageClasses = [SBPatrick::class, SBPatrickSpec::class,
        PassBase::class])
@ConfigurationPropertiesScan
class SBPatrick {
    val logger: Logger by LoggerDelegate()

    @PostConstruct
    fun afterConstruct() {
        logger.info("""Constructing complete""")
    }
}

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<SBPatrick>(*args)
}
