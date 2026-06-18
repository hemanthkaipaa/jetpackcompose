package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

import java.util.Date

fun main (){
    println("hello Welcome to kotlin learning")
    compileVar()
    compileVal()
    stringTemplate()
}
// variables
fun compileVar(){
    var name = "hemanth" //mutable variable
    name = "kaipa Hemanth kumar Reddy"
    println(name)
}

fun compileVal(){ //immutable variable
    val name = "val" // value assigned so we cannot change it later
   // name = "kaipa Hemanth kumar Reddy" // we cannot change, and it will give compile error / val cannot be reassigned
    val newName : String
    newName = "This is val variable" // here we haven't assigned the value so we can change it
    println(newName)
}

fun stringTemplate(){
    val name = "Hemanth"
    val dob = 1994
    val currentYear = 2026
    println ("my name is $name and my age is ${currentYear - dob }")
}

//basic types in kotlin
// Int, Long, Double, Float, Boolean, Char, String

fun basicTypes(){
    val a : Int // variable 'a' declared without initialization
    a = 1000  // variable assigned with value
    val d : Double = 100.00 // explicit type declaration
    val f : Float // variable 'f' declared without initialization
    // print(f) // This will give you an error -> f must be initialized


}
