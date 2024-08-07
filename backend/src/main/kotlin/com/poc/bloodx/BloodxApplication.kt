package com.poc.bloodx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import com.poc.bloodx.activity.BloodRequestActivity
import com.poc.bloodx.workflow.BloodRequestWorkFlow
import com.poc.bloodx.workflow.BloodRequestWorkflowImpl

@SpringBootApplication
class BloodxApplication

fun main(args: Array<String>) {
	val appContext: ConfigurableApplicationContext = runApplication<BloodxApplication>(*args)
    val factory: WorkerFactory = appContext.getBean(WorkerFactory::class.java)
    val signUpActivity: BloodRequestActivity = appContext.getBean(BloodRequestActivity::class.java)
    val worker: Worker = factory.newWorker(BloodRequestWorkFlow.QUEUE_NAME)
    worker.registerWorkflowImplementationTypes(BloodRequestWorkflowImpl::class.java)
    worker.registerActivitiesImplementations(signUpActivity)
    factory.start()
}
