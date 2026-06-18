package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

fun main(){
    checkIf()
    noTernary()
    whenCheck()
    whenAsExpression()
    forLoop()
    whileLoop()
    doWhileLoop()
}

// kotlin recommends to use when instead of if

fun checkIf(){
    println("--------------------checkIf---------------------")
    var d : Int
    val check = true

    if(check) d = 1 else d = 2
    println("$d")
}
// kotlin doesn't have ternary operator if you want to make it in a single line don't use braces.
fun noTernary(){
    println("--------------------noTernary---------------------")
    val a = 1; val  b = 2;
    println(if(a>b) a else b)
}

//when
// use when instead of if when you have multiple conditions

fun whenCheck(){
    println("--------------------whenCheck---------------------")
    val a = 1
    when(a) {
        1 -> println("a is 1")
        2 -> println("a is two")
        3 -> println("a is two")
        else -> println("a is unknown")
    }
}

// when can be used as expression

fun whenAsExpression(){
    println("--------------------whenAsExpression---------------------")
    val a = 1
    val  result = when(a){
        1 -> println("a is 1")
        2 -> println("a is two")
        else -> println("a is unknown")
    }
    println(result)
}

//Loops and ranges
//  are used to iterate over a range of values
// for iterating 1,2,3,4 we use ' .. ' operator to iterate as 1..4
// for using step iteration we use 1..10 step 2
// for using reverse iteration we use 10 downTo 1 nothing but 10,9,8,7..1 etc

// most common loops are for, while,do-while

fun forLoop(){
    println("--------------------forloop---------------------")
    val loop = listOf(1,2,3,4,5,6)
    for (item in loop){
        println(item)
    }
}

fun whileLoop(){
    println("--------------------whileLoop---------------------")
    val loop = listOf(1,2,3,4,5,6)
    var i=0
    while(i<loop.size){
        println(loop[i])
        i++
    }
}
fun doWhileLoop() {
    println("--------------------do whileLoop---------------------")
    val loop = listOf(1, 2, 3, 4, 5, 6,10,11,12,12,14)
    var i = 0
    do {
        println(loop[i])
        i++
    } while (i<loop.size)
}