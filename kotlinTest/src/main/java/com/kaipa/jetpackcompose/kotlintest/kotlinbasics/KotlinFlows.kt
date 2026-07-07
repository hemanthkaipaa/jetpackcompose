package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import sun.rmi.server.Dispatcher

fun main() = runBlocking {
    val flows = KotlinFlows()
    flows.simpleFlowConsumer()
    flows.everyFlow()
    flows.consumeBuilderFlow()
    flows.consumeBuilderFlowAsFixedValues()
    flows.consumeBuilderFlowAsListToFlow()
    flows.intermediateOperators()
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
    private fun simpleFlowProducer(): Flow<Int> = flow {
        println("flow started")
        for (i in 1..5) {
            delay(1000)
            emit(i)
        }
        println("flow producer completed emitting values")
    }

    // flow consumer which will collect the stream of values from producer
    suspend fun simpleFlowConsumer() {
        println("flow consumer started")
        val flowValues = simpleFlowProducer()
        flowValues.collect {
            println("collected value is $it")
        }
    }

    /**
     * Three things in every flow
     * Builder flow{...}, flowOf(...), asFlow() etc.
     * Operators map,filter,debounce etc(intermediate, lazy)
     * A terminal operator - collect,toList,first etc.
     */
    //ex:

    suspend fun everyFlow() {
        flow {
            emit(1)
            emit(2)
            emit(3)
        } // flow builder emits
            .map { it * 2 }.filter { it > 4 }  // intermediate operators which can transform values
            .collect { println(it) }  // terminal operator triggers everything
    }

    // flow builders are flexible, and simply you write a suspend block that calls emit()
//--------------------------------------------------------------------------------------------------
    fun builderFlow() : Flow<Long> = flow{
        for (i in 1..5){
            emit(i.toLong())
        }
    }

    suspend fun consumeBuilderFlow(){
        builderFlow().collect {
            println("consuming builder flow $it")
        }
    }

    // builderFlow as fixed values
    fun builderFlowAsFixedValues() = flowOf(1L,2L,3L,4L)
    suspend fun consumeBuilderFlowAsFixedValues(){
        builderFlowAsFixedValues().collect {
            println("consuming builder flow as fixed values $it")
        }
    }

    // builderFlow as convert list to flow
    fun builderFlowAsListToFlow() = listOf(1,2,3,4,5).asFlow()
    suspend fun consumeBuilderFlowAsListToFlow(){
        builderFlowAsListToFlow().collect {
            println("converted list to a flow $it")
        }
    }

    /**
     * why -> why list has to be converted as flow??
     * list gives all instant but flow gives only one at a time with suspend capability
     * Imagine you have getAllusers() //1000 in ram at once is kind of more memory work at once
     * instead use flow receives all users in a flow one by one and render the UI with good fps
     * list is like a photo
     * flow is like a video it gives steam of frames
     *
     * // Imagine fetching 1000 users from DB
     * // List way — loads ALL 1000 users into memory first
     * val users: List<User> = db.getAllUsers()  // 1000 users in RAM 😬
     * users.forEach { updateUI(it) }
     *
     * // Flow way — processes ONE user at a time
     * db.getAllUsers()       // returns Flow<User>
     *     .onEach { delay(16) }           // smooth 60fps updates
     *     .collect { updateUI(it) }       // UI updates one user at a time ✅
     *
     *     and flow is lazy waits unitll its collect by consumer.
     */

    // callbackFlow
    /**
     * regular callbacks push data to you
     * flow lets you collect data as a stream
     *
     * callback flow is bridge between the two
     * ex:
     * fun countdownFlow(): Flow<Int> = callbackFlow {
     *
     *     val timer = Timer()
     *
     *     var count = 5
     *
     *     timer.scheduleAtFixedRate(object : TimerTask() {
     *         override fun run() {
     *             trySend(count)   // push current count into flow
     *             count--
     *
     *             if (count < 0) {
     *                 close()      // tell flow we're done emitting
     *             }
     *         }
     *     }, 0L, 1000L)            // fires every 1 second
     *
     *     awaitClose {
     *         timer.cancel()       // cleanup when collector stops listening
     *     }
     * }
     *
     * // consume it
     * suspend fun main() = runBlocking {
     *     countdownFlow().collect {
     *         println("T-minus $it")
     *     }
     * }
     * "callbackFlow converts a push-based callback API into a cold Flow — trySend pushes values in,
     * and awaitClose ensures cleanup when the collector stops listening."
     */
//--------------------------------------------------------------------------------------------------
    // Intermediate operators
    /**
     * 1. map
     * 2. filter
     * 3. transform
     * 4. take
     * 5. drop
     * 6. onEach
     * 7. debounce
     * 8. distinctUntilChanged
     * 9. flatmapConcat,flatmapMerge,flatmapLatest
     * 10. combine
     * 11. zip
     */

    suspend fun intermediateOperators(){
        println("--------------------------started IntermediateOperators--------------------------")
        // map transforms each value
        val listMap = listOf(1,2,3,4,5,6).asFlow()
        listMap.map { it * 2} // map transforms each value multiplied by 2
            .collect { println("map transformed values are - $it") }
        // output will be // 2,4,6,8,10,12 -> but it has to be collected to get the values

        //filter
        val listFilter = listOf(1,2,3,4,5,6).asFlow()
        listFilter.filter { it > 10 } // filter filters the values which are greater than 10
            .collect { println("filtered values are - $it") }
        // output ill be 12   -> but it has to collected during consumption

        // transform
        val transformList = flowOf(1,2,3,4,5,6)
        transformList.transform { emit("hello $it") } //transforms each value and emits it
            .collect { println("transformed values are - $it") }
        // output will be // hello 1, hello 2, hello 3, hello 4, hello 5, hello 6

        //take
        flowOf(1,2,3,4,5).take(3) // it will take 1,2,3 only and not 4,5
            .collect { println("take - $it") }
        flowOf(1,2,3,4,5).drop(2) // it will drop 1,2 and take 3,4,5
            .collect { println("drop - $it") }

        //onEach
        flowOf(1,2,3,4,5).onEach { println("onEach - $it ") } //for each element it will log, delay or do something
            .collect{println("after collect $it")}

        // debounce
        flowOf(1,2,3,4,5).debounce(100) // it will wait for 100ms and then emit the last value
            .collect { println("debounce - $it") }

        // distinctUntilChanged

        flowOf(1,2,2,3,4,2,5,1).distinctUntilChanged() // it will remove consecutive duplicate values
            .collect { println("distinctUntilChanged - $it") }

        //combine
        val flow1 = flowOf(1,2,3,4,5)
        val flow2 = flowOf("a","b","c","d","e","7")
        val newFlow = combine(flow1,flow2){
            a,b -> "$a,$b"
        }
        newFlow.collect { println("combine -> $it") }

        // zip
        val flow3 = flowOf(1,2,3,4)
        val flow4 = flowOf("a","b","c","d")
        val newFlow2 = flow3.zip(flow4){
            a,b -> "$a,$b"
        }
        newFlow2.collect { println("zip -> $it") }
    }
    //--------------------------------------------------------------------------------------------
    // Terminal operators
    /**
     * 1. Collect - most commonly used
     * 2. toList() /toSet() - collect into collection
     * 3. first()/firstOrNull() - first value
     * 4. reduce/fold
     * 5. count
     */

    // flow context
    fun numbers() : Flow<Int> = flow{
        for (i in 1..3){
            emit(i)
        }
    }
    fun flowMain() = runBlocking{
        withContext(Dispatchers.IO) {
            numbers().collect {
                println("emitting on ${Thread.currentThread().name}")
            }
        }
    }
    // output both emit and collect running with IO thread
    fun numberWithFlowOn() : Flow<Int> = flow {
        emit(1)
    }.flowOn(Dispatchers.IO) // emit is running on IO thread

    fun flowWithContext() = runBlocking {
        numberWithFlowOn().collect {
            println("emitting on ${Thread.currentThread().name}") // different or main it is running
        }
    }
    // output both emit and collect runs in a different thread
    /**
     * Q: What is a Flow?
     * An asynchronous stream of values delivered sequentially over time. Cold by default — execution starts only when a collector subscribes.
     * Q: Difference between a suspend function and a Flow?
     * Suspend function returns a single value once. Flow emits multiple values over time.
     * Q: What does "cold" mean?
     * The flow doesn't execute until collected. Each new collector triggers a fresh execution from the start.
     * Q: Difference between map and flatMap-style operators?
     * map transforms each value to another value. flatMap* transforms each value into a new flow, then flattens those into one stream.
     * Q: When would you use flatMapLatest?
     * When new values should cancel work-in-progress from old values. Classic use: search-as-you-type — new keystroke cancels in-flight search.
     * Q: What does flowOn(Dispatchers.IO) do?
     * Switches the upstream (producer) to run on IO. The collector stays on its own dispatcher.
     * Q: What does catch?
     * Exceptions from upstream operators only. It does NOT catch exceptions thrown inside collect { } — for those, wrap collect in try/catch.
     * Q: Reactive vs imperative — what's the difference?
     * Imperative: you explicitly orchestrate steps and pull data. Reactive: you declare a pipeline of transformations on a data stream, and react when data flows through.
     */
}