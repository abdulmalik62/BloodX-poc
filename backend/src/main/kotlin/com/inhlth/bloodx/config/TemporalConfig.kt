package com.inhlth.bloodx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import com.inhlth.bloodx.activity.BloodRequestActivityImpl
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory
import com.inhlth.bloodx.repository.BloodRequestRepository
import org.springframework.beans.factory.annotation.Value

@Component
@Configuration
class TemporalConfig(private val bloodRequestRepository: BloodRequestRepository) {

    @Value("\${temporal.service-address}")
    private lateinit var temporalServiceAddress: String

    @Value("\${temporal.namespace}")
    private lateinit var temporalNamespace: String

    @Bean
    fun workflowServiceStubs(): WorkflowServiceStubs {
        return WorkflowServiceStubs.newInstance(
            WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServiceAddress).build()
        )
    }

    @Bean
    fun workflowClient(workflowServiceStubs: WorkflowServiceStubs): WorkflowClient {
        return WorkflowClient.newInstance(
            workflowServiceStubs,
            WorkflowClientOptions.newBuilder().setNamespace(temporalNamespace).build()
        )
    }

    @Bean
    fun workerFactory(workflowClient: WorkflowClient): WorkerFactory {
        return WorkerFactory.newInstance(workflowClient)
    }

    @Bean
    fun signUpActivity(): BloodRequestActivityImpl {
        return BloodRequestActivityImpl(bloodRequestRepository)
    }
}
