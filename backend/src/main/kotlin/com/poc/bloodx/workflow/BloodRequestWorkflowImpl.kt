package com.poc.bloodx.workflow

import io.temporal.workflow.Workflow
import io.temporal.activity.ActivityOptions
import java.time.Duration;

class BloodRequestWorkflowImpl : BloodRequestWorkflow {


    /*
     * At least one of the following options needs to be defined:
     * - setStartToCloseTimeout
     * - setScheduleToCloseTimeout
     */
    private val options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(120))
        .build()

    /*
     * Define the HelloWorldActivity stub. Activity stubs are proxies for activity invocations that
     * are executed outside of the workflow thread on the activity worker, that can be on a
     * different host. Temporal is going to dispatch the activity results back to the workflow and
     * unblock the stub as soon as activity is completed on the activity worker.
     *
     * The activity options that were defined above are passed in as a parameter.
     */
    private val activity: BloodRequestActivities = Workflow.newActivityStub(BloodRequestActivities::class.java,options)
    // private val activity: HelloWorldActivities = Workflow.newActivityStub(HelloWorldActivities::class.java, options)

    override fun handleBloodRequest(requestId: String): String {
        // Example logic to handle blood request
        // Replace with actual logic
        return activity.processBloodRequest(requestId)
    }
}