package com.poc.bloodx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import com.poc.bloodx.activity.BloodRequestActivityImpl
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory
import com.poc.bloodx.repository.BloodRequestRepository

@Component
@Configuration
class TemporalConfig(private val bloodRequestRepository: BloodRequestRepository) {

    private val temporalServiceAddress = "localhost:7233"
    private val temporalNamespace = "default"

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
