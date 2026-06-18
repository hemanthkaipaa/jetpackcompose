package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

fun main(){
    letUsage()
    transformValue()
    runUsage()
    withUsage()
    withMainUsage()
    applyUsage()
    alsoUsage()
}

// scope functions
// There are 5 looks similar but different.
/**
 * 1. let  ->   it      lambda result
 * 2. run  ->   this    lambda result
 * 3. with ->   this    lambda result
 * 4. apply ->  this    object itself
 * 5. also ->   it      object itself
 */

// let - access object via 'it' and returns lambda result
// main use case is null safety:
var scopeName : String? = "Hemanth"
fun letUsage(){
    val letResult = scopeName?.let {
        println("This is a let usage example $it")
        it.uppercase()
    }
    println(letResult)
}
// let use case 2 transform value

fun transformValue(){
    val input = "Hello"
    val result = input.let {
        it.trim()
        it.uppercase()
        it.replace("H","#")
    }
    println("Replacing $result")
}

// run
// run access object instance via this

fun runUsage(){
    val name = "Hemanth"
    val result = name.run {
        println("This is run usage $this")
        this.replace("Hemanth", "Kaipa").uppercase()
    }
    println("The run result = $result")
}
// let and run both are same only difference is let use it nad run this -> which is implicit no need to right this

// with
// same as run access object via this keyword and returns lambda result, but called differently

fun withUsage(){
    val name = "hemanth"
    val result = with(name){
        uppercase()
    }
    println(result)
}

// run and with are same but only difference is how we call it
// main use case

data class ScopeUser(val name: String ,val age : Int )
fun withMainUsage(){
    val scopeUser = ScopeUser("Hemanth",31)
    with(scopeUser){
        println(name)
        println(age)
        println("This is with main usecase ${name} age $age")
    }
}

// apply
// apply will return the object itself

data class ScopeEmployee(var name: String ="",  var idNo:Int= 0)
fun applyUsage() {
    val scopeEmployee = ScopeEmployee().apply {
        name = "hemanth"
        idNo = 1001
    }
    println(scopeEmployee)
}

// also access object via it and returns object itself
fun alsoUsage() {
    val scopeEmployee = ScopeEmployee().also {
        it.name = "HK"
        it.idNo = 1111
    }
    println(scopeEmployee)
}

