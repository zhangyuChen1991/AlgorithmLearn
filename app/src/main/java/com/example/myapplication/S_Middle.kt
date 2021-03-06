package com.example.myapplication

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.utils.Traversal
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

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

    思路是拆分任务，递归，找“2345”对应字母的所有排列组合，就是找"2" 和 “345”里面所有的组合的组合，“345”的所有组合就是找"3"和“45“的所有组合的组合，”45“的所有组合就是找"4"和”5的所有组合“
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

        var list = letterCombinations1(digits.substring(1), letterMap)

        var firstNumber = digits[0].toString().toInt()
        var firstNumberChars = letterMap[firstNumber]

        for (element in firstNumberChars) {
            for (j in 0 until list.size) {
                var sb = StringBuilder()
                sb.append(element)
                sb.append(list[j])
                retList.add(sb.toString())

                Log.d(S.TAG, "已添加 ${sb.toString()}")
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
    fun generateParenthesis(n: Int): List<String>? {
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
        Log.d(S.TAG, "generate n=$n, ${ans.toString()}")

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

        for (i in nums.size - 1 downTo 1) {
            //从后往前找，找[i]比[i-1]大的位置
            if (nums[i] > nums[i - 1]) {
                //找i后面比[i]大的最小的数，跟i交换
                var minIndex = i
                for (j in i + 1..nums.size - 1) {
                    if (nums[j] < nums[minIndex] && nums[j] > nums[i - 1]) {
                        minIndex = j
                    }
                }
                var temp = nums[i - 1]
                nums[i - 1] = nums[minIndex]
                nums[minIndex] = temp

                //将i-1位置之后的数从小到大排序
                for (p in i..nums.size - 1) {
                    for (q in p + 1..nums.size - 1) {
                        if (nums[p] > nums[q]) {
                            var temp = nums[p]
                            nums[p] = nums[q]
                            nums[q] = temp
                        }
                    }
                }
                printArray(nums)
                return
            }
        }

        //如果没有找到[i]比[i-1]大的位置,说明这个序列时从大到小排列的，进行倒序
        for (k in 0 until nums.size / 2) {
            var temp = nums[k]
            nums[k] = nums[nums.size - k - 1]
            nums[nums.size - k - 1] = temp
        }
    }

    fun printArray(nums: IntArray) {
        var sb = java.lang.StringBuilder()
        for (element in nums) {
            sb.append(", $element ")
        }
        Log.d(S.TAG, sb.toString())
    }

    fun printMatric(matric: Array<IntArray>) {
        for (i in matric.indices) {
            printArray(matric[i])
        }
    }

    fun printList(nums: ArrayList<Int>) {
        var sb = java.lang.StringBuilder()
        for (element in nums) {
            sb.append(", $element ")
        }
        Log.d(S.TAG, sb.toString())
    }

    fun arrayStr(nums: IntArray): String {
        var sb = java.lang.StringBuilder()
        for (i in 0 until nums.size) {
            if (i == 0) {
                sb.append("[")
            }
            sb.append("${nums[i]}")
            if (i < nums.size - 1) {
                sb.append(", ")
            }
            if (i == nums.size - 1) {
                sb.append("]")
            }

        }
        return sb.toString()
    }

    fun arrayStr(nums: Array<Int?>): String {
        var sb = java.lang.StringBuilder()
        for (i in 0 until nums.size) {
            if (i == 0) {
                sb.append("[")
            }
            sb.append("${nums[i]}")
            if (i < nums.size - 1) {
                sb.append(", ")
            }
            if (i == nums.size - 1) {
                sb.append("]")
            }

        }
        return sb.toString()
    }

    fun arrayArrayStr(nums: Array<IntArray>): String {
        var sb = java.lang.StringBuilder()
        for (i in 0 until nums.size) {
            if (i == 0) {
                sb.append("[")
            }
            sb.append("${arrayStr(nums[i])}")
            if (i < nums.size - 1) {
                sb.append(", ")
            }
            if (i == nums.size - 1) {
                sb.append("]")
            }

        }
        return sb.toString()
    }

    fun listStr(nums: List<Any>): String {
        var sb = java.lang.StringBuilder()
        for (i in 0 until nums.size) {
            if (i == 0) {
                sb.append("[")
            }
            sb.append("${nums[i]}")
            if (i < nums.size - 1) {
                sb.append(", ")
            }
            if (i == nums.size - 1) {
                sb.append("]")
            }

        }
        return sb.toString()
    }

    fun listListStr(nums: List<List<Any>>): String {
        var sb = java.lang.StringBuilder()
        for (i in 0 until nums.size) {
            if (i == 0) {
                sb.append("[")
            }
            sb.append("${listStr(nums[i])}")
            if (i < nums.size - 1) {
                sb.append(", ")
            }
            if (i == nums.size - 1) {
                sb.append("]")
            }

        }
        return sb.toString()
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
        var end = nums.size - 1
        while (start <= end) {
            var middle = (start + end) / 2
            if (nums[middle] == target) {
                return middle
            }

            if (target > nums[0]) {
                //答案在左半段
                if (nums[middle] < nums[0]) {
                    //如果middle在右半段，end
                    end = middle - 1
                } else {
                    //如果middle在左半段。正常二分法
                    if (nums[middle] < target) {
                        start = middle + 1
                    } else {
                        end = middle - 1
                    }
                }


            } else if (target < nums[0]) {
                //答案在右半段
                if (nums[middle] >= nums[0]) {
                    //如果middle在左半段，start往右移动
                    start = middle + 1
                } else {
                    //如果middle在右半段。正常二分法
                    if (nums[middle] < target) {
                        start = middle + 1
                    } else {
                        end = middle - 1
                    }
                }

            } else {
                return 0
            }
        }

        return -1
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回[-1, -1]。

    进阶：
    你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？

    示例 1：
    输入：nums = [5,7,7,8,8,10], target = 8
    输出：[3,4]

     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            var middle = (start + end) / 2
            if (nums[middle] == target) {
                var preIndex = middle - 1
                var afterIndex = middle + 1

                while (preIndex >= 0 && nums[preIndex] == target) {
                    preIndex--
                }
                while (afterIndex < nums.size && nums[afterIndex] == target) {
                    afterIndex++
                }

                preIndex++
                afterIndex--

                return intArrayOf(preIndex, afterIndex)
            }

            if (nums[middle] > target) {
                end = middle - 1
            }
            if (nums[middle] < target) {
                start = middle + 1
            }
        }

        return intArrayOf(-1, -1)
    }

    /**
     * 39. 组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。

    示例 1：
    输入：candidates = [2,3,6,7], target = 7
    输出：[[2,2,3],[7]]
    解释：
    2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
    7 也是一个候选， 7 = 7 。
    仅有这两种组合。

    示例 2：
    输入: candidates = [2,3,5], target = 8
    输出: [[2,2,2,2],[2,3,3],[3,5]]
     */
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {

        val len: Int = candidates.size
        val res: List<List<Int>> = ArrayList()
        if (len == 0) {
            return res
        }

        val path: ArrayList<Int> = ArrayList()
        dfs(candidates, 0, len, target, path, res as ArrayList<ArrayList<Int>>)
//        dfs1(candidates, 0, target, path, res as ArrayList<ArrayList<Int>>)
        return res

    }

    /**
     * 精选答案的算法，for循环里面递归，直接看这个不太好理解
     *
     * 直接搜有道云笔记：【39. 组合总和 回溯，循环里面调用递归，总结】，有图文详细的解释和回溯算法的总结.
     */
    private fun dfs(
        candidates: IntArray,
        begin: Int,
        len: Int,
        target: Int,
        path: ArrayList<Int>,
        res: ArrayList<ArrayList<Int>>,
    ) {
        Log.d(S.TAG, "dfs： begin：${begin}, len ${len}, target ${target}, path ${listStr(path)}")

        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            Log.i(S.TAG, "无答案： ${listStr(path)}")
            return
        }
        if (target == 0) {
            Log.w(S.TAG, "答案： ${listStr(path)}")
            res.add(ArrayList(path))
            return
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (i in begin until len) {
            path.add(candidates[i])

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res)

            // 状态重置
            path.remove(path[path.size - 1])
        }
    }

    private fun dfs1(
        candidates: IntArray,
        start: Int,
        target: Int,
        path: ArrayList<Int>,
        res: ArrayList<ArrayList<Int>>,
    ) {
        Log.d(S.TAG, "dfs： start：${start}, target ${target}, path ${listStr(path)}")

        if (start >= candidates.size) {
            Log.i(S.TAG, "到末尾了，无答案： ${listStr(path)}")
            return
        }
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            Log.i(S.TAG, "target < 0, 无答案： ${listStr(path)}")
            return
        }
        if (target == 0) {
            Log.w(S.TAG, "答案： ${listStr(path)}")
            res.add(ArrayList(path))
            return
        }

        var nextTarget = target - candidates[start]
        path.add(candidates[start])
        dfs1(candidates, start, nextTarget, path, res)
        path.removeLast()

        var nextStart = start + 1
        dfs1(candidates, nextStart, target, path, res)
    }

    /**
     * 46. 全排列
     */
    fun permute(nums: IntArray): List<List<Int>> {
        var ret = ArrayList<ArrayList<Int>>()
        if (nums.isEmpty()) {
            return ret
        }

        //数组转成链表，好操作，因为后续答案list每加一个节点，就要从数据list里移除一个节点
        var linkedList = LinkedList<Int>()
        for (j in nums.indices) {
            linkedList.add(nums[j])
        }

        for (i in linkedList.indices) {
            var list = ArrayList<Int>()
            permute(linkedList, linkedList[i], list, ret)
        }
        return ret
    }

    fun permute(
        nums: LinkedList<Int>,
        currentElement: Int,
        list: ArrayList<Int>,
        ret: ArrayList<ArrayList<Int>>,
    ) {
        list.add(currentElement)
        var newNums = LinkedList(nums)
        newNums.remove(currentElement)

//        Log.d(S.TAG,"permute： currentElement：${currentElement}, newNums ${listStr(newNums)}, path ${listStr(list)}")

        if (newNums.isEmpty()) {
            //探寻到头了
            ret.add(ArrayList(list))
//            Log.w(S.TAG, "permute： 答案${listStr(list)}")
            return
        }

        for (i in newNums.indices) {
            permute(newNums, newNums[i], list, ret)
            list.removeAt(list.size - 1)
        }
    }

    /**
     * 48. 旋转图像
     *
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

    1 2 3
    4 5 6
    7 8 9
    转换成：
    7 4 1
    8 5 2
    9 6 3

    思路，就找规律，(x,y)与旋转之后的点(x1,y1), x1 = size - y - 1; y1 = x; 同时左上角的点换到右上角，右上角的点就会换到右下角，然后到左下角，左上角，四个点同时轮换
    用一个map存起来哪些点换过了，然后下次遍历到直接跳过。
    按这个规律两重遍历就行了。
    打败了7%的提交.....
     *
     */
    fun rotate(matrix: Array<IntArray>): Unit {
//        printMatric(matrix)
        if (matrix.isEmpty() || matrix.size == 1) return
        var size = matrix.size
        var rotateRecord = HashMap<String, Boolean>()
        for (x in matrix.indices) {
            var array = matrix[x]
            for (y in array.indices) {
                var key = "$x-$y"
                if (rotateRecord.containsKey(key) && rotateRecord[key]!!) {
                    continue
                }

                var x1 = size - y - 1
                var y1 = x

                var x2 = size - y1 - 1
                var y2 = x1

                var x3 = size - y2 - 1
                var y3 = x2


                Log.d(S.TAG, "$x-$y , $x1-$y1 , $x2-$y2 , $x3-$y3 轮换")

                var temp = -1

                temp = matrix[x][y]
                matrix[x][y] = matrix[x1][y1]
                matrix[x1][y1] = matrix[x2][y2]
                matrix[x2][y2] = matrix[x3][y3]
                matrix[x3][y3] = temp

                rotateRecord.put("$x-$y", true)
                rotateRecord.put("$x1-$y1", true)
                rotateRecord.put("$x2-$y2", true)
                rotateRecord.put("$x3-$y3", true)
            }
        }
//        Log.d(S.TAG, "-----------------------------")
//        printMatric(matrix)

    }

    /**
     * 参考精选答案，在上面的基础上，去掉了map记录哪些点已经旋转过了，只旋转左上角1/4大小的方块区域就行了
     * 优化之后，打败了12%的提交.....
     */
    fun rotate1(matrix: Array<IntArray>): Unit {
        printMatric(matrix)
        if (matrix.isEmpty() || matrix.size == 1) return
        var size = matrix.size
        for (x in 0 until size / 2) {
            for (y in 0 until (size + 1) / 2) {

                var x1 = size - y - 1
                var y1 = x

                var x2 = size - y1 - 1
                var y2 = x1

                var x3 = size - y2 - 1
                var y3 = x2


                Log.d(S.TAG, "$x-$y , $x1-$y1 , $x2-$y2 , $x3-$y3 轮换")

                var temp = matrix[x][y]
                matrix[x][y] = matrix[x1][y1]
                matrix[x1][y1] = matrix[x2][y2]
                matrix[x2][y2] = matrix[x3][y3]
                matrix[x3][y3] = temp

            }
        }
        Log.d(S.TAG, "-----------------------------")
        printMatric(matrix)

    }

    /**
     * 49. 字母异位词分组
     *
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

    示例 1:
    输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

    下面的解法思路是：从第一个开始遍历，找到这个字符串字母的全排列(回溯+剪枝)，然后往后对比，看后面的字母是不是在这个排列里面。是就归拢到一个list里，不是就跳过。每一个被归拢过的字符串都用一个数组在对应下标做上标记，避免重复对比。
    结果应该是对的，测试用例跑了106个都通过了，第107个用例输入了一个上千个字符串的数组，内存超出限制了。。但是这个解法应该是可以用的，在不限制内存的情况下。

    精选答案的做法是把每个字符串按照字母排序，只要是字母一样而顺序不同的字符串，排完序就都一样了，排序完再归类就行了。之前做了三个用回溯找全排列类型的题，导致思维惯性把这个也用找全排列去做，绕路了。怎么就没想到先排序...

    总结：先排序是数组一类的题的一个辅助解决的思路，不止一次被用到了..
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        var ret = ArrayList<ArrayList<String>>()
        if (strs.isEmpty()) return ret

        var record = arrayOfNulls<Int>(strs.size)
        for (i in strs.indices) {
            if (record[i] == 1) {//已经被添加过了
                continue
            }
            var itemOfAnswer = ArrayList<String>()
            itemOfAnswer.add(strs[i])
            var currStrs = ArrayList<String>()
            var stringBuilder = StringBuilder()
            strsOfStr(strs[i], currStrs, stringBuilder)
            Log.d(S.TAG, "${strs[i]} 的全排列： ${listStr(currStrs)}")

            for (j in i + 1 until strs.size) {
                if (record[j] == 1) {//已经被添加过了
                    continue
                }

                for (k in currStrs.indices) {
                    if (currStrs[k] == strs[j]) {
                        itemOfAnswer.add(strs[j])
                        record[j] = 1
                    }
                }
            }
            Log.d(S.TAG, "添加一组答案： ${listStr(itemOfAnswer)}")
            ret.add(itemOfAnswer)
        }
        Log.d(S.TAG, "最终答案： ${listListStr(ret)}")

        return ret
    }


    fun strsOfStr(str: String, strList: ArrayList<String>, sb: StringBuilder) {
        if (str.isEmpty()) {
            strList.add(sb.toString())
            return
        }
        for (i in str.indices) {
//            var firstIndex = str.indexOf(str[i])
//            Log.d(S.TAG, "$str.indexOf(${str[i]})： $firstIndex")
//                        Log.d(S.TAG, "$str  ${str[i]}  $i")

            if (str.indexOf(str[i]) == i) {//重复排列，剪枝
                sb.append(str[i])
                var sb1 = StringBuilder()
                var str1 = sb1.append(str.substring(0, i)).append(str.substring(i + 1, str.length))
                    .toString()
                strsOfStr(str1, strList, sb)
                sb.deleteCharAt(sb.length - 1)
            }
        }
    }

    /**
     * 49. 字母异位词分组
     * 精选答案
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun groupAnagrams1(strs: Array<String?>?): List<List<String?>?>? {
        return ArrayList(Arrays.stream(strs)
            .collect(Collectors.groupingBy { str ->
                // 返回 str 排序后的结果。
                // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
                val array: CharArray = str!!.toCharArray()
                Arrays.sort(array)
                String(array)
            }).values)
    }

    /**
     * 49. 字母异位词分组
     * 精选答案的翻译版本
     */
    fun groupAnagrams2(strs: Array<String>): List<List<String>> {
        var ret = ArrayList<ArrayList<String>>()
        if (strs.isEmpty()) return ret

        var sortStrs = arrayOfNulls<String>(strs.size)
        for (i in strs.indices) {
            val array = strs[i].toCharArray()
            Arrays.sort(array)
            val sortStr = String(array)
            sortStrs[i] = sortStr
        }

        var record = arrayOfNulls<Int>(strs.size)
        for (i in strs.indices) {
            if (record[i] == 1) {//已经被添加过了
                continue
            }
            var itemOfAnswer = ArrayList<String>()
            itemOfAnswer.add(strs[i])

            for (j in i + 1 until strs.size) {
                if (record[j] == 1) {//已经被添加过了
                    continue
                }
                if (sortStrs[i] == sortStrs[j]) {
                    itemOfAnswer.add(strs[j])
                    record[j] = 1
                }
            }
            //Log.d(S.TAG, "添加一组答案： ${listStr(itemOfAnswer)}")
            ret.add(itemOfAnswer)
        }
        //Log.d(S.TAG, "最终答案： ${listListStr(ret)}")

        return ret
    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标。

    示例1：
    输入：nums = [2,3,1,1,4]
    输出：true
    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。


     *  按照自己的思路，又开始去递归了，自己看代码，应该解法也行得通，就是内存又超出了。每一个起点startIndex，走1步，走2步，直到走nums[startIndex]步，都去递归一次，看最后有没有能到终点的，有就true，没有就false。会递归很多次。
     *  形成这种f(n)和f(n-1)找关系，然后去递归的惯性思维了...
     *
     *  精选答案的思路是，从第一个开始算，算它目前能走到的最大位置maxP，再往后遍历，遍历到最大位置，看前maxP个元素里能走的最远的是哪个元素，然后再更新maxP,如果更新到最后能达到或者超过数组终点，说明可以走到终点。
     *  因为每一步可以随意走1、2、nums[i]步，所以maxP左边的点全都是可以到达的。思路巧妙，解法写出来很简洁
     *
     *
     */
    fun canJump(nums: IntArray): Boolean {
        if (nums.isEmpty()) {
            return false
        }
        printArray(nums)
        var maxPosition = 0
        for (i in 0..maxPosition) {
            //第i个元素能够跳到的最远距离
            var temp = nums[i] + i
            //能到达的最远距离
            if (temp > maxPosition) {
                maxPosition = temp
            }
            if (maxPosition >= nums.size - 1) return true
        }
        return false
    }

    /**
     * 55. 跳跃游戏 自己的递归解法
     * 内存超出限制了..
     */
    fun canJump1(nums: IntArray): Boolean {
        printArray(nums)
        if (nums.isEmpty()) return false
        var resultList = ArrayList<Boolean>()
        canJump1(0, nums, resultList)
        return resultList.contains(true)
    }

    fun canJump1(startIndex: Int, nums: IntArray, result: ArrayList<Boolean>) {
        Log.d(S.TAG, "startIndex： ${startIndex}")

        if (startIndex >= nums.size
            || nums[startIndex] == 0 && startIndex != nums.size - 1
        ) {
            Log.w(S.TAG, "startIndex： ${startIndex}, 结果，false")
            if (!result.contains(false)) {
                result.add(false)
            }
            return
        }

        if (startIndex == nums.size - 1) {
            Log.w(S.TAG, "startIndex： ${startIndex},  currNum: ${nums[startIndex]}，  结果，true")
            result.add(true)
            return
        }


        var currNum = nums[startIndex]
        var maxStep = currNum.coerceAtMost(nums.size - startIndex - 1)
        for (i in 1..maxStep) {
            var nextStartIndex = startIndex + i
            canJump1(nextStartIndex, nums, result)
        }
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

    示例 1：
    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出：[[1,6],[8,10],[15,18]]
    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

    思路：没有啥特别的技巧，就是按题意去合并，踩了两个坑，还是做出来了。首先是排序，保证开头数比较小的子数组排在前面，然后比较subArray[i][1] 和 subArray[i + 1][1] 的值，去合并。
    踩的第一个坑是，subArray[i + 1][1]的值比subArray[i][1]的值小，前面的数组整个包住了后一个数组，结尾的时候还用后一个数组的结尾值，结果就错了
    第二个坑是subArray[i]和subArray[i+1]合并之后，不能继续往后看subArray[i+2]跟subArray[i+3]的对比，有可能subArray[i]和subArray[i+1]合并之后的数组跟subArray[i+2]还可以继续合并，也就是连续合并的情况。所以subArray[i]和subArray[i+1]合并之后
    要把subArray[i+1]的值更新成新数组的值，然后继续跟subArray[i+2]对比，依此往后。这是想错了的一个点。
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {

        Log.d(S.TAG, "入参: intervals： ${arrayArrayStr(intervals)}")

        if (intervals.isEmpty()) {
            return intervals
        }
        var retList = ArrayList<IntArray>()

        //先按子数组中的第一个数字排序
        var sortIntervals = quickSort(intervals)
        Log.d(S.TAG, "排序后: intervals： ${arrayArrayStr(sortIntervals)}")

        var index = 0
        while (index <= sortIntervals.size - 1) {
            var currArray = sortIntervals[index]
            if (index < sortIntervals.size - 1) {
                var nextArray = sortIntervals[index + 1]
                if (currArray[1] >= nextArray[0]) {
                    var newArray = intArrayOf(currArray[0], nextArray[1])
                    if (currArray[1] > nextArray[1]) {
                        newArray = intArrayOf(currArray[0], currArray[1])
                    }
                    sortIntervals[index + 1] = newArray
                } else {
                    retList.add(currArray)
                }
            } else {//已经到达最后一个
                retList.add(currArray)
            }
            index++
        }
        Log.d(S.TAG, "返回值: ret： ${arrayArrayStr(retList.toTypedArray())}")


        return retList.toTypedArray()
    }

    fun quickSort(array: Array<IntArray>): Array<IntArray> {
        if (array.size < 2) return array

        val pivot = array[array.size / 2][0]

        val less = array.filter { it[0] < pivot }
        val equal = array.filter { it[0] == pivot }
        val greater = array.filter { it[0] > pivot }

        return quickSort(less.toTypedArray()) + equal + quickSort(greater.toTypedArray())
    }

    /**
     * 62. 不同路径
     *
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    问总共有多少条不同的路径？

    思路：规律就是f(m,n) = f(m - 1,n) + f(m, n-1)
    跟之前上楼梯一次能走一步或者两步，一共有多少中走法一样。用递归的话一定是栈溢出。那个题是用的滚动数组，这个题是从一个格子开始推，(i,1)和(1,i)都是单条格子，只有一条路。(i,2)和(2,i)有i种走法。
    然后知道(0,1) (1,0)就知道(1,1), 知道(1,1) (2,0) 就知道(2,1)，依次类推，从起始点开始算，把每个格子的步数都算出来，最后算出(m,n)就是答案
     */
    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 1 || n == 1) {
            return 1
        }
        if (m == 2) {
            return n
        }
        if (n == 2) {
            return m
        }

        var d: Array<IntArray> = Array(m) { IntArray(n) }
        for (i in 0 until m) {
            d[i][0] = 1
            d[i][1] = i + 1
        }
        for (i in 0 until n) {
            d[0][i] = 1
            d[1][i] = i + 1
        }

        for (i in 2 until m) {
            for (j in 2 until n) {
                d[i][j] = d[i - 1][j] + d[i][j - 1]
                Log.d(S.TAG, "i: $i, j: $j, ret: ${d[i - 1][j - 1]}")

            }
        }
        Log.d(S.TAG, "m: $m, n: $n, ret: ${d[m - 1][n - 1]}")

        return d[m - 1][n - 1]

//        var ret = uniquePaths(m - 1, n) + uniquePaths(m, n - 1)
//        return ret
    }


