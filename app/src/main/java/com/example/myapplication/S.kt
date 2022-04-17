package com.example.myapplication

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by zhangyu on 2022/4/6.
 */
class S {
    companion object {
        const val TAG = "Solution"
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        return if (null == list1 && null == list2) {
            null
        } else if (null == list1) {
            list2
        } else if (null == list2) {
            list1
        } else {
            //每个节点的next都等于 下一次比较的两个节点中较小的那个，依此递归
            // 最终返回的不是list的头节点就是list2的头节点
            if (list1.data < list2!!.data) {
                list1.next = mergeTwoLists(list1.next, list2)
                list1
            } else {
                list2.next = mergeTwoLists(list1, list2.next)
                list2
            }
        }
    }

    /**
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。

    由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么nums的前 k 个元素应该保存最终结果。

    将最终结果插入nums 的前 k 个位置后返回 k 。

    不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size <= 1) {
            return nums.size
        }
        var ret = 0
        for (i in nums.indices) {
            var j = i
            while (j < nums.size - 1 && nums[j] == nums[j + 1]) {
                j++
            }
            //从i到j都是一样的数字
            //从i+1到j都赋值为j+1位置的数字，即下一个比他们大的那个数
            for (k in i + 1..j) {
                if (j == nums.size - 1) {
                    break
                }
                nums[k] = nums[j + 1]
            }
            if (nums[i] == nums[nums.size - 1]) {
                ret = i + 1
                break
            }
        }
        return ret
    }

    /**
     * 快慢指针，(有动图，看官方的题解)
     * 假设数组 {nums}nums 的长度为 n。将快指针 {fast}fast 依次遍历从 1 到 n−1 的每个位置，对于每个位置，如果 {nums}[{fast}] \ne {nums}[{fast}-1]nums[fast]
    =nums[fast−1]，说明 {nums}[{fast}]nums[fast] 和之前的元素都不同，因此将 {nums}[{fast}]nums[fast] 的值复制到 {nums}[{slow}]nums[slow]，然后将 {slow}slow 的值加 11，即指向下一个位置。
    遍历结束之后，从 {nums}[0]nums[0] 到 {nums}[{slow}-1]nums[slow−1] 的每个元素都不相同且包含原数组中的每个不同的元素，因此新的长度即为 {slow}slow，返回 {slow}slow 即可。

     */
    fun removeDuplicates1(nums: IntArray): Int {
        if (nums.size <= 1) {
            return nums.size
        }
        var ret = 0
        //i是快指针，j是慢指针
        var j = 1
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i]
                j++
            }
            ret = j
        }
        return ret
    }

    /**
     * 移除元素
     *
     * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    fun removeElement(nums: IntArray, value: Int): Int {
        //用快慢指针，解法跟 升序数组 nums，原地删除重复出现的元素的解法一样
        var ret = 0
        var j = 0
        for (i in nums.indices) {
            if (nums[i] != value) {
                nums[j] = nums[i]
                j++
            }
            ret = j
        }
        return ret
    }

    /**
     * 28. 实现 strStr()
     *
    实现strStr()函数。
    给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。

    说明：
    当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。

     */
    fun strStr(haystack: String, needle: String): Int {
        if (null == needle) return 0
        var startIndex = 0
        while (true) {
            var k = 0
            for (i in startIndex..(startIndex + needle.length)) {
                if (i == haystack.length) {
                    return -1
                }
                if (haystack[i] == needle[k]) {
                    k++
                } else {
                    startIndex++
                    break
                }
                if (k == needle.length) {
                    return i - needle.length + 1
                }
            }

            if (startIndex == haystack.length - needle.length + 1) {
                break
            }
        }
        return -1
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

    请必须使用时间复杂度为 O(log n) 的算法。

    nums 为 无重复元素 的 升序 排列数组

     */
    fun searchInsert(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0
        if (target > nums[nums.size - 1]) return nums.size
        if (target < nums[0]) return 0

        var startIndex = 0
        var endIndex = nums.size
        while (true) {
            var middleIndex = (startIndex + endIndex) / 2

            if (target > nums[middleIndex] && target < nums[middleIndex + 1]) {
                return middleIndex + 1
            }
            if (target < nums[middleIndex] && target > nums[middleIndex - 1]) {
                return middleIndex
            }
            if (target == nums[middleIndex]) {
                return middleIndex
            }

            if (target > nums[middleIndex]) {
                startIndex = middleIndex
            }

            if (target < nums[middleIndex]) {
                endIndex = middleIndex
            }
        }
    }

    //官方推荐解法
    fun searchInsert1(nums: IntArray, target: Int): Int {
        val n = nums.size
        var left = 0
        var right = n - 1
        var ans = n
        while (left <= right) {
            val mid = (right - left shr 1) + left
            if (target <= nums[mid]) {
                ans = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return ans
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    例如，121 是回文，而 123 不是。
     */
    fun isPalindrome(x: Int): Boolean {
        var array = ArrayList<Int>()
        var x1 = x
        while (x1 > 0) {
            var r = x1 % 10
            array.add(r)
            x1 /= 10
        }

        var valueRevert = 0
        var d = 1
        for (i in array.size - 1 downTo 0) {
            valueRevert += array[i] * d
            d *= 10
        }

        return valueRevert == x
    }


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串""。

    示例 1：
    输入：strs = ["flower","flow","flight"]
    输出："fl"
    示例 2：
    输入：strs = ["dog","racecar","car"]
    输出：""
    解释：输入不存在公共前缀。
     */
    fun longestCommonPrefix(strs: Array<String>): String {

        if (strs.isEmpty()) return ""
        var commonPrefix = ""
        var currentCommonPrefix = strs[0]
        for (i in 1 until strs.size) {
            currentCommonPrefix = commonPreFix(currentCommonPrefix, strs[i])
            if ("" == currentCommonPrefix) {
                break
            }
        }

        return currentCommonPrefix
    }

    fun commonPreFix(s1: String, s2: String): String {
        var minLength = Math.min(s1.length, s2.length)
        var targetIndex = 0
        for (i in 0 until minLength) {
            if (s1[i] != s2[i]) {
                targetIndex = i
                break
            }
            if (i == s1.length - 1) {
                return s1
            }
            if (i == s2.length - 1) {
                return s2
            }
        }
        if (targetIndex >= 0) {
            return s1.substring(0, targetIndex)
        } else {
            return ""
        }
    }

    /**
     *  有效的括号
     *
    给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

    有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    示例 1：

    输入：s = "()"
    输出：true
    示例2：

    输入：s = "()[]{}"
    输出：true
    示例3：

    输入：s = "(]"
    输出：false
    示例4：

    输入：s = "([)]"
    输出：false
    示例5：

    输入：s = "{[]}"
    输出：true

     */
    fun isValid(s: String): Boolean {

        if (s.length % 2 == 1) return false
        var map = HashMap<Char, Char>()
        map['('] = ')'
        map['['] = ']'
        map['{'] = '}'
        //左括号 入栈，右括号依次与栈顶对比，能匹配上就继续往下遍历，匹配不上返回false
        var leftStack = Stack<Char>()
        for (i in s.indices) {
            var char = s[i]
            if (char === '(' || char === '[' || char === '{') {
                leftStack.push(char)
            } else {
                if (leftStack.empty()) {
                    return false
                }
                if (char === map[leftStack.pop()]) {
                    continue
                } else {
                    return false
                }
            }
        }

        //遍历完毕，最后栈空了，就说明全部匹配完毕，否则说明有左括号没匹配上，返回false
        return leftStack.empty()
    }

    class TreeNode(var value: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    /**
     * 94. 二叉树的中序遍历
     * 中序遍历，就是根节点在中间，顺序为左、中、右。只要一个节点有左节点，就先打印左节点，没有左节点或者打印完了左节点再打印中节点，然后如果有右节点在打印右节点
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        var list: ArrayList<Int> = ArrayList()
        inorderTraversal(root, list)
        return list
    }

    private fun inorderTraversal(root: TreeNode?, list: ArrayList<Int>) {
        root?.let {
            if (null != it.left) {
                inorderTraversal(it.left, list)
            }
            list.add(it.value)

            if (null != it.right) {
                inorderTraversal(it.right, list)
            }
        }

    }
    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组 是数组中的一个连续部分。
     */
    fun maxSubArray(nums: IntArray): Int {
        //先找以nums[0]结尾的子数组的最大值，子数组长度为1  最大值d[0] = nums[0]
        //如果d[i]是一个负数，则i之前的连续数组可以丢弃掉，从i+1开始为新的起点往后算
        //找以nums[1]结尾的子数组的最大值，子数组长度为2  最大值为d[0] + nums[1] 与 nums[1] 之中的最大值  记为d[1]
        //找以nums[2]结尾的子数组的最大值，子数组长度为3  最大值为d[1] + nums[2] 与 nums[2] 之中的最大值  记为d[2]
        //...
        //最后找以nums[nums.size - 1]结尾的子数组的最大值，子数组长度为n  最大值为d[n - 2] + nums[n - 1] 与 nums[n - 1 之中的最大值  记为d[n - 1]

        //至此就把以nums数组中每一个数结尾的子数组的最大值都找出来了
        //d[0]到d[n - 1]之中的最大值就是要找的最大值
        var d = arrayOfNulls<Int>(nums.size)//记录以每个数结尾的子数组的最大值
        d[0] = nums[0]
        var ret = d[0]!!
        for (i in 1 until nums.size) {
            d[i] = Math.max(d[i - 1]!! + nums[i], nums[i]!!)
            if (ret < d[i]!!) {
                ret = d[i]!!
            }
        }

        return ret
    }

    /**
     * 分治法，看 {@Link com.example.myapplication.Solution maxCrossingSum}方法
     */


    /**
     * 70. 爬楼梯
     *
     * 这个递归思路是对的，就是会运行超时。当n==45的时候，运行了40秒才出结果。思路对但是不能这么写。
     * n > 2的时候，有两种选择，否则，只有一种选择；两种选择下，分别执行走一步和走两步，剩下的阶梯数的下一步，继续判断，于是f(x) = f(x - 1) + f(x - 2),直到x小于等于1
     *
     *
     */
    fun climbStair1(n: Int): Int {
        if (n <= 1) return 1
        return climbStair1(n - 1) + climbStair1(n - 2)
    }

    /**
     * 递归不行，把f(x) = f(x - 1) + f(x - 2)倒过来看，先算f(0) f(1) 就有了f(2);有了f(1)和f(2),就有了f(3);依次滚动下去，就有了f(n)
     *
     *  x      y     ret
     * f(0) + f(1) = f(2)
     * f(1) + f(2) = f(3)
     * f(2) + f(3) = f(4)
     * ...
     * f(n - 2) + f(n - 1) = f(n)
     *
     * 滚动起来，从第一行到第二行, x = y; y = ret; ret = x + y
     * f(2)执行上面的代码1次；f(n) 执行上面的代码n - 1次；最后滚动出来的值就是返回值
     */
    fun climbStairs(n: Int): Int {
        if (n <= 1) return 1
        var ret = 0
        var x = 1
        var y = 1
        for (i in 1 until n){//f(n) 执行n - 1次滚动
            ret = x + y
            x = y
            y = ret
        }
        return ret
    }

}