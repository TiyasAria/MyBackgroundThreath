package com.tiyas.mybackgroundthreath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.tiyas.mybackgroundthreath.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private  lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val btnStart = mainBinding.btnStart
        val tvStatus = mainBinding.tvStatus


        /**
         * Code ini untuk backgroundThread menggunakan executor dan hanlder
         *   code untuk menambahkan executor
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

              btnStart.setOnClickListener {
              executor.execute {
                        try {
        ////                simulasi proses compressinhg

                            for(i in 0..10){
                             Thread.sleep(500)
                            val percentage = i *10
        ////                        ditambahkan dengan handler post , untuk pendamping dg executornya
                            handler.post{
                                 if (percentage == 100){
                                     tvStatus.setText(R.string.task_completed)
                              } else{
                                   tvStatus.text = String.format(getString(R.string.compressing), percentage)
                                   }
                           }
                         }
                      } catch (e: InterruptedException){
                          e.printStackTrace()
                        }
                  }
             }
         */

        btnStart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default){
//                simulasi proses di background threaad
                for (i in 0..10){
                    delay(500)
                    val percentage = i* 10
                    withContext(Dispatchers.Main){
                        if(percentage == 100){
                            tvStatus.setText(R.string.task_completed)
                        } else{
                            tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }

    }
}