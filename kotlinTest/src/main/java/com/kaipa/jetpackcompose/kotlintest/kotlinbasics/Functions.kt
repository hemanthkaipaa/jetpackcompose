package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

fun main(){
    normalFunction()
    functionWithParams(1,2)
    println("This is function with return ${functionWithReturn(1,2)}")
    functionWithNamedArgs(phone = "7899619971", message ="Hello there!")
    functionWithDefaultParamValues()
    // singleExpression functions
    println("---------------------------- singleExpressionFunction ----------------------------")
    println("This is a single expression function ${singleExpressionFunction(1,2,4)}")
    println("---------------------------- lambda expression with params and body with explicit type annotation ----------------------------")
    println(lambda(2,2))
    println("---------------------------- lambda expression with params and body with implicit type annotation ----------------------------")
    println(lambdaWithImplicitType(3,3))
    println("---------------------------- lambda expression with single param and body ----------------------------")
    singleParamLambda(4)
    println("---------------------------- lambda expression with trailing lambda ----------------------------")
    repeats(3){
        // write network retry code here
        println("This is trailing lambda body repeating network $it")
    }
    println("---------------------------- lambda expression Anonymous function ----------------------------")
    println(anonymousFunction(2,5))
}

// functions
fun normalFunction(){
    println("---------------------------- normalFunction ----------------------------")
    println("This is normal function")
}

fun functionWithParams(x:Int, y:Int){
    println("---------------------------- functionWithParams ----------------------------")
    println("This is function with params $x and $y")
}

fun functionWithReturn(x:Int,y:Int):Int{
    println("---------------------------- functionWithReturn ----------------------------")
    return x+y
}

fun functionWithNamedArgs(message : String, phone:String){
    println("---------------------------- functionWithNamedArgs ----------------------------")
    println("This is function with named args message = $message and phone $phone")
}

fun functionWithDefaultParamValues(x:Int = 1, y:Int = 2){
    println("---------------------------- functionWithDefaultParamValues ----------------------------")
    println("This is function with default param values $x and $y")
}

// single expression functions
fun singleExpressionFunction(x:Int,y:Int, z:Int) = x+y+z


// lambda expressions or anonymous functions


// syntax Explicit — type declared on the variable
// val add: (Int, Int) -> Int = { a, b -> a + b }
val lambda : (Int,Int) -> Int = {
        a,b ->
        var result = a
        for(i in 1..5){
            result+=i
        }
        result + b
}

// Implicit — type inferred by compiler from params
// val add = { a: Int, b: Int -> a + b }
val lambdaWithImplicitType = {a :Int, b :Int ->
    var j =0
    for(i in 1 until 3){
       j+=i
    }
    j+a+b
}

// single param lambda which skips the -> use

val singleParamLambda : (Int) -> Unit = {
    println(it*2) // here it is variable of Int type.
}

// trailing lambda
fun repeats (a:Int, lamb : (Int)->Unit){
    for(i in 0 until  a){
        lamb(i)
    }
}

// anonymous function
val anonymousFunction = fun(a:Int, b:Int):Int{
    return a+b;
}


