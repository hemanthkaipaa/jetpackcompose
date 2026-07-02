package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Coroutine is a block of code that pause(suspend) and resume later without blocking the thread that it runs on

// A coroutine is just a code wrapped in builder like launch{} or async{}
 fun _launchBlock(){
    println("I'm a coroutine")  // coroutine is running
//    delay(1000)               // coroutine is paused(suspended) here and it does other work but not freeze or stuck here
    println("I'm still here")   // coroutine is resumed here.
}

// Difference between thread and a coroutine
//Thread
// 1. When we use thread -> thread created by OS uses memory about 1-2MB for each thread created and which is very expensive
// 2. max practical count / threads  created is hundreds if more cpu will be busy and expect choppy performance
// 3. OS-level context switching is expensive - when thread blocked with Thread.sleep(1000)
//    it will waste the thread time and stuck there
// 4. thread interruption is hard

// Coroutine
// 1. Coroutines uses few bytes each and created by kotlin runtime, practically we can create hundreds of thousands
// 2. uses function call switching so that it can handle multiple other works when it is in suspended state
// 3. when called delay(1000) it goes to suspended state and gets busy in other parallel works and resumed back.
// 4. I can say coroutine is like a joint family helps on other's work when they are free/suspended and resume their work again
// 5. interuption is easy as its built stuctured.
// 6. Coroutines are not a replacement of threads, because internally they run on threads.
//      Dispatchers will decide which thread pool to be used
//------------------------------------------------------------------------------------------------------------------------------------

// 'suspend' is a keyword used before fun and marking it as suspend function. which will be paused and resumed later

suspend fun fetUser():User{
    for(i in 1..3){
        delay(1000) // suspending call
        println("delay for $i sec")
    }
    return User("hemanth", Address("Bangalore")) // resumed here after 1000ms delay
}

// Rule :
// where can you call or invoke a suspend function?
// Inside another suspend function , inside a coroutine builder Ex: launch{},runBlocking{},async{}
// ex: above fetchUser() can be called inside other suspend function as below

suspend fun otherSuspendFunction(){
    println("calling other suspend function fetuser() ")
}

// first working coroutine
class CoroutineExample{
    fun call() = runBlocking {
        val user = fetUser()
        otherSuspendFunction()
        println("calling from runblocking function ${user.name},${user.address?.city}")
    }
}

// coroutine scopes
// There are 4 ->
// Special coroutine scope  - runBlocking scope - used with main() or unittest - it blocks the current thread until it finishes all the coroutines inside the block.
// lifecycleScope -> Tied to activity/fragment lifecycle and canceled when activity or fragment destroyed
// viewModelScope -> Tied to viewModel and will be alive till viewModel is cleared.
// GlobalScope lives entire lifetime and No automatic cancellation //  don't use it memory leaks are common and hard to control

// first coroutine working Example

fun main(): Unit = runBlocking {
    CoroutineExample().call() // blocks the below launch
    launch {  // launch waits for CoroutineExample().call()
        launchFireAndForget(this)
    }

    // instead
    launch{
        CoroutineExample().call()
    }
    launch {
        println("Runs parallel")
    }
    asyncAwait(this)
}

/**
 * The Essential coroutine builder
 * 1. launch{} - fire and forget doesn't return result
 * 2. async {} - fire and wait for result
 * 3. runBlocking {} -  bridge between coroutine world to non coroutine world, which blocks the main thread to complete all coroutines inside it.
 */

suspend fun launchFireAndForget(scope: CoroutineScope){
    val noResult = scope.launch {
        delay(1000)
        println("No result")
    }
    delay(1000)
    println(noResult)
}
// fire and get result -> returns deferred object
suspend fun asyncAwait(scope: CoroutineScope){
    val apiResponse = scope.async(Dispatchers.IO) {
        delay(3000)
        4
    }
    apiResponse.await()
    println("api response is $apiResponse")
}


