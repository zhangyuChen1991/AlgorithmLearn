package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.Constants
import com.example.myapplication.utils.TransformUtils
import com.example.myapplication.utils.Traversal
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var s = S()
    var sMiddle = S_Middle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var solution = Solution()
        solution.romanToInt("MCMXCIV")

        findViewById<TextView>(R.id.tv).setOnClickListener {
            test105()
        }
    }

    private fun test96() {
        var ret = sMiddle.numTrees(3)
        Log.w(S.TAG,"ret: ${ret}")
    }

    private fun test102() {
        var array = arrayOf(3,9,20,null,null,15,7)
//        var array = arrayOf(3,1,5,0,2,4,6,null,null,null,3)
        var node = Traversal().arrayToTree(array)
        var ret = sMiddle.levelOrder(node)
        Log.w(S.TAG,"ret: ${sMiddle.listListStr(ret)}")
    }

    private fun test105() {
        //[3,9,20,15,7]
        //[9,3,15,20,7]
        var array = intArrayOf(3,9,20,15,7)
        var array1 = intArrayOf(9,3,15,20,7)
        var ret = sMiddle.buildTree(array,array1)

        Log.w(S.TAG,"ret:")

    }

    private fun test114() {
        var array = arrayOf(1,2,5,3,4,null,6)
//        var array = arrayOf(3,1,5,0,2,4,6,null,null,null,3)
        var node = Traversal().arrayToTree(array)
        var ret = sMiddle.flatten(node)
    }

    private fun test98() {
        var array = arrayOf(32,26,47,19,null,null,56,null,27)
//        var array = arrayOf(3,1,5,0,2,4,6,null,null,null,3)
        var node = Traversal().arrayToTree(array)
        var ret = sMiddle.isValidBST(node)
        Log.w(S.TAG,"ret: ${ret}")
    }

    private fun test79() {
//        var array1 = charArrayOf('A','B','C','E')
//        var array2 = charArrayOf('S','F','C','S')
//        var array3 = charArrayOf('A','D','E','E')

        var array1 = charArrayOf('A','B','C','E')
        var array2 = charArrayOf('S','F','C','S')
        var array3 = charArrayOf('A','D','E','E')

//        var array1 = charArrayOf('C','A','A')
//        var array2 = charArrayOf('A','A','A')
//        var array3 = charArrayOf('B','C','D')
        var d = arrayOf(array1,array2,array3)
        var ret = sMiddle.exist(d,"SEE")
//        var ret = sMiddle.exist(d,"ABCB")
//        var ret = sMiddle.exist(d,"AAB")

    }

    private fun test78() {
        var array1 = intArrayOf(1,2,3,4)
        sMiddle.subsets(array1)
    }
    private fun test75() {
        var array1 = intArrayOf(2,0,2,1,1,0)
        var array2 = intArrayOf(0,2,1,0,1,2,0,1,2,0,1,2,0,1,2)
        var array3 = intArrayOf(1,0)
        sMiddle.sortColors(array3)
    }
    private fun test64() {
        var array1 = intArrayOf(1,3,1)
        var array2 = intArrayOf(1,5,1)
        var array3 = intArrayOf(4,2,1)
        var d = arrayOf(array1,array2,array3)
        var ret = sMiddle.minPathSum(d)
        Log.w(S.TAG,"ret: ${ret}")

    }
    private fun test36() {
//[[".",".","4",".",".",".","6","3","."],[".",".",".",".",".",".",".",".","."],[],[],[],[],[],[],[]]

        var array1 = arrayOf(".",".","4",".",".",".","6","3",".")
        var array2 = arrayOf(".",".",".",".",".",".",".",".",".")
        var array3 = arrayOf("5",".",".",".",".",".",".","9",".")
        var array4 = arrayOf(".",".",".","5","6",".",".",".",".")
        var array5 = arrayOf("4",".","3",".",".",".",".",".","1")
        var array6 = arrayOf(".",".",".","7",".",".",".",".",".")
        var array7 = arrayOf(".",".",".","5",".",".",".",".",".")
        var array8 = arrayOf(".",".",".",".",".",".",".",".",".")
        var array9 = arrayOf(".",".",".",".",".",".",".",".",".")
        var d = arrayOf(array1,array2,array3,array4,array5,array6,array7,array8,array9)
//        sMiddle.isValidSudoku(d)
    }
    private fun test62() {
        Log.w(S.TAG,"起始时间${System.currentTimeMillis()}")
        sMiddle.uniquePaths(99,99)
        sMiddle.uniquePaths(3,7)
        Log.w(S.TAG,"结束时间${System.currentTimeMillis()}")

//        sMiddle.uniquePaths(5,4)
//        sMiddle.uniquePaths(5,3)
//        sMiddle.uniquePaths(2,5)
//        sMiddle.uniquePaths(1,5)
    }
    private fun test49() {
//        var array = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
        var array = arrayOf("hhhhu","tttti","tttit","hhhuh","hhuhh","tittt")
        var ret = sMiddle.groupAnagrams2(array)
//        var currStrs = ArrayList<String>()
//        var stringBuilder = StringBuilder()
//        sMiddle.strsOfStr("hhhhu",currStrs,stringBuilder)
    }
    private fun test46() {
        var array: IntArray = intArrayOf(1,2,3,4)
//        var array: IntArray = intArrayOf(2,3,5)
//        var array: IntArray = intArrayOf(5,5,6,6,6,7,7,8,8,8,9)
        var ret = sMiddle.permute(array)
    }
    private fun test56() {
        var array: Array<IntArray> = arrayOf(intArrayOf(1,4),intArrayOf(0,2),intArrayOf(3,5))
//        var array: IntArray = intArrayOf(2,3,5)
        var ret = sMiddle.merge(array)
    }

    private fun test55() {
        var array: IntArray = intArrayOf(2,3,1,1,4)
//        var array: IntArray = intArrayOf(3,2,1,0,4)
//        var array: IntArray = intArrayOf(5,5,6,6,6,7,7,8,8,8,9)
        var ret = sMiddle.canJump(array)
        Log.d(S.TAG, "ret： $ret")

    }
    private fun test48() {
        var array1: IntArray = intArrayOf(1,2,3)
        var array2: IntArray = intArrayOf(4,5,6)
        var array3: IntArray = intArrayOf(7,8,9)
//        var array1: IntArray = intArrayOf(1,2,3,4)
//        var array2: IntArray = intArrayOf(5,6,7,8)
//        var array3: IntArray = intArrayOf(9,10,11,12)
//        var array4: IntArray = intArrayOf(13,14,15,16)

        var matric: Array<IntArray> = arrayOf(array1,array2,array3)
//        var matric: Array<IntArray> = arrayOf(array1,array2,array3,array4)
        var ret = sMiddle.rotate1(matric)
    }
    private fun test39() {
        var array: IntArray = intArrayOf(3,5,8)
//        var array: IntArray = intArrayOf(2,3,5)
//        var array: IntArray = intArrayOf(5,5,6,6,6,7,7,8,8,8,9)
        var ret = sMiddle.combinationSum(array,11)
    }

    fun test17() {
        var ret = sMiddle.letterCombinations("234")

        Log.d(S.TAG, "answer: ${ret} ")

    }
    fun test22() {
//        sMiddle.generateParenthesis(3)
        sMiddle.generate(3)

    }

    private fun test31() {
        var array: IntArray = intArrayOf(2,3,1)
        var ret = sMiddle.nextPermutation(array)
    }

    private fun test34() {
        var array: IntArray = intArrayOf(1)
//        var array: IntArray = intArrayOf(4,5,6,7,0,1,2)
//        var array: IntArray = intArrayOf(5,5,6,6,6,7,7,8,8,8,9)
        var ret = sMiddle.searchRange(array,0)
        sMiddle.printArray(ret)
    }

    private fun test33() {
//        var array: IntArray = intArrayOf(3,5,1) //0
//        var array: IntArray = intArrayOf(4,5,6,7,0,1,2)
        var array: IntArray = intArrayOf(3,1)
        var ret = sMiddle.search(array,1)
        Log.d(S.TAG, "answer: ${ret} ")
    }

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
        root.left = S.TreeNode(3)
        root.right = S.TreeNode(9)
        root.left!!.left = S.TreeNode(1)
        root.left!!.right = S.TreeNode(6)
        var ret = s.inorderTraversal1(root)
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

    fun test5_() {

        var a = arrayOf("aba")
        for (i in a.indices){
            var ret = sMiddle.longestPalindrome(a[i])
            Log.d(S.TAG, "s: ${a[i]}, answer: ${ret} ")
        }

    }

    fun test283() {
        var array = intArrayOf(0, 1, 0, 3, 10)
        s.moveZeroes(array)
    }

    fun test11() {
        var array = intArrayOf(1,8,6,2,5,4,8,3,7)
        var ret = sMiddle.maxArea(array)
        Log.d(S.TAG, "answer: ${ret} ")

    }
    fun test15() {
        var array = intArrayOf(-1,0,1,2,-1,-4)
        var ret = sMiddle.threeSum(array)
        Log.d(S.TAG, "answer: ${ret} ")

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