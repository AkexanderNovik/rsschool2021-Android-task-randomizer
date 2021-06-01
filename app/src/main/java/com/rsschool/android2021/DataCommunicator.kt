package com.rsschool.android2021

interface DataCommunicator {
    fun passData(min: Int, max: Int)
    fun passToFirstFragment()
    fun passRandomNumber(randomNumber: Int)
}