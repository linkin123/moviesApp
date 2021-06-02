package com.worklin.movieapp.core

import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

object internetCheck {
    suspend fun isNetWorkAvailable() =
        coroutineScope {
            return@coroutineScope try{
                val sock = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)
                sock.connect(socketAddress, 2000)
                sock.close()
                true
            }catch ( e : Exception){
                false
            }
        }
}