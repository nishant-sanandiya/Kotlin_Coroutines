package com.example.coroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // run in sequence(line after line).
        // takes 6 seconds for finish full block execution.
        lifecycleScope.launch {
            Log.d(TAG, "Start")
            val response = makeApiCall()
            val response2 = makeApiCall2()
            Log.d(TAG, response)
            Log.d(TAG, response2)
            Log.d(TAG, "End")
        }

        // run in parallel using awaitAll function block and provide a response of both api in just 4 seconds.
        // takes only 4 seconds for finish full block execution.
        lifecycleScope.launch {
            Log.d(TAG, "Start")
            val response = awaitAll(async { makeApiCall() }, async { makeApiCall2() })
            Log.d(TAG, response.toString())
            Log.d(TAG, "End")
        }

        // we can switch coroutines context at runtime.
        lifecycleScope.launch {
            Log.d(TAG, "Start")
            val response = makeApiCall()
            withContext(Dispatchers.Main) {
                val response2 = makeApiCall2()
                Log.d(TAG, response2)
            }
            Log.d(TAG, response)
            Log.d(TAG, "End")
        }


        // contains coroutine in a job variable and cancel whenever you want using job.
        val job = lifecycleScope.launch {
           repeat(5){
               Log.d(TAG,"Coroutines Still Working")
               delay(1000)
           }
        }

        runBlocking {
            delay(3000)
            job.cancel()
        }


    }

    private suspend fun makeApiCall(): String {
        delay(2000)
        Log.d(TAG, "2 Seconds Delay")
        return "Return After 2 Seconds"
    }

    private suspend fun makeApiCall2(): String {
        delay(4000)
        Log.d(TAG, "4 Seconds Delay")
        return "Return After 2 Seconds"
    }
}