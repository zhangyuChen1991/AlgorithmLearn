package com.example.myapplication

import kotlin.jvm.Volatile
import com.example.myapplication.Test.ProduceThread
import com.example.myapplication.Test.CustomerThread

/**
 * Created by zhangyu on 2022/6/30.
 */
class Test {
    //请使用两个线程实现生产者，消费者模型：
    //1. 其中一个线程完成对某个对象的int成员变量的增加操作，即每次加1
    //2. 另一个线程完成对该对象的成员变量的减操作，即每次减1;
    //同时要保证该变量的值不会小于0，也不会大于1，该变量的初始值为0
    @Volatile
    private var count = 0

    internal inner class CustomerThread : Thread() {
        override fun run() {
            super.run()
            try {
                sleep(100)
                if (count == 1) {
                    count--
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    internal inner class ProduceThread : Thread() {
        override fun run() {
            super.run()
            try {
                sleep(100)
                if (count == 0) {
                    count++
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun test() {
        ProduceThread().start()
        CustomerThread().start()
    }
}