package com.kaipa.jetpackcompose.ilearn.googlecompose

import java.util.UUID

data class Message( var author:String, var body:String,val id: String = UUID.randomUUID().toString())