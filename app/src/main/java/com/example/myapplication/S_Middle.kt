package com.example.myapplication

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

/**
 * 中等难度
 * Created by zhangyu on 2022/5/25.
 */
class S_Middle {

    /**
     * 2. 两数相加
     * 两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0开头。
    具体题意看leetcode，有图

    好像没啥难度，就递归处理每一位，模拟竖式加法。注意最末尾进位导致多出一位的情况，还有对应位数一个为空一个不为空的情况。递归返回条件两个节点的next都为空并且没有进位(两个next都为空且有进位则处理完进位之后返回)
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        if (null == l1) return l2
        if (null == l2) return l1
        addTwoNumbers(l1, l2, 0)
        return l1
    }

    /**
     * j是进位
     */
    fun addTwoNumbers(l1: ListNode, l2: ListNode, j: Int) {

        var sum = l1.`val` + l2.`val` + j
        l1.`val` = sum % 10
        var nextJ = sum / 10

        if (null == l1.next && null == l2.next) {
            if (nextJ != 0) {
                l1.next = ListNode(nextJ)
            }
            return
        } else if (l1.next == null && l2.next != null) {
            l1.next = ListNode(0)
        } else if (l1.next != null && l2.next == null) {
            l2.next = ListNode(0)
        }
        addTwoNumbers(l1.next!!, l2.next!!, nextJ)
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。

    示例1:

    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:

    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:

    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。

    思路：分治法，拆分任务，首先答案肯定是以某个字符来结尾的，从第一个字符开始找，以第一个字符结尾的最长子字符串就是它本身，然后看第二个，如果当前的子串包含当前的字符，就从重复的那个位置开始往后截断，
    然后加上当前字符，就是以当前字符结尾的最长子串。找出以每个字符结尾的最长子串之后，记录下它们之中最长的那个，返回其长度，就是最终答案。跟从数组中找出和最大的连续子数组思路一样。
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        var currentMaxSubStr = ""
        var currSubStr = ""

        for (i in s.indices) {
            if (currSubStr.contains(s[i])) {
                var start = currSubStr.indexOf(s[i])
                currSubStr = currSubStr.substring(start + 1) + s[i]
            } else {
                currSubStr += s[i]
            }

            if (currentMaxSubStr.length < currSubStr.length) {
                currentMaxSubStr = currSubStr
            }
        }

        return currentMaxSubStr.length
    }

    /**
     * 5. 最长回文子串
     *
     * 思路，拆分任务，最长回文子串的答案肯定是以某个字符为中心，或者以某个相同字符的子串为中心的 一个子串
     *那就从第一个字符开始遍历，找以以个字符为中心，或者和这个字符相同的子串为中心的 回文子串
     * 遍历时，记录下当前的最长回文子串
     * 最后返回结果
     */
    fun longestPalindrome(s: String): String {

        var answerStartIndex = -1
        var answerEndIndex = -1
        var currMaxLength = 0

        var sameCharStartIndex = 0
        var sameCharEndIndex = 0
        var sameCharCount = 1
        for (i in s.indices) {
            if (sameCharCount == 1) {
                sameCharStartIndex = i
            }
            if (i < s.length - 1 && s[i] == s[i + 1]) {
                sameCharCount++
                continue
            }
            sameCharEndIndex = sameCharStartIndex + sameCharCount - 1

            var count = 1
            //找出以i或者与i相同的连续字符组成的子串 为中心的最长回文子数组
            while (sameCharStartIndex - count >= 0 && sameCharEndIndex + count < s.length) {
                if (s[sameCharStartIndex - count] == s[sameCharEndIndex + count]) {
                    count++
                } else {
                    break
                }
            }
            count--

            var l = count * 2 + sameCharCount
            if (currMaxLength < l) {
                answerStartIndex = sameCharStartIndex - count
                answerEndIndex = sameCharEndIndex + count
                currMaxLength = l
            }

            sameCharCount = 1

        }
        return s.substring(answerStartIndex, answerEndIndex + 1)
    }

    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
    找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
    返回容器可以储存的最大水量。
    题意看不懂具体看leetCode，有图。

