package com.example.todoapp.destination

interface Destinations {
    val route : String

}

object Welcome : Destinations{
    override val route = "Weclome"
}

object MobileNumberScreen : Destinations{
    override val route = "MobileNumberScreen"
}