//    fun uniquePaths(m: Int, n: Int): Int {
//        val dp = Array(m) { IntArray(n) }
//        for (i in 0 until n) dp[0][i] = 1
//        for (i in 0 until m) dp[i][0] = 1
//        for (i in 1 until m) {
//            for (j in 1 until n) {
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
//            }
//        }
//        return dp[m - 1][n - 1]
//    }

    /**
     * 36. 有效的数独
     *
     * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
    数字1-9在每一行只能出现一次。
    数字1-9在每一列只能出现一次。
    数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）


    注意：
    一个有效的数独（部分已被填充）不一定是可解的。
    只需要根据以上规则，验证已经填入的数字是否有效即可。
    空白格用'.'表示。

    示例：
    输入：board =
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    输出：true


    思路：最开始想复杂了，以为要解出每个矩阵的答案才能知道是不是有效数独矩阵。其实题里面说明了，一个有效的数独（部分已被填充）不一定是可解的。
    所以，解题就是在现有出现的数字中找，这个数字在该行，该列，该box里面有没有已有出现重复的数字，如果有，则说明这个数独题有误。
    题解就是遍历每个数字，看该数字在所在行、列、box里面有没有一样的数字。
    本来行、列、box，可以用三个Map来记录值，但是由于题目给定了是9*9的矩阵，所以可以用三个定长数组来记录.
    下面配合注释看，应该讲明白了.
     */
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        //记录第j行出现过的数字，出现过5就在col[i][5]计一个数，下次再出现5，找到col[i][5]发现已经有这个数，表明这个数重复出现了
        var col = Array(9) { IntArray(10) }
        //记录第i列出现过的数字
        var row = Array(9) { IntArray(10) }
        //记录第x个box出现过的数字 box的下标和i行j列的对应关系是 boxIndex = (i / 3) * 3 + j / 3
        var box = Array(9) { IntArray(10) }

        for (i in board.indices) {
            var colArray = board[i]
            for (j in colArray.indices) {
                var c = board[i][j]
                if (c === '.') {
                    continue
                }
                var num = c - '0'
                //第i列出现过这个数字了，重复出现，返回false
                if (row[i][num] > 0) {
                    return false
                }

                //第j行出现过这个数字了，重复出现，返回false
                if (col[j][num] > 0) {
                    return false
                }
                var boxIndex = (i / 3) * 3 + j / 3
                //第boxIndex个box出现过这个数字了，重复出现，返回false
                if (box[boxIndex][num] > 0) {
                    return false
                }

                //出现过的数字，在该行、列、box里记下值
                row[i][num] = 1
                col[j][num] = 1
                box[boxIndex][num] = 1
            }
        }

        return true
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。

    输入：grid = [
    [1,3,1],
    [1,5,1],
    [4,2,1]]
    输出：7
    解释：因为路径 1→3→1→1→1 的总和最小。

    思路：拿到题的思路是用递归，把每一种情况都遍历出来，然后用一个list记录每种情况步数总和，最后在list里面找最小的值。也猜到这种解法又会栈溢出，结果果然，跑到20/61个用例的时候内存超出限制了。想根据62题那样把递归转成for循环，想了一阵子又没有想到思路。
    看精选答案果然是把递归转成for循环来处理了。
    最开始想的是，从左上角走到右下角，不走完每一条完整的路径是不知道这条路径的值是多少的，也无法预知未来走哪一步是最小路径，所以要递归遍历完所有情况。而且按这个思路，无法转递归为for循环。
    但是倒过来看就可以想通。右下角开始算，它的左边格子(left)的总步数和上边格子(top)的总步数里面选小的那条，就是最小路径，left和top也是同样的选法，最后选到左上角d[0][0]的格子右边d[0][1]和下面d[1][0]，
    这两个格子的路径只有一条，就是从左上角走过来，把左上角格子的数字和它们相加，记在它们格子里，就是它们当前的最小路径的步数。然后知道了d[0][1]和d[1][0],d[1][1]的最小步数就是d[1][1]的值和d[0][1]、d[1][0]之中总步数最小的那个值相加，
    然后依次类推，就变成跟62题一样的两重for循环了。

    下面minPathSum1是自己递归的解法，内存超限
    minPathSum是参考精选答案的思路，转成for循环的写法。
     */
    fun minPathSum1(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var totalSteps = ArrayList<Int>()
        minPathSum1(grid, 0, 0, 0, totalSteps)
        var minStep = Int.MAX_VALUE
        for (i in totalSteps.indices) {
            if (totalSteps[i] < minStep) {
                minStep = totalSteps[i]
            }
        }
        return minStep
    }

    private fun minPathSum1(
        grid: Array<IntArray>,
        currI: Int,
        currJ: Int,
        currTotalStep: Int,
        totalSteps: ArrayList<Int>,
    ) {
        Log.d(S.TAG, "i: $currI, j: $currJ, currTotalStep: ${currTotalStep}")

        var totalStep = currTotalStep + grid[currI][currJ]
        if (currI == grid.size - 1 && currJ == grid[0].size - 1) {
            totalSteps.add(totalStep)
            Log.d(S.TAG, "到达终点，添加totalStep:$totalStep")
            printList(totalSteps)
            return
        }

        if (currI < grid.size - 1) {
            minPathSum1(grid, currI + 1, currJ, totalStep, totalSteps)
        }
        if (currJ < grid[0].size - 1) {
            minPathSum1(grid, currI, currJ + 1, totalStep, totalSteps)
        }

    }

    fun minPathSum(grid: Array<IntArray>): Int {
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i == 0 && j == 0) {
                    continue
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j]
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j]
                } else {
                    grid[i][j] = grid[i - 1][j].coerceAtMost(grid[i][j - 1]) + grid[i][j]
//                    grid[i][j] = Math.min(grid[i - 1][j],(grid[i][j - 1]))
                }
            }
        }
        return grid[grid.size - 1][grid[0].size - 1]
    }


    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

    我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
    必须在不使用库的sort函数的情况下解决这个问题。

    本来想在一次循环里面把0挪到前面，把2挪到后面，中间剩下1。结果逻辑越写越复杂，最后没写出来。
    简洁但是慢一点的做法，先把0挪到前面，用一次循环；再把1挪到前面，用一次循环；然后结束。

    下面这个代码跑到82/87用例的时候，输入[1,0]，说我输出也是[1,0]，实际跑下来明明是输出的[0,1]。已经是第二次出现这种情况了，不纠结这个问题，掌握思路了就好。
     */
    fun sortColors(nums: IntArray): Unit {
        printArray(nums)
        if (nums.size < 2) return
        var currRedIndex = 0
        var currWhiteIndex = 0
        var currIndex = 0


        while (currIndex < nums.size) {
            if (nums[currIndex] == 0 && currRedIndex < nums.size && currIndex > currRedIndex) {

                var temp = nums[currIndex]
                nums[currIndex] = nums[currRedIndex]
                nums[currRedIndex] = temp

                currRedIndex++

            } else {
                currIndex++
            }
        }

        currIndex = currRedIndex
        currWhiteIndex = currRedIndex

        while (currIndex < nums.size) {
            if (nums[currIndex] == 1 && currWhiteIndex < nums.size && currIndex > currWhiteIndex) {

                var temp = nums[currIndex]
                nums[currIndex] = nums[currWhiteIndex]
                nums[currWhiteIndex] = temp
                currWhiteIndex++

            } else {
                currIndex++
            }
        }

        printArray(nums)
    }

    fun sortColors1(nums: IntArray) {
        val n = nums.size
        var ptr = 0
        for (i in 0 until n) {
            if (nums[i] == 0) {
                val temp = nums[i]
                nums[i] = nums[ptr]
                nums[ptr] = temp
                ++ptr
            }
        }
        for (i in ptr until n) {
            if (nums[i] == 1) {
                val temp = nums[i]
                nums[i] = nums[ptr]
                nums[ptr] = temp
                ++ptr
            }
        }
    }

    /**
     * 78. 子集
     * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    示例 1：
    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    思路：拆分任务，[1,2,3]的全子集，就是1和[2,3]的全子集拼起来，加上[2,3]的全子集，[2,3]的全子集就是2和[3]的全子集拼起来，加上[3]的全子集
    [3]的全子集是[3]和[]
    于是[2,3]的全子集就是[2,3],[2]再加上[3]和[]，即：[2,3],[2],[3],[]
    于是[1,2,3]的全子集 就是1跟上面的子集都拼一遍，再加上上面的子集 构成的总集合
    所以递归函数的雏形就出来了
    就是array里面取第一个作为num，和剩下的array求全集合。递归返回的条件：array为空，返回[[]]
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        return subsets(0, nums)
    }

    private fun subsets(
        startIndex: Int,
        currNums: IntArray,
    ): ArrayList<ArrayList<Int>> {
        if (startIndex == currNums.size) {
            var ret = ArrayList<ArrayList<Int>>()
            ret.add(ArrayList<Int>())
            Log.w(S.TAG, "添加答案：[]")

            return ret
        }

        var firstNum = currNums[startIndex]
        var nextStartIndex = startIndex + 1
        var restLists = subsets(nextStartIndex, currNums)

        var lists = ArrayList<ArrayList<Int>>()
        for (i in restLists.indices) {
            var item = restLists[i]
            var newItem = ArrayList<Int>()
            newItem.addAll(item)
            newItem.add(firstNum)

            lists.add(newItem)
            Log.w(S.TAG, "添加答案：${listStr(newItem)}")
        }
        lists.addAll(restLists)

        Log.d(S.TAG, "当前答案：${listListStr(lists)}")
        return lists
    }


    /**
     * 79. 单词搜索
    给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    思路：先遍历二维数组，找第一个字母匹配的位置，找到之后找这个字母的上下左右位置是否有下一个字母，这个逻辑在递归里面做，依次递归，在四周找下一个，找到的话再下一个，直到找完整个单词，返回true；
    如果找不完，返回false；
    注意的点是，要标记每轮查找中已经用过的位置，不能重复使用，避免走回头路，导致错误结果。
    精选答案也是这个思路，但是代码要简短一些。下面这个只超过了5%的用户...
     */
    fun exist(board: Array<CharArray>, word: String): Boolean {

        var usedPosition = HashMap<String, Boolean>()
        for (i in board.indices) {
            var array = board[i]
            for (j in array.indices) {
                var c = board[i][j]
                if (c == word[0]) {
                    usedPosition["$i-$j"] = true
                    var ret = hasNextChar(i, j, word, 1, board, usedPosition)
                    Log.d(S.TAG, "i: $i, j: $j ret: $ret")
                    if (ret) {
                        return true
                    } else {
                        usedPosition["$i-$j"] = false
                        continue
                    }
                }
            }
        }
        return false
    }

    private fun hasNextChar(
        i: Int,
        j: Int,
        word: String,
        currIndex: Int,
        board: Array<CharArray>,
        usedPosition: HashMap<String, Boolean>,
    ): Boolean {

        Log.d(S.TAG, "hasNextChar：i: $i, j: $j, word: $word, currIndex: $currIndex,")

        if (currIndex == word.length) {
            return true
        }
        var top: Char? =
            if (j - 1 < 0 || (usedPosition.containsKey("$i-${j - 1}") && usedPosition["$i-${j - 1}"]!!)) {
                null
            } else {
                board[i][j - 1]

            }
        var left: Char? =
            if (i - 1 < 0 || (usedPosition.containsKey("${i - 1}-${j}") && usedPosition["${i - 1}-${j}"]!!)) {
                null
            } else {
                board[i - 1][j]
            }
        var right: Char? =
            if (i + 1 >= board.size || (usedPosition.containsKey("${i + 1}-${j}") && usedPosition["${i + 1}-${j}"]!!)) {
                null
            } else {
                board[i + 1][j]
            }
        var bottom: Char? =
            if (j + 1 >= board[0].size || (usedPosition.containsKey("${i}-${j + 1}") && usedPosition["${i}-${j + 1}"]!!)) {
                null
            } else {
                board[i][j + 1]
            }

        var nextIndex = currIndex + 1
        top?.let {
            if (it == word[currIndex]) {
                usedPosition["$i-${j - 1}"] = true
                var ret = hasNextChar(i, j - 1, word, nextIndex, board, usedPosition)
                if (ret) {
                    return true
                } else {
                    usedPosition["$i-${j - 1}"] = false
                }
            }
        }
        left?.let {
            if (it == word[currIndex]) {
                usedPosition["${i - 1}-${j}"] = true
                var ret = hasNextChar(i - 1, j, word, nextIndex, board, usedPosition)
                if (ret) {
                    return true
                } else {
                    usedPosition["${i - 1}-${j}"] = false
                }
            }
        }
        right?.let {
            if (it == word[currIndex]) {
                usedPosition["${i + 1}-${j}"] = true
                var ret = hasNextChar(i + 1, j, word, nextIndex, board, usedPosition)
                if (ret) {
                    return true
                } else {
                    usedPosition["${i + 1}-${j}"] = false
                }
            }
        }
        bottom?.let {
            if (it == word[currIndex]) {
                usedPosition["$i-${j + 1}"] = true
                var ret = hasNextChar(i, j + 1, word, nextIndex, board, usedPosition)
                if (ret) {
                    return true
                } else {
                    usedPosition["$i-${j + 1}"] = false
                }
            }
        }
        return false
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     *
     * 思路：找1到n能构成的二叉树总数，就是找以1为根节点、以2为跟节点、以n为跟节点的二叉树的类型总和，以i为根节点的总类型数，等于1到n-1能构成的树作为左子数和以i+1到n能构成的数作为右子树的乘积。这就可以抽出一个递归函数，求从startNum到endNum能构成的数的总类型，
     * 递归找出左子树总类型，再找出右子树总类型，相乘，得出结果。递归返回条件，当startNum == endNum时，返回类型总数为1。
     */
    fun numTrees(n: Int): Int {
        return numTrees(1, n)
    }

    private fun numTrees(startNum: Int, endNum: Int): Int {
        if (startNum == endNum) {
            Log.d(S.TAG, "numTrees：startNum: $startNum, endNum: $endNum, totalCount: 1")
            return 1
        }
        var totalCount = 0
        for (i in startNum..endNum) {
            var lCount = 1
            var rCount = 1
            var currCount = 0
            if (i > startNum) {
                lCount = numTrees(startNum, i - 1)
            }
            if (i < endNum) {
                rCount = numTrees(i + 1, endNum)
            }
            currCount = lCount * rCount
            totalCount += currCount
        }
        Log.d(S.TAG, "numTrees：startNum: $startNum, endNum: $endNum, totalCount: $totalCount")
        return totalCount
    }

    /**
     * 98. 验证二叉搜索树
     *给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

    有效 二叉搜索树定义如下：
    节点的左子树只包含 小于 当前节点的数。
    节点的右子树只包含 大于 当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。

     * 最开始容易想错一个点，以为每个节点的左节点小于自己，右节点大于自己就可以了，写一个简单的回调函数挨个判断就行了。但是有个误区，二叉搜索树的左子树每个节点数字都小于自己，右子树每个节点的数据都大于自己，所以不光是判断
     * 自己和左右节点的大小，每个节点在往下遍历的时候都有一个范围，越往左，最小值越小，节点超过了最小值就不符合条件，越往右遍历，最大值越大，节点小于最大值了也不符合条件。
     * 所以递归函数的参数有两个值来表示当前节点的取值范围，节点值在范围内，就符合条件。范围值随着往左和往右遍历逐步变化，依次遍历到底。
     */
    fun isValidBST(root: Traversal.TreeNode?): Boolean {
        if (null == root) return true
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    fun isValidBST(root: Traversal.TreeNode?, min: Long, max: Long): Boolean {
        if (null == root) return true

        if (root.`val` <= min || root.`val` >= max)
            return false

        var lRet = isValidBST(root.left, min, root.`val`.toLong())
        var rRet = isValidBST(root.right, root.`val`.toLong(), max)

        return lRet && rRet
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * 输入：root = [3,9,20,null,null,15,7]
    输出：[[3],[9,20],[15,7]]

    思路：一层一层的遍历，用一个currList存当前这一层，从root开始，再用一个nextList存下一层;下一层(nextList)的值就是currList的所有左右子节点的集合，遍历之后nextList就变成currList，一轮一轮的遍历，直到currList为空。
     */
    fun levelOrder(root: Traversal.TreeNode?): List<List<Int>> {
        var ret = ArrayList<ArrayList<Int>>()
        if (null == root) return ret
        var nextDeepthList = ArrayList<Traversal.TreeNode>()
        var currDeepthList = ArrayList<Traversal.TreeNode>()
        currDeepthList.add(root)

        while (!currDeepthList.isEmpty()) {
            var currValues = ArrayList<Int>()

            for (i in currDeepthList.indices) {
                currValues.add(currDeepthList[i].`val`)

                currDeepthList[i].left?.let {
                    nextDeepthList.add(it)
                }
                currDeepthList[i].right?.let {
                    nextDeepthList.add(it)
                }
            }
            ret.add(currValues)

            currDeepthList.clear()
            currDeepthList.addAll(nextDeepthList)
            nextDeepthList.clear()
        }

        return ret
    }

    /**
     * 114. 二叉树展开为链表
     *
    给你二叉树的根结点 root ，请你将它展开为一个单链表：
    展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
    展开后的单链表应该与二叉树 先序遍历 顺序相同。

    思路: 就是先序遍历，遍历的节点都存在list里面，然后list[i]指向list[i+1]就行了
     */
    fun flatten(root: Traversal.TreeNode?): Unit {

        var nodeList = ArrayList<Traversal.TreeNode>()
        flatten(root, nodeList)
        for (i in 0 until nodeList.size - 1) {
            nodeList[i].left = null
            nodeList[i].right = nodeList[i + 1]
        }
        Log.w(S.TAG, "ret: ${listStr(nodeList)}")

    }

    fun flatten(root: Traversal.TreeNode?, nodeList: ArrayList<Traversal.TreeNode>): Unit {

        if (root == null) return
        nodeList.add(root)
        root.left?.let {
            flatten(it, nodeList)
        }
        root.right?.let {
            flatten(it, nodeList)
        }

    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。


    思路，关键点是：preorder的第一个元素就是这棵树的根节点，在inorder找到这个根节点，左边就是左子树，右边就是右子树，左右子树再对应到preorder相应的元素，里面左子树的第一个又是左子树的根节点，右子树的第一个又是右子树的根节点，
    这样形成一个递归处理的逻辑，每次都取到当前的根节点，分出下一层的左右子树，继续递归.

    具体图文看 有道笔记[从前序与中序遍历序列构造二叉树]

     */
    fun buildTree(preorder: IntArray, inorder: IntArray): Traversal.TreeNode? {

        if (preorder.isEmpty()) return null

        var root = Traversal.TreeNode(preorder[0])

        var inOrderRootIndex = inorder.indexOf(root.`val`)

        var nextLeftInArray: IntArray? = null
        var nextRightInArray: IntArray? = null

        var nextLeftPreArray: IntArray? = null
        var nextRightPreArray: IntArray? = null

        if (inOrderRootIndex > 0) {
            nextLeftPreArray =
                preorder.copyOfRange(1, inOrderRootIndex + 1)//preorder里下一层的左子树对应的元素位置
            nextLeftInArray = inorder.copyOfRange(0, inOrderRootIndex)
        }
        if (inOrderRootIndex < preorder.size) {
            nextRightPreArray =
                preorder.copyOfRange(inOrderRootIndex + 1, preorder.size)//preorder里下一层的右子树对应的元素位置
            nextRightInArray = inorder.copyOfRange(inOrderRootIndex + 1, inorder.size)
        }

        nextLeftPreArray?.let {
            root.left = buildTree(it, nextLeftInArray!!)
        }

        nextRightPreArray?.let {
            root.right = buildTree(it, nextRightInArray!!)
        }

        return root
    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 思路：
     * 先转换一下，问题就是要从数组中取出若干个数，让它的值等于数组数字总和的1/2，数组数字总和如果是奇数，可以直接返回false；
     * 这里是典型的背包问题处理方式，从前i个数字中取若干个，能否等于目标数target，有两种选择，1.不选第i个数，问题转换成前i-1个数里能否凑出target；2.选中第i个数，问题转换成前i-1个数里能否凑出target-nums[i];
     * 如果用一个二维数组来记录每个配对的结果，d[i][j]的含义就是从i个数字中取若干个，能否等于目标数j
     * 得出的方程是
     * d[i][j] = d[i-1][j] or d[i-1][j-nums[i]]  (j-nums[i]要注意越界)
     *
     * 试过了用递归去做会超市(LeetCode的第一次提交是递归的代码)
     * 这个跟爬楼梯那个类似f(n) = f(n-1) + f(n-2)，就从f(1),f(2)开始算，循环往上加，从最基础的已知的加到目标值
     * 在这里,从前1个数中凑出目标值j，很容易初始化赋值，那就是，当nums[0]就是j的时候，值为true，其他都是false
     * d[0][nums[0]]是true(注意越界)，其他都是false
     * d[0][j]这一列赋值完成之后，后边的d[i][j],d[2][j]都能通过上面的公式算出结果了
     *
     * 最后返回d[nums.len][target]的值就行了(就是二维数组最右下角的值)
     */
    fun canPartition(nums: IntArray): Boolean {
        var sum = 0
        nums.sort()
        for (i in nums.indices) {
            sum += nums[i]
        }

        if (sum % 2 != 0) return false

        var target = sum / 2

        var dp = Array(nums.size) { BooleanArray(target + 1) }
        if (nums[0] < dp[0].size) {//初始化第一列的值
            dp[0][nums[0]] = true
        }

        for (i in 1 until dp.size){
            var targets = dp[i]
            for (j in targets.indices){

                if (j - nums[i] == 0){//nums[i]刚好就是目标值，那直接满足条件了
                    dp[i][j] = true
                }else if (j - nums[i] > 0){
                    dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]]
                }else{
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }
        return dp[nums.size - 1][target]
    }

}