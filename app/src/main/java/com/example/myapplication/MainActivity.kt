package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var solution = Solution()
        solution.romanToInt("MCMXCIV")

        findViewById<TextView>(R.id.tv).setOnClickListener {
            test2()
        }

    }
    var s = S()
    private fun test1() {
        var array : IntArray = intArrayOf(1,1,2,2,2,3,3,3,4,5,6,7,8)
//        var array : IntArray = intArrayOf(1,1,1,1,1,1,1,1)
       var ret =  s.removeDuplicates1(array)
        for (element in array){
            Thread.sleep(30)
            Log.d(S.TAG,"${element}")
        }
        Log.d(S.TAG,"ret: ${ret}")

    }
    private fun test2() {
        var array : IntArray = intArrayOf(2,1,1,2,3,3,3,4,5,2,6,7,8)
       var ret =  s.removeElement(array,2)
        for (element in array){
            Thread.sleep(30)
            Log.d(S.TAG,"${element}")
        }
        Log.d(S.TAG,"ret: ${ret}")

    }
    private fun test() {
        var n1_1 = ListNode(1)
        var n1_2 = ListNode(3)
        var n1_3 = ListNode(5)
        var n1_4 = ListNode(7)
        n1_1.next = n1_2
        n1_2.next = n1_3
        n1_3.next = n1_4

        var n2_1 = ListNode(2)
        var n2_2 = ListNode(3)
        var n2_3 = ListNode(6)
        var n2_4 = ListNode(8)
        n2_1.next = n2_2
        n2_2.next = n2_3
        n2_3.next = n2_4

        var n = s.mergeTwoLists(n1_1, n2_1)
        while (n?.next != null) {
            Log.d(S.TAG, "${n?.data}")
            n = n.next
        }
    }
}