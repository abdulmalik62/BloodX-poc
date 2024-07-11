package sampleBloodX.BloodX

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("sampleBloodX.BloodX.repository")
@EntityScan("sampleBloodX.BloodX.model")
class BloodXApplication

fun main(args: Array<String>) {
	runApplication<BloodXApplication>(*args)
}
