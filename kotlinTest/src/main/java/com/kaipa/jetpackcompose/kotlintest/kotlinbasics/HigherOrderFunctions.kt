package com.kaipa.jetpackcompose.kotlintest.kotlinbasics


// a function that either can have a function as a param or a return type or both = Higher order functions
// we'll cover
// 1. Function as a parameter
// 2. Function as a return type
// ----------------------------------below are used everywhere in code -----------------------------
// 3. map
// 4. filter
// 5. reduce
// 6. fold
// 7. forEach
// 8. flatmap
// 9. groupBy
// 10. sortedBy
//--------------------------------------------------------------------------------------------------

fun main(){
    val sum = operate(4,6){
            x,y -> x+y
    }
    val mul = operate(4,6){
            x,y -> x*y
    }
    println("Operate sum is $sum")
    println("Operate mul is $mul")
    trailFunction()
    lambdaInPara()
    storedLambda()
    functionReference()
    functionAsAReturnType()
    _HoMap()
    _HoFilter()
    _HoReduce()
    _HoFold()
    _HoForEach()
    _HoFlatten()
    _HoFlatMap()
}
// Function as a parameter

fun operate(x:Int,y:Int, action : (Int,Int) -> Int): Int{
     return action(x,y)
}

// multiple way to pass a function

fun doOperate(x:Int,action:(Int) -> Int):Int{
    return action(x)
}

// trailing lambda

fun trailFunction(){
    val mul = doOperate(5){
        it * 2
    }
    println("trailing lambda = $mul")
}

// lambda in parenthesis
fun lambdaInPara(){
    val mul = doOperate(5, action = {it * 2})
    println("lambda in para = $mul")
}

// stored lambda passed as a variable to a function
fun storedLambda(){
    val double : (Int) -> Int = {it *3}
    val mul = doOperate(3,double)
    println("stored lambda $mul")
}

// function reference

fun functionReference(){
    fun doubleIt(x:Int):Int = x * 3
    val mul = doOperate(5,::doubleIt)
    println("functionReference  $mul")

}

// --------------- function as a return type -------------------------------------------------------

fun getOperations(type: String): (Int,Int) -> Int{
    return when(type){
        "sum"       -> {x, y -> x+y}
        "mul"       -> {x, y -> x*y}
        else        -> {x, y -> 0}
    }
}

fun functionAsAReturnType(){
    val add = getOperations("sum")
    println("add function as a parameter ${add(5,5)}")

    val mul = getOperations("mul")
    println("mul as a function as a return type ${mul(7,7)}")
}

// map -> transforms each element of a list and transforms as a new modified list

fun _HoMap(){
    val list = listOf(1,2,4,6,7)
    val latestList = list.map{
        it * 2
    }
    println(latestList)
    val addStringsToNumbers = list.map{"Number $it"}
    println("add strings to given list of real numbers $addStringsToNumbers")
}

// filter

fun _HoFilter(){
    val numbers = listOf(1,2,3,4,5,6,7,8)
    val fil =  numbers.filter { it % 2 ==0 }
    println("filter by even numbers = $fil")
    // filterNot -> opposite of filter
    // filterIsInstance -> it checks the type and filter with the type items.filterInstance<String>()
    // partition -> splits ino multiple
}

// reduce -> combines all elements into a single value

fun _HoReduce(){
    val list = listOf(1,2,3,4,5,6)
    val reduceValue = list.reduce { initial,second -> initial + second  }
    println("reduce value of sum of list = $reduceValue")

    // reduce crashes on empty list instead we can use reduceOrNull
}

// fold is same as reduce but here with fold we need to supply the initial value

fun _HoFold(){
    val list = listOf(2,3,4,5)
    val fold = list.fold(0){
        acc, i ->
        print("acc = $acc and i = $i ")
        acc + i
    }
    println(fold)
    //unlike reduce we don't get crash if the list is empty as we are passing fold with initial
    // always prefer fold over reduce as it is safer
}

// forEach - similar to for loop but different in functional, foreach has no return value
fun _HoForEach(){
    val numbers = listOf(1,2,4,5,6,7)
    numbers.forEach {
        println(" $it")
    }
}

// flatmap

fun _HoFlatMap(){
    data class User(val name: String, val roles: List<String>)

    val users = listOf(
        User("Hemanth", listOf("admin", "dev")),
        User("Ravi", listOf("dev"))
    )

    users.map { it.roles }       // [[admin, dev], [dev]]      ← nested
    users.flatMap { it.roles }   // [admin, dev, dev]          ← flat
    println(users)
}

// flatten
fun _HoFlatten(){
    val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    nested.flatten()  // [1, 2, 3, 4, 5]
    println(nested)
}





