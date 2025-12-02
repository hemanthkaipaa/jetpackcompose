package com.kaipa.jetpackcompose.kotlintest

import java.util.stream.Collectors

fun main(array: Array<String>){
    collectionsList()
    collectionSet()
    collectionMap()
}

fun lambdas(){
    val arr = listOf(1,2,24,55,6)

    arr.forEach {
        if(it >2){
            println(it)
        }
    }
    val res = arr.filter {
        it > 24
    }
    println(res)

    val lamb : () -> Unit = {
        println("this is normal lambda without any parameters")
    }
    lamb()

    val paramLamb : (Int , String) -> String = {
            age,name -> "my name is $name and my age is $age"
    }
    println(paramLamb(31,"Hemanth"))

    fun trail(batsman:String, score : (Int, Int, Float) -> String){
        println("The batsman $batsman has score ${score.invoke(135,160,50.5f)}")
    }
    trail(batsman = "Kohli"){
            runs,strikeRate,avg -> "hello there"
    }
}

fun collectionsList(){
    var arr  = listOf(1,2,3,4,5)
    var arr2  = mutableListOf<Int>(1,2,3,4,5,6)
    arr.forEach{
        println(it)
    }
    println("--------------------")
    println(arr[1]) // prints 2
    println(arr.get(4)) // prints 5
    println(arr.first()) // prints 1
    println(arr.last()) // prints 5
    println(arr2.firstOrNull()) // prints null
    arr2.add(arr2.last(),100)
    println(arr2)
    arr2.remove(arr2.lastOrNull())
    println(arr2)
    arr2.removeIf { it > 5 }
    println(arr2)
    arr2.replaceAll{
        it -> 2
    }
    println(arr2)

    println(arr.reversed())
    println(arr.stream().anyMatch { it == 3 })
    val res = arr.stream().filter { it >2 }
    println(res.collect(Collectors.toList()))
    println("----------------------------------------")
}

fun collectionSet(){
    val set = setOf(1,2,3,4,"Hello")
    println(set)
    val mutableSet = mutableSetOf(1,4,5,7,7,4,6,4)
    mutableSet.add(22)
    val addSet = setOf(33,4,55,6,23)
    mutableSet.addAll(addSet)
    val sortedSet = mutableSet.sorted()
    println(mutableSet)
    println(sortedSet)
    val desSortSet = mutableSet.sortedDescending()
    println(desSortSet)
    val sortwith = mutableSet.sortedWith { a, b -> b-a }
    println(sortwith)

}

fun collectionMap(){

}