     *
     * 最开始想的是找前N条中最大的那个值，记录起始点，然后前N+1条里面的最大值和前n条里面的最大值建立关联关系，这样，找到f(1)就找到f(2),直到f(n)，记录最大值就行了。结果f(n)和f(n-1)之间的关系没找对，错了两次，这个思路可能是行不通。
     * 参考的精选答案的思路，起止截点从两端往中间移动，因为面积取决于两者中的短板，所以移动长板的话，短板要么不变，要么变小，长度还减一，面积一定变小。每次都移动短的那个，向内靠，直到两指针相遇，记录最大值
     */
    fun maxArea(height: IntArray): Int {
        var startIndex = 0
        var endIndex = height.size - 1
        var answer = areaOfTwoIndex(height, startIndex, endIndex)
        while (startIndex < endIndex) {
            if (height[startIndex] > height[endIndex]) {
                endIndex--
            } else {
                startIndex++
            }
            answer = answer.coerceAtLeast(areaOfTwoIndex(height, startIndex, endIndex))
            Log.d(S.TAG, "answer: ${answer}, startIndex:$startIndex, endIndex:$endIndex")

        }
        return answer
    }


    private fun areaOfTwoIndex(height: IntArray, p1: Int, p2: Int): Int {
        return (p2 - p1) * height[p1].coerceAtMost(height[p2])
    }

    /**
     * 15. 三数之和
    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。

    思路： 没办法就只能暴力解，三重循环。时间复杂度O(N3)这种写出来也没鸟用...
    先排序，再用双指针，减少一重循环，时间复杂度O(N2)
    排序之后顺序由小到大，然后遍历，找跟第i个匹配的另外两个值。设置两个指针，在两端(i+1和N-1),两个指针处的值和n[i]求和 sum，
    sum > 0,右指针左移；sum < 0 左指针右移；
    sum == 0，就找到一组值，然后左指针右移，右指针左移，看还有没有别的匹配上的值，指针移动的时候要判断跟前一个数字一不一样，排除相同的答案。
    最后遍历完，找完答案
    注意点，遍历的时候判断i跟i-1的值一不一样，同样的数字不再重复求解
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val ans: MutableList<List<Int>> = ArrayList()
        val len: Int = nums.size
        if (nums == null || len < 3) return ans
        nums.sort() // 排序

        for (i in 0 until len) {
            if (nums[i] > 0) break // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] === nums[i - 1]) continue  // 去重
            var L = i + 1
            var R = len - 1
            while (L < R) {
                val sum = nums[i] + nums[L] + nums[R]
                if (sum == 0) {
                    ans.add(listOf(nums[i], nums[L], nums[R]))
                    while (L < R && nums[L] === nums[L + 1]) L++ // 去重
                    while (L < R && nums[R] === nums[R - 1]) R-- // 去重
                    L++
                    R--
                } else if (sum < 0) L++ else if (sum > 0) R--
            }
        }
        return ans
    }

    /**
     * 17. 电话号码的字母组合
     *
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
      给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     具体题意看leetcode，有图

     思路是拆分任务，递归，找“2345”对应字母的所有排列组合，就是找"2" 和 “345”里面所有的组合的组合，“345”的所有组合就是找3和“45“的所有组合的组合，”45“的所有组合就是找"4"和”5的所有组合“
     所以递归函数的返回值，是当前这组数字的所有字母组合，返回一个list<String>，当前这组数字的所有字母组合就是当前这组数字的头一个数字 和 剩下的数字的所有组合
     递归函数的结构大致就是：
    callSelf(digits):list<String>{
        restList = callSelf(digits.substring(1))
        var firstNum
        var list
        list 列出firstNum和restList的所有组合
        return list
    }

     */
    fun letterCombinations(digits: String): List<String> {
        val letterMap = arrayOf(
            "",  //0
            "",  //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs",  //7
            "tuv",  //8
            "wxyz" //9
        )
        if (digits.isEmpty()) {
            return ArrayList<String>()
        }

        return letterCombinations1(digits, letterMap)
    }

    private fun letterCombinations1(
        digits: String,
        letterMap: Array<String>,
    ): ArrayList<String> {

        var retList = ArrayList<String>()
        if (digits.isEmpty()) return retList

        if (digits.length == 1) {
            var str = letterMap[digits[0].toString().toInt()]
            for (i in str.indices) {
                retList.add(str[i].toString())
            }
            return retList
        }

        var list = letterCombinations1(digits.substring(1),letterMap)

        var firstNumber = digits[0].toString().toInt()
        var firstNumberChars = letterMap[firstNumber]

            for (element in firstNumberChars) {
                for (j in 0 until list.size) {
                    var sb = StringBuilder()
                    sb.append(element)
                    sb.append(list[j])
                    retList.add(sb.toString())

                    Log.d(S.TAG,"已添加 ${sb.toString()}")
                }
            }

        return retList
    }


