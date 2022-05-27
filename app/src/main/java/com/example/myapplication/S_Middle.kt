package com.example.myapplication

import android.text.TextUtils

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
}