package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

fun main(){

    println("rcr".isPalindrome())
}
// An extension function let you add a new function to an existing class without modifying its source code or subclassing it
fun String.isPalindrome(): Boolean{
    return this == this.reversed()
}

