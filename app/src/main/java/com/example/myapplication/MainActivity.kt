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
            test6()
        }
    }

    var s = S()
    private fun test1() {
        var array: IntArray = intArrayOf(1, 1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 7, 8)
//        var array : IntArray = intArrayOf(1,1,1,1,1,1,1,1)
        var ret = s.removeDuplicates1(array)
        for (element in array) {
            Thread.sleep(30)
            Log.d(S.TAG, "${element}")
        }
        Log.d(S.TAG, "ret: ${ret}")

    }

    private fun test2() {
        var array: IntArray = intArrayOf(2, 1, 1, 2, 3, 3, 3, 4, 5, 2, 6, 7, 8)
        var ret = s.removeElement(array, 2)
        for (element in array) {
            Thread.sleep(30)
            Log.d(S.TAG, "${element}")
        }
        Log.d(S.TAG, "ret: ${ret}")

    }

    private fun test3() {
        test3_1()
        test3_2()
        test3_3()
        test3_4()
        test3_5()
        test3_6()
        test3_7()
    }

    private fun test3_1() {
//        var s1 = "mississippi"
//        var s2 = "issip"
//        var s1 = "aaa"
//        var s2 = "aaaa"
//        var s1 = "aabaabbbaabbbbabaaab"
//        var s2 = "abaa"

        var s1 = "a"
        var s2 = "a"
        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "ret: ${ret}")

    }

    private fun test3_2() {
//        var s1 = "mississippi"
//        var s2 = "issip"
//        var s1 = "aaa"
//        var s2 = "aaaa"
//        var s1 = "aabaabbbaabbbbabaaab"
//        var s2 = "abaa"

        var s1 = "a"
        var s2 = "a"
        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1, s2:$s2, ret: ${ret}")

    }

    private fun test3_3() {
        var s1 = "aaa"
        var s2 = "aaaa"

        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1, s2:$s2, ret: ${ret}")

    }

    private fun test3_4() {
        var s1 = "aabaabbbaabbbbabaaab"
        var s2 = "abaa"
        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1, s2:$s2, ret: ${ret}")

    }

    private fun test3_5() {
        var s1 = "mississippi"
        var s2 = "issip"

        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1, s2:$s2, ret: ${ret}")
    }

    private fun test3_6() {
        var s1 = "aaaaa"
        var s2 = "bba"

        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1,  s2:$s2,  ret: ${ret}")
    }

    private fun test3_7() {
        var s1 = "abc"
        var s2 = "c"

        var ret = s.strStr(s1, s2)
        Log.d(S.TAG, "s1:$s1, s2:$s2, ret: ${ret}")
    }

    private fun test4() {
        test4_1()
        test4_2()
        test4_3()
    }

    private fun test4_1() {
        var array: IntArray = intArrayOf(1, 3, 5, 6)
        var target = 5
        var ret = s.searchInsert(array, target)
        Log.d(S.TAG, "array:${array.toString()},  target:$target,  ret: ${ret}")
    }

    private fun test4_2() {
        var array: IntArray = intArrayOf(1, 3, 5, 6)
        var target = 2
        var ret = s.searchInsert(array, target)
        Log.d(S.TAG, "array:${array.toString()},  target:$target,  ret: ${ret}")
    }

    private fun test4_3() {
        var array: IntArray = intArrayOf(1, 3, 5, 6)
        var target = 7
        var ret = s.searchInsert(array, target)
        Log.d(S.TAG, "array:${array.toString()},  target:$target,  ret: ${ret}")
    }

    private fun test5() {
        //[-2,1,-3,4,-1,2,1,-5,4]
        var array: IntArray = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)
        var ret = s.maxSubArray(array)
        Log.d(S.TAG, "array:${array},   ret: ${ret}")
    }

    private fun test6() {
        var array: IntArray = intArrayOf(1,2,3,4,5,6,7,8,9,45)
        for (i in array.indices) {
            var ret = s.climbStair1(array[i])
            Log.d(S.TAG, "n: ${array[i]},   ret: ${ret}")
        }
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
            Log.d(S.TAG, "${n?.`val`}")
            n = n.next
        }
    }

    fun test7() {
        var root = S.TreeNode(5)
        var ret = s.inorderTraversal(root)
        for (i in ret.indices){
            Log.d(S.TAG, "${ret[i]}, ")

        }
    }
}