/**
 * basic coroutine interview questions
 *
 * Q1: What's a coroutine?
 * A coroutine is a block of code that can suspend (pause) and resume later without blocking the thread it runs on.
 * They are lightweight — many coroutines can share a single thread — and are used for non-blocking concurrent work like network calls, database access, and UI updates.
 * Dispatchers control which thread or thread pool a coroutine actually runs on.
 *
 * Q2: How are coroutines different from threads?
 * Threads are OS-level, expensive (~1-2 MB each), and limited in count. Coroutines are lightweight (few bytes), run on top of threads via dispatchers,
 * and you can have hundreds of thousands of them. Coroutines also have built-in structured cancellation.
 *
 * Q3: What does the suspend keyword mean?
 * It marks a function as capable of suspending. The compiler transforms it to support pausing and resuming.
 * Suspend functions can only be called from other suspend functions or from coroutine builders.
 *
 * Q4: Difference between launch and async?
 * launch returns a Job and is fire-and-forget — no return value. async returns a Deferred<T>; you call .await() to get the result.
 * Use async for parallel work where you need results.
 *
 * Q5: Why shouldn't you use runBlocking in Android production code?
 * runBlocking blocks the calling thread until the coroutine completes. On the main thread, this freezes the UI — exactly what coroutines exist to avoid.
 *
 * Q6: What's the difference between delay() and Thread.sleep()?
 * Thread.sleep() blocks the thread — nothing else can run on it. delay() suspends the coroutine, freeing the thread to run other coroutines.
 *
 */

// module 2 - Dispatchers,withContext,Scopes
/**
 * Fundamental Question
 * Every coroutine runs on some thread. The question is what thread it runs on?
 * A) Dispatcher is a coroutine context element that decides which thread(or thread pool) to run on.!
 *-----------------------------------------------------------
 * explain about 4 built in Dispatchers
 * ----------------------------------------------------------
 * 1) Dispatcher.Main
 * A) The Android UIThread (main Looper)
 *  Single thread - uses for android main thread
 *  use for :only updating UI, LiveData etc
 *  Never do Heavy work here - freezes the UI.
 *
 *  viewModelScope.launch(Dispatcher.Main){  // runs on main thread
 *      println("running on main thread")
 *      textView.text = "Hello"
 *  }
 *-----------------------------------------------------------
 * 2) Dispatcher.IO
 * A threadpool optimized for blocking I/O operations which uses shared threadpool of up to 64 threads
 * or Runtime.availableProcessors() or whichever is larger
 * use for : network calls, DB updates, IO operations
 *
 * ex: val response = withContext(Dispatcher.IO){
 *              val user = ApiResponse()    -> network call safe here as it runs on IO
 *         }
 *
 * why pool of 64 threads required? Because I/O operations spend more time in waiting state so many threads are waiting simultaneously
 * without burning cpu (kind of not optmized for good cpu usage)
 *
 * ---------------------------------------------------------------
 *
 * 3) Dipatcher.Default
 * A threadpool optimized for cpu intensive work -
 * backed by a pool of Runtime.availableProcesseors (theads) ex: An 8 core processor phone have typically 8 threads
 * use for: sorting large lists, JSON parsing, image processing, encryption etc.
 *
 * ex: val sorted = withContext(Dispatcther.Default){
 *              largelist.sortedBy(it.complextCalculation())
 *          }
 *
 * ---------------------------------------------------------------
 * 4) Dispatcher.Unconfined
 * runs on whatever thread is avaiable at that time.
 * Doesn't switch the threads and resume on same thread.
 * use case: Testing scenario
 * Almost never use this in production.
 * ex:
 * launch(Dispatchers.Unconfined) {
 *     println(Thread.currentThread().name)   // could be anything
 *     delay(100)
 *     println(Thread.currentThread().name)   // could be a different thread!
 * }
 */

// withContext -  The cleanest way to switch Dispatchers
// withContext is a suspend function runs a block of code w.r.t dispatcher and return its result

/**
 *      fun main() = runBlocking{ this runs on Dispatcher.Main
 *          loadUser() // this runs on Dispatcher IO
 *      }
 *
 *   suspend fun loadUser() : User{
 *      return withContext(Dispatcher.IO){ // use of specified Dispatcher
 *          api.fetchUser() // returns the user as a result
 *      }
 *   }
 *
 *   its a suspend function and it can naturally chain without nesting.
 *   it will return value and you don't need to wait.
 *   it will automatically switches to caller dispatcher
 *   its light weight and will not create new coroutine like launch/async
 */


// module 3 Job Cancellation and Exception handling

// Job -  every coroutine has its own lifecycle which is handled by 'Job'
// Job has certain properties and methods
/**
 * Job Properties -
 * job.isActive,
 * job.isCancelled,
 * job.isCompleted
 *
 * Job methods -
 * job.cancel(), -> job request for cancellation
 * job.cancel('reason'), -> job request for cancellation with a reason
 * job.join(), -> suspends until completes
 * job.cancelAndJoin() -> cancel + wait for cleanup
 */