    /**
     * 22. 括号生成
     *
     * f0 = 空
     * f1 = ()
     * f2 = (f1)f0, (f0)f1 就是 (()), ()()
     * f3 = (f2)f0,         (f1)f1,      (f0)f2
     *    ((())),(()())    (())()     ()(()),()()()
     *
     * fn = (fn-1)f0 ,(fn-2)f1, ... ,(f0)fn-1
     *根据最后这条方程，得出当值为n时，fn与后面f0到fn-1之间的关系，这里比较复杂，f2到fn-1的值都是一个列表，
     * fn的所有情况需要罗列当i从0到n-1时，括号里面的所有列表，括号外面的所有列表，排列组合出的所有情况
     * i从0到n-1是一重循环
     * 括号里面的所有列表是一重循环
     * 括号右边的所有列表是一重循环
     * 一共三重循环
     *
     */
    fun generateParenthesis(n: Int):List<String>? {
       return generate(n)
    }

    /**
     *
    执行n=3的时候，打印的日志如下：
    generate n=0, []
    generate n=1, [()]
    generate n=2, [()(), (())]
    generate n=3, [()()(), ()(()), (())(), (()()), ((()))]
    程序递归的过程中，会把n等于0，1，2的答案也全部计算出来。因为n=3的时候的值跟前面的各种情况是关联的，要一一罗列出来，
    没有前面的基础计算不出后面的值

     */
    var cache = arrayOfNulls<ArrayList<String>>(100)
    fun generate(n: Int): List<String>? {
        if (cache[n] != null) {
            return cache[n]
        }
        val ans = ArrayList<String>()
        if (n == 0) {
            ans.add("")
        } else {
            for (c in 0 until n) {
                for (left in generate(c)!!) {
                    for (right in generate(n - 1 - c)!!) {
                        ans.add("($left)$right")
                    }
                }
            }
        }
        cache[n] = ans
        Log.d(S.TAG,"generate n=$n, ${ans.toString()}")

        return ans
    }


    //重要总结：如果一道题，给你一个变量n，要你罗列出所有的情况，比如上面的17题和22题
    // 这种题型如果暴力去解决，循环的层数和n值是相关的，代码不好写。
    //解决这种题的思路一般是
    //先找f(n)和f(n-1)的关系，一般f(n-1)也是一个多种组合的列表,f(n)就是n这个变量和f(n-1)可以产生的排列组合的
    //所有情况，然后写循环列出所有的情况，比如键盘那道题，"234"就是"2"跟"34"的所有排列组合，"34"是一个字母排列的列表，
    // 2是一个字符排列的列表，两重for循环找出"2"跟"34"的所有组合，返回列表结果，找"34"的时候需要用递归方法递归到下一层。
    //其实相当于是，暴力解法的时候我们不知道循环写几层，因为n是变化的，于是我们用递归来代替循环，需要往下找几层就递归几次。
    //这种写法，递归一般都是在for循环的条件里面的
    //括号那道题稍微复杂一点，因为f(n)和f(n-1)的关系比键盘那道题难找，但是这种题型只要找到了f(n)和f(n-1)的关系，后面的
    //套路都是一样的，都是for循环遍历n与f(n-1)的排列组合，f(n-1)的值通过递归来获得。


    /**
     * 31. 下一个排列
     *
     * 这个题的意思大概就是给几个数字，它们随意拼成一个数n，用这个几个数拼出下一个数，它的大小比刚好比n大，但是比其他比n大的数都小
     * 比如给个123,比123大又比其他比123大的数都小，就是132
     * 123456,就是123465,其他不管是213456还是123645等等都比123465大。就是贴着边找到刚好比这个数大的数
     *
     *
     * 找规律容易想漏，最开始想的是给了一个数，从右往左找，比左边大就略过，比左边小就把这一位i与i-1的值互换。
     * 两个漏洞，
     * 漏洞1.不应该是i与i-1互换，而应该是i-1后面比i-1大的最小的那个数跟i-1互换，比如 123465，不是4跟6互换，而是4跟5互换
     * 漏洞2.互换之后并不是就结束了，还要将i-1后边的数字从小到大排序才行，比如123465，5跟4换了，是123564，但是答案应该是123546.
     * 最后还有找不到i比i-1大的位置，说明这个数字是倒序的，比如54321这种，倒叙一遍就行了。
     *
     * 最后代码就是落实上面的思路，感觉没右什么算法技巧在里面，就是考验思维的缜密。这题两个漏洞是看了精选答案之后才意识到的，面试要是现做这个题是做不过的。
     */
    fun nextPermutation(nums: IntArray): Unit {

        for (i in nums.size - 1 downTo  1){
            //从后往前找，找[i]比[i-1]大的位置
            if (nums[i] > nums[i-1]){
                //找i后面比[i]大的最小的数，跟i交换
                var minIndex = i
                for (j in i + 1 .. nums.size - 1){
                    if (nums[j] < nums[minIndex] && nums[j] > nums[i - 1]){
                        minIndex = j
                    }
                }
                var temp = nums[i - 1]
                nums[i - 1] =  nums[minIndex]
                nums[minIndex] = temp

                //将i-1位置之后的数从小到大排序
                for (p in i .. nums.size - 1){
                    for (q in p + 1 .. nums.size - 1){
                        if (nums[p] > nums[q]){
                            var temp = nums[p]
                            nums[p] =  nums[q]
                            nums[q] = temp
                        }
                    }
                }
                printArray(nums)
                return
            }
        }

        //如果没有找到[i]比[i-1]大的位置,说明这个序列时从大到小排列的，进行倒序
        for (k in 0 until  nums.size /2){
            var temp = nums[k]
            nums[k] =  nums[nums.size - k - 1]
            nums[nums.size - k - 1] = temp
        }
    }

    fun printArray(nums: IntArray){
        for (element in nums) {
            Log.d(S.TAG, "${element}")
        }
    }

    /**
     * 33. 搜索旋转排序数组
     *
     *整数数组 nums 按升序排列，数组中的值 互不相同 。
    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。

    示例 1：
    输入：nums = [4,5,6,7,0,1,2], target = 0
    输出：4

     这个题是在二分查找法上面的变换，把升序数组分成了两截：
    左半段
       /
      /
     /
           /
          /
         /
      右半段
     这样子纯靠middle的值和target比大小就没法知道该移动起点还是移动终点了。

     最开始想的时候，想到了关键点，就是这样旋转之后，右半段最大值也是比左半段小的。所以通过对nums[0]和target的值就知道答案在左半段还是右半段，同样也可以判断出middle是在左半段还是右半段。
     后面就弄晕了，没有一个清晰的思路。其实这里只要分情况处理一下就好了，如果middle 和target都在同一段，那就按照正常的二分查找来对比、移动start或者end就行了，
     特殊情况是，target在左半段，middle在右半段，这种就把end移动到middle - 1
     还有 target在右半段，middle在左半段，这种就把start移动到middle + 1
     就这四种情况，两种情况是middle和target都在同一段，常规处理，两种情况是middle和target分别在左右两段，分别判断应该移start还是移end

     这里要夯实一下二分查找的基本功，写的时候错了好几次。
     定律：
     1.偶数个的数组，middle命中 中间两个的偏左那一个。    [0,1,2,3]就命中 1；   [0,1]就命中 0
     2.start和end移动的时候，一定要middle + 1或者middle - 1,否则，当target在右边界的时候，可能会找不到，因为只剩两个的时候，middle永远命中左边那个。
     3.循环的条件是start <= end，一定要加等号，两个相等的时候，就是去找最后一个数的时候，去掉等号，最后一个数就会找漏
     4.按上面那样做，middle最后一定会命中target，除非没有值，就会start > end跳出循环
     *
     */
    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size  - 1
        while (start <= end){
            var middle = (start + end) / 2
            if (nums[middle] == target){
                return middle
            }

            if (target > nums[0]){
                //答案在左半段
                if (nums[middle] < nums[0]){
                    //如果middle在右半段，end
                    end = middle - 1
                }else {
                    //如果middle在左半段。正常二分法
                    if (nums[middle] <target){
                        start = middle + 1
                    }else{
                        end = middle - 1
                    }
                }


            }else if(target < nums[0]){
                //答案在右半段
                if (nums[middle] >= nums[0]){
                    //如果middle在左半段，start往右移动
                    start = middle + 1
                }else {
                    //如果middle在右半段。正常二分法
                    if (nums[middle] <target){
                        start = middle + 1
                    }else{
                        end = middle - 1
                    }
                }

            }else{
                return 0
            }
        }

        return -1
    }
}