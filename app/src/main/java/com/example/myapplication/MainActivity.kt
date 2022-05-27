package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.Constants
import com.example.myapplication.utils.TransformUtils
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var solution = Solution()
        solution.romanToInt("MCMXCIV")

        findViewById<TextView>(R.id.tv).setOnClickListener {
            test3_()
        }
    }

    var s = S()
    var sMiddle = S_Middle()
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

    private fun test2_1() {
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
        var array: IntArray = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        var ret = s.maxSubArray(array)
        Log.d(S.TAG, "array:${array},   ret: ${ret}")
    }

    private fun test6() {
        var array: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 45)
        for (i in array.indices) {
            var ret = s.climbStair1(array[i])
            Log.d(S.TAG, "n: ${array[i]},   ret: ${ret}")
        }
    }


    fun test7() {
        var root = S.TreeNode(5)
        var ret = s.inorderTraversal(root)
        for (i in ret.indices) {
            Log.d(S.TAG, "${ret[i]}, ")

        }
    }

    fun test58() {
//        var str = "abc cdf bfbf"
        var str = "   fly me   to   the moon  "
        var ret = s.lengthOfLastWord(str)
        Log.d(S.TAG, "lengthOfLastWord, $str, ret = ${ret}, ")

    }

    fun test66() {
        var retArray = intArrayOf(9, 9, 9, 9)
        var retArray1 = intArrayOf(1, 6, 7, 9)
        var retArray2 = intArrayOf(1, 6, 7, 3)
        var a = arrayOf(retArray, retArray1, retArray2)

        for (i in a.indices) {
            var ret = s.plusOne(a[i])
            var printStr = Arrays.toString(ret)
            Log.d(S.TAG, "plusOne, ret = ${printStr}, ")
        }


    }

    fun test67() {
        var s1 = "1111"
        var s2 = "1111"
        var ret = s.addBinary(s1, s2)
        Log.d(S.TAG, "addBinary, ret = ${ret}, ")
    }

    fun test101() {
        var s1 = "1111"
        var s2 = "1111"
        var ret = s.addBinary(s1, s2)
        Log.d(S.TAG, "addBinary, ret = ${ret}, ")
    }

    fun test136() {
//        var s1 = 4
//        var s2 = 5
//        var s3 = 4
//        var ret = s1 xor s2
//        var ret1 = s1 xor s3
//        Log.d(S.TAG, "异或, ret = ${ret},ret1 = $ret1 ")

        var array: IntArray = intArrayOf(4, 1, 2, 1, 2)
        var ret = s.singleNumber(array)
        Log.d(S.TAG, "ret: $ret")

    }

    fun test121() {

        var retArray = intArrayOf(7, 1, 5, 3, 6, 4)
        var retArray1 = intArrayOf(10, 20, 1, 7, 9, 5)
        var retArray2 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var a = arrayOf(retArray, retArray1, retArray2)

        for (i in a.indices) {
            var ret = s.maxProfit(a[i])
            Log.d(S.TAG, "maxProfit, ret = ${ret}, ")
        }

    }

    fun test169() {

        var retArray = intArrayOf(1, 3, 3, 2, 1, 3, 3)
        var retArray1 = intArrayOf(1, 1, 1, 2, 2, 2, 2)
        var retArray2 = intArrayOf(1, 2, 3, 2, 1, 2, 5, 2)
        var a = arrayOf(retArray, retArray1, retArray2)

        for (i in a.indices) {
            var ret = s.majorityElement(a[i])
            Log.d(S.TAG, "majorityElement, ret = ${ret}, ")
        }

    }

    fun testTransformArrayToListNode() {
        var array = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        var headNode = TransformUtils.arrayToListNode(array)
        while (null != headNode) {
            Log.d(Constants.TAG, "${headNode.`val`}")
            headNode = headNode.next
        }
    }

    fun test206() {

        var array = intArrayOf(1, 2, 3, 4, 5)
        var ret = s.reverseList1(TransformUtils.arrayToListNode(array))
        while (ret != null) {
            Log.d(S.TAG, "${ret.`val`}")
            ret = ret.next
        }

    }
    fun test2() {

        var array = intArrayOf(2,4,3)
        var array2 = intArrayOf(5,6,4)
        var l1 = TransformUtils.arrayToListNode(array)
        var l2 = TransformUtils.arrayToListNode(array2)
        var ret = sMiddle.addTwoNumbers(l1,l2)
        while (ret != null) {
            Log.d(S.TAG, "${ret.`val`}")
            ret = ret.next
        }

    }
    fun test3_() {
        var a1 = "pnbw".substring(4) + 'w'
        Log.d(S.TAG, "越界substring: $a1")


        var a = arrayOf("abcabcbb", "bbb", "pwwkew","jbpnbwwd")
        for (i in a.indices){
            var ret = sMiddle.lengthOfLongestSubstring(a[i])
            Log.d(S.TAG, "s: ${a[i]}, answer: ${ret} ")
        }

    }

    fun test283() {
        var array = intArrayOf(0, 1, 0, 3, 10)
        s.moveZeroes(array)
    }

    fun test461() {
        var x = 1
        var y = 4
        var ret = s.hammingDistance(x, y)
        Log.d(S.TAG, "$ret")
    }

    fun test448() {
        var array = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)
        var ret = s.findDisappearedNumbers(array)
        var sb = StringBuilder()
        for (i in ret.indices) {
            sb.append(ret[i])
            sb.append(", ")
        }
        Log.d(S.TAG, sb.toString())
    }

    fun test10000() {
        var sb = StringBuilder()
        for (i in 0..100) {
            sb.append(s.countOnes(i))
            sb.append(", ")
        }
        Log.d(Constants.TAG, sb.toString())
    }
}