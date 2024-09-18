package com.inhlth.bloodx

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import com.inhlth.bloodx.config.OkHttpClientConfig

@SpringBootTest
@Import(OkHttpClientConfig::class)
class BloodxApplicationTests {

	@Test
	fun contextLoads() {
	}

}
