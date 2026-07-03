package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val flows = KotlinFlows()
    flows.simpleFlowConsumer()
}
class KotlinFlows {

    // A Flow<T> is an asynchronous stream of values of type T
    /**
     * There are three core properties.
     * 1. Asynchronous : works on background thread, values can arrive overtime with delays.
     * 2. Sequential : values are processed one at a time.
     * 3. Cold :  the flow doesn't do anything until you collect from it.
     *
     * Ex: Think of a flow is a conveyor belt
     * producer -->---> [value1] -->-->[value2]-->-->[value3]-->-->consumer
     *                here we can do
     *                map.filter.transform - we can manipulate the [value] and pass it to the consumer
     *
     */
    // producer will emit stream of integers
    private fun simpleFlowProducer(): Flow<Int> = flow{
        println("flow started")
        for(i in 1..5){
            delay(1000)
            emit(i)
        }
        println("flow producer completed emitting values")
    }

    // flow consumer which will collect the stream of values from producer
    suspend fun simpleFlowConsumer(){
        println("flow consumer started")
        val flowValues = simpleFlowProducer()
        flowValues.collect {
            println("collected value is $it")
        }
    }

}