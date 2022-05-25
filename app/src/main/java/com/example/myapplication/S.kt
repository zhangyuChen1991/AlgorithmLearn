package com.example.myapplication

import android.util.Log
import java.util.*

/**
 * Created by zhangyu on 2022/4/6.
 */
class S {
    companion object {
        const val TAG = "Solution"
    }

    fun mergeTwoLists1(list1: ListNode?, list2: ListNode?): ListNode? {
        return if (null == list1 && null == list2) {
            null
        } else if (null == list1) {
            list2
        } else if (null == list2) {
            list1
        } else {
            //每个节点的next都等于 下一次比较的两个节点中较小的那个，依此递归
            // 最终返回的不是list的头节点就是list2的头节点
            if (list1.`val` < list2!!.`val`) {
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

    class TreeNode(var `val`: Int) {
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
            list.add(it.`val`)

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
        for (i in 1 until n) {//f(n) 执行n - 1次滚动
            ret = x + y
            x = y
            y = ret
        }
        return ret
    }

    /**
     * 21.合并两个有序链表
     * 递归
    两节点对比，【返回较小的节点】，较小的节点指向下一次两个对比的节点中【较小的那个】(较小的这个即是递归函数的返回值)
     */
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (null == list1 && null == list2) return null
        if (null == list1) return list2
        if (null == list2) return list1
        if (list1.`val` < list2.`val`) {
            list1.next = mergeTwoLists(list2, list1.next)
            return list1
        } else {
            list2.next = mergeTwoLists(list1, list2.next)
            return list2
        }
    }

    /**
     * 58. 最后一个单词的长度
     */
    fun lengthOfLastWord(s: String): Int {
        var trimStr = s.trim()
        var indexOfLastSpace = trimStr.lastIndexOf(" ")
        return trimStr.length - indexOfLastSpace - 1
    }

    /**
     * 66. 加一
     *
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
    最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    fun plusOne(digits: IntArray): IntArray {
        //所有的数字都是9，最后返回扩容后的数组，数组长度加1
        var allIsNine = true
        for (i in digits.indices) {
            if (digits[i] != 9) {
                allIsNine = false
                break
            }
        }
        if (allIsNine) {
            var retArray = IntArray(digits.size + 1)
            retArray[0] = 1
            return retArray
        }

        //并非所有的数字都是9，返回的数组位数不变
        var currentIndex = digits.size - 1
        var currentNum = digits[currentIndex]
        while (currentNum == 9) {
            digits[currentIndex] = 0
            currentIndex--
            currentNum = digits[currentIndex]
        }
        digits[currentIndex] = currentNum + 1
        return digits
    }

    /**
     * 67. 二进制求和
     *
     * 思路就是模拟二进制竖式相加在草稿纸上计算的过程
     * 从末尾开始每一位相加，a + b + 进位，注意头部不足的位数补0.
     * 相加结果为0，记录此位为0，进位为0；相加结果为1，记录此位为1，进位为0；结果为2、3依此类推。每位结果都由StringBuffer记录下来(sb.append(result))
     * 最后StringBuffer记录的结果反转一下，就是最后加出来的最终结果。
     *
     * 注意点，计算的时候下标要判断准，犯了一个错，补0的时候补到了尾部而不是头部。从0开始遍历，index是length - i- 1，这样才是尾部对齐。
     * 从max开始遍历到0，这样容易算晕，还容易搞成是头部对齐来计算，结果是错的
     */
    fun addBinary(a: String, b: String): String {

        var sb = StringBuffer()
        var upper = 0
        var maxLength = a.length.coerceAtLeast(b.length)
        for (i in 0 until maxLength) {
            var resultNum = 0

            var aValue = 0
            var aIndex = a.length - i - 1;
            aValue = if (aIndex < 0) {
                0
            } else {
                a[aIndex] - '0'
            }

            var bValue = 0
            var bIndex = b.length - i - 1;
            bValue = if (bIndex < 0) {
                0
            } else {
                b[bIndex] - '0'
            }

            resultNum = aValue + bValue + upper
            when (resultNum) {
                0 -> {
                    upper = 0
                    sb.append('0')
                }
                1 -> {
                    upper = 0
                    sb.append('1')
                }
                2 -> {
                    upper = 1
                    sb.append('0')
                }
                3 -> {
                    upper = 1
                    sb.append('1')
                }
            }
        }

        if (upper == 1) {
            sb.append('1')//如果最一位也进位了，再补一个1
        }

        return sb.toString().reversed()
    }


    /**
     * 101.对称二叉树
     *
     * 对比两个节点的值是否相等，再对比左节点的左节点与右节点的右节点、左节点的右节点与右节点的左节点值是否相同(对着图看)，递归对比
     */
    fun isSymmetric(root: TreeNode?): Boolean {
        return isSymmetric(root?.left, root?.right)
    }

    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (null == left && null == right) return true
        if (null == left) return false
        if (null == right) return false

        if (left.`val` != right.`val`) return false
        else {
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
        }
    }


    /**
     * 104. 二叉树的最大深度
     *
     * 给定一个二叉树，找出其最大深度。
    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
    说明: 叶子节点是指没有子节点的节点。
     */
    fun maxDepth(root: TreeNode?): Int {
        if (null == root) return 0
        return maxDepth(root, 1)
    }

    fun maxDepth(treeNode: TreeNode?, currentDepth: Int): Int {
        if (null == treeNode) return currentDepth

        if (null != treeNode?.left && null != treeNode?.right) {//左节点和右节点都不为空，返回两者中深度较大的一个
            return Math.max(maxDepth(treeNode?.left, currentDepth + 1),
                maxDepth(treeNode?.right, currentDepth + 1))
        } else if (null != treeNode?.left) {//左节点不为空，右节点为空，左节点所在的深度
            return maxDepth(treeNode?.left, currentDepth + 1)
        } else if (null != treeNode?.right) {//右节点不为空，左节点为空，右节点所在的深度
            return maxDepth(treeNode?.right, currentDepth + 1)
        } else {//左右节点都为空，本节点就是当前分支的最底部
            return currentDepth
        }
    }

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    说明：
    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？


    使用异或运算。异或运算时，将数字转换为二进制数字做位运算，每一位上相同为0，不同为1,就像下面的竖式
    1111
    1011
    ------
    0100

    异或运算满足三个定律：
    任何数和0异或，结果为原来的数；
    自身和自身异或，结果为0；
    异或运算满足交换律和结合律:  4 xor 1 xor 2 xor 1 xor 2 = 4 xor (1 xor 1) xor (2 xor 2) = 4
    0            0
    所以把数组中的数挨个做一次异或运算，就把单独的那个数找出来了

     */
    fun singleNumber(nums: IntArray): Int {
        var single = 0
        for (i in nums.indices) {
            Log.d(TAG, "single: $single, nums: ${nums[i]}")
            single = single xor nums[i]
            Log.d(TAG, "异或计算结果，single: $single")
        }

        return single
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
    你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。


    暴力解法就是两次循环，找到每两个元素之间的差值，返回其中最大的那个差值。
    但是其实不用所有的都找完，最大利润肯定是后边价格的减去前面的发现的已知最小价格

    想象自己在画一条股票曲线，从第一天开始画，如果是上升段，那么目前的最大利润就是现在的价格减去当前最小的价格；
    如果是下降段，那么判断，如果产生了更小的最低价格，后续更大的利润肯定是减去这个更低的价格来产生的，所以，更新最小价格，继续往后画，
    如果发现画到哪一天产生的更大的利润，就更新这个值。
    所以只需要一次遍历，记录两个值就可以了，一个值是到目前为止的最小价格，往后看，如果产生更大的额利润一定是在这个价格的基础上；另一个值是到目前为止的最大利润。
     */
    fun maxProfit(prices: IntArray): Int {
        //遍历数组，记录当前的价格最小值，如果有更小的，就更新这个值，因为往后看 如果有更大的利润，肯定是在减去更小的价格的时候产生的
        //同时记录最大利润，如果当前的价格减去之前最小的价格利润更大了，就更新利润
        var currentMinPrice = Int.MAX_VALUE
        var currentMaxProfit = 0
        for (i in prices.indices) {
            var price = prices[i]
            if (price < currentMinPrice) {
                currentMinPrice = price
            } else {
                if (price - currentMinPrice > currentMaxProfit) {
                    currentMaxProfit = price - currentMinPrice
                }
            }
        }
        return currentMaxProfit
    }

    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 常规解法，遍历链表，map存遍历过的节点，判断当前节点的next节点是否在map中，是 则存在环
     */
    fun hasCycle(head: ListNode?): Boolean {
        if ((head?.next == null)) return false
        var map = HashMap<ListNode, Int>()
        var currentNode = head
        while (currentNode?.next != null) {
            map[currentNode!!] = 1
            currentNode = currentNode?.next
            if (map.containsKey(currentNode)) {
                return true
            }
        }

        return false
    }

    /**
     * 141. 环形链表
     * 快慢指针 ，快指针一次走两步，慢指针一次走一步，如果存在环，快指针会被慢指针追上，快慢指针相等 则说明存在环形
     * 疑问：这里快指针追上慢指针一定是两个值一样吗，快指针会不会总是直接跳过慢指针，两者永远不相等?这个能追上的理论基础是什么?
     * 自己试了一下，不管环上的节点是奇数个还是偶数个，确实最终两者能相遇。但是写不出数学公式来给这个判断提供理论基础
     */
    fun hasCycle1(head: ListNode?): Boolean {
        if ((head?.next == null)) return false
        var fastNode = head.next
        var slowNode = head
        while (fastNode?.next != null) {
            if (fastNode == slowNode) return true
            fastNode = fastNode.next?.next
            slowNode = slowNode?.next
        }

        return false
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

    解题思路关键点是 目标数字出现的次数大于n/2
    普通解法 用一个hashmap存次数就行了
    但是利用上面那个关键点，有两个思路。1是进行排序，因为它次数大于n/2，排序之后中间的那个数一定是目标数字，但是快排的时间复杂度也是O(n*logn)
    最优解，摩尔投票
    初始候选人为nums[0] count = 1
    当投到相同的数时 count+1，投到不同的数时，count-1，count为0时更换候选人并重置count = 1.最后留下了的候选人就是目标数，而且它的count一定时大于1的，
    因为它出现的次数比其他数加起来都多
     */
    fun majorityElement(nums: IntArray): Int {
        //摩尔投票解法
        var ret = nums[0]
        var count = 1
        for (i in nums.indices) {
            var b = nums[i]
            if (b == ret) {
                count++
            } else {
                count--
                if (count == 0) {
                    ret = b
                    count = 1
                }
            }
        }
        return ret
    }

    /**
     * 160. 相交链表
     *
     *
    给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。

    题目数据 保证 整个链式结构中不存在环。

    解法：
    普通解法就是遍历两个链表，用一个hashMap存其中一个链表遍历过的节点，另一个链表遍历每个节点的时候都去检查map里有没有这个节点，有就找到了。时间复杂度O(N)；空间复杂度O(N);
    降低空间复杂度的办法是：抓住一个关键点，非相交部分链表A的长度a + 相交部分链表长度c + 非相交部分链表B的长度b = 非相交部分链表B的长度b+ 相交部分链表长度c + 非相交部分链表A的长度a
    如下图：
    链表A从a1开始遍历，遍历到c2，然后挪到b1开始继续往下遍历，走到c1时，走过的长度为a+c+b;
    链表B从b1开始遍历，遍历到c2，然后挪到a1开始继续往下遍历，走到c1时，走过的长度为b+c+a;
    a+c+b和b+c+a 这两个长度是相等的，所以如果存证相交点，两个链表按照这样的遍历顺序，走同样多的步数之后，一定会同时走到相交点。所以只要按照这个顺序去遍历，对比当前两个点是否一样，就可以找到相交的点，遍历步数是a+c+b。
    然后处理不存在的情况，当遍历到队尾时，记一个轮数，当第二次遍历到队尾都还没有找到相同点，说明这两个链表不存在相交点，返回null。
    再处理两种特殊情况，一个是有链表为null时，直接返回null；一个是两个链表头节点就相同时，直接返回头节点，不需要再遍历;


    a1->a2->a3
    ->c1->c2(相交公共部分)
    b1->b2
     */
    fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        if (null == headA || null == headB) return null
        if (headA === headB) return headA
        var nodeA = headA?.next
        var nodeB = headB?.next
        var round = 0
        while (nodeA !== nodeB) {
            if (null == nodeA) {
                if (round > 0) return null//跑了两圈了也没找到相交的节点，判断不存在相交节点
                round++
                nodeA = headB
            } else {
                nodeA = nodeA!!.next
            }

            if (null == nodeB) {
                nodeB = headA
            } else {
                nodeB = nodeB?.next
            }
        }
        return nodeA
    }

    /**
     * 206. 反转链表
     *
     * 过渡用的节点初始化不需要new一个值，直接为null作为反转后的尾节点，再往前挪.
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (null == head) return null
        if (null == head.next) return head
        var ret: ListNode? = null
        var currNode = head
        while (null != currNode) {
            var next = currNode.next
            currNode.next = ret
            ret = currNode
            currNode = next
        }
        return ret
    }

    /**
     * 206. 反转链表
     * 递归解法
     * 就是传入两个相邻节点，让后一节点指向前一节点，再传入后一对相邻节点，递归调用，直到末尾
     */
    fun reverseList1(head: ListNode?): ListNode? {
        if (null == head) return null
        if (null == head.next) return head
        return reverseList1_2(null, head)
    }

    fun reverseList1_2(currNode: ListNode?, nextNode: ListNode?): ListNode? {
        if (null == nextNode) return currNode
        var next = nextNode.next
        nextNode.next = currNode
        return reverseList1_2(nextNode, next)
    }

    /**
     * 226. 翻转二叉树
     * 从上到下依次调换左右节点就行了
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if (null == root) return null
        var temp = root?.left
        root?.left = root?.right
        root?.right = temp

        invertTree(root?.left)
        invertTree(root?.right)

        return root
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    请注意 ，必须在不复制数组的情况下原地对数组进行操作。

    常规做法：循环遍历，遇到0就往后挪，挪到最后，然后再循环，遇到0再挪，直到遍历完成
    加快速度，减少遍历次数，思路：只遍历一次，遇到0就一起往后挪，相当于是批量处理了。比常规做法循环次数要少很多
     */
    fun moveZeroes(nums: IntArray): Unit {
        for (i in nums.indices) {
            var countOf0 = 0//直到当前发现的0的个数
            var j = i
            while (j < nums.size && nums[j] == 0) {
                countOf0++
                j++
                //上面遍历完之后，从i到j都是连续的0
            }
            if (j == nums.size) return

            //把连续的0整体往后挪
            for (k in i until i + countOf0) {
                if (k + countOf0 < nums.size) {
                    var temp = nums[k]
                    nums[k] = nums[k + countOf0]
                    nums[k + countOf0] = temp
                }
            }
        }
    }

    /**
     * 338. 比特位计数
     * 给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     *
     *  示例：
     *  输入：n = 2
    输出：[0,1,1]
    解释：
    0 --> 0
    1 --> 1
    2 --> 10

    思路：要想快，就不能每一个都去算，后面的数的答案跟前面的数的答案有关联，可以根据前面数的答案直接得出后面的数的答案，不用每个都去算就快了
    两条规律：
    1.奇数的二进制数末尾是1，而比他小1的偶数末尾是0，这个偶数加1就是这个奇数，只有末尾从0变成了1，其他的位置数字是不变的，所以奇数的1个数比比他小1的偶数多1
    2.偶数的末尾是0，偶数除以2，就是右移一位，相当于把末尾的0去掉了，所以偶数n的1的个数跟n/2的个数是一样的
    所以所有的数都可以根据前面的数得到值，不用再一一计算了
     */
    fun countBits(n: Int): IntArray {
        var ret = IntArray(n + 1)
        for (i in 0..n) {
            if (i % 2 == 0) {
                if (i == 0) {
                    ret[i] = 0
                } else {
                    ret[i] = ret[i / 2]
                }
            } else {
                ret[i] = ret[i - 1] + 1
            }
        }
        return ret
    }

    /**
     * 计算一个数的二进制形式里面有多少个1
     * 最朴素的方法，把这个数的末尾一位与1做与运算，检查是否为1，然后右移一位，继续检查，直到为0
     * 比如1101
     *       1
     * --------
     *       1
     * 右移一位... 接下来三个与运算的结果分别是0，1，1。总共3个1
     *
     */
    fun countOnes(x: Int): Int {
        var x = x
        var ones = 0
        while (x > 0) {
            if (x and 1 == 1) {
                ones++
            }
            x = x shr 1//右移动一位
        }
        return ones
    }

    /**
     * 448. 找到所有数组中消失的数字
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

    示例：
    给 [1,2,1,2,5,6]
    返回[3,4]
    因为本来该有 1 2 3 4 5 6，只出现了1 2 5 6

    常规思路，额外开一个List，长度是n，把1到n依次放进去，然后遍历数字，发现一个就从List里面去掉一个，最后剩下的就是结果。时间复杂的O(N),空间复杂的O(N)

    要降低空间复杂度，就不能使用list，观察规律，题里面的关键信息是，数字是从1到n范围内的，所以可以把数字-1之后当作下标来处理，取到对应的数字，就处理对应下标的数据。
    处理完之后，如果这个下标的数字被处理过了，就说明这个存在这个下标对应的数字 num <-> index + 1,没被处理过的下标对应的数字就是不存在的数字

     */
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        var list = ArrayList<Int>()
        var size = nums.size
        for (i in nums.indices) {
            var index = -1
            if (nums[i] > size) {
                //这个数被处理过了，先还原原数据，再计算下标
                var origin = nums[i] - size
                index = origin - 1
            } else {
                //这个数还没被处理过，直接计算下标
                index = nums[i] - 1
            }
            if (nums[index] <= size) {
                //下标对应的数字如果没被处理过，才给它加值
                nums[index] += size
            }
        }
        for (i in nums.indices) {
            //被处理过的数都大于size，如果小于size，说明这个数没被处理过，其对应的下标就是缺失的数字
            if (nums[i] <= size) {
                list.add(i + 1)
            }
        }
        return list
    }

    /**
     * 461. 汉明距离
     *
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。

    给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
    示例 1：

    输入：x = 1, y = 4
    输出：2
    解释：
    1   (0 0 0 1)
    4   (0 1 0 0)
    ↑   ↑
    上面的箭头指出了对应二进制位不同的位置。

     思路：跟计算二进制数中1的个数一样，把这个数和1做与运算，判断出末尾这一位的数字，再右移
     两个数比较，就同时做对比，再右移，直到两个数都变成0，统计不同的个数
     */
    fun hammingDistance(x: Int, y: Int): Int {
        var count = 0
        var x = x
        var y = y
        while (x > 0 || y > 0){
            if (x and 1 != y and 1){
                count++
            }
            x = x shr 1
            y = y shr 1
        }
        return count
    }

    /**
     * 543. 二叉树的直径
     *
     * 找任意两个子节点之间最长路径中的最大值(具体看原题，配着图看)
     *
     * 思路，官方解题思路是，任务拆分：
     * 1.找当前根节点的直径，就是左节点的最大深度，加上右节点的最大深度（因为深度是节点层数，直径是节点之间的路径相加，左深度+右深度刚好是当前节点的直径）
     * 2.找当前节点的深度，就是左节点的最大深度和右节点的最大深度中的最大值，再加1(左右节点到自己的距离)
     * 点2可以写一个递归，求当前节点的深度,伪代码如下：
     * deep(root){
     *   L = deep(root.left)
     *   R = deep(root.right)
     *   return max(L,R) + 1
     * }
     * 在这个递归中，其实当前节点的直径也可以求出来，就是 L + R
     * 所以在这个递归过程中，可以顺便把每个节点的直径都找出来，然后记录下最大的值，就求出答案了，伪代码如下
     *
     * var ans = 0
     * deep(root){
     *   L = deep(root.left)
     *   R = deep(root.right)
     *   currNodeAns = L + R
     *   ans = max(ans,currNodeAns)
     *   return max(L,R) + 1
     * }
     *
     * 最后，这个ans就是二叉树的直径
     */
    var answer543 = 0//第543题的答案
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        deep(root)
        return answer543
    }

    /**
     * 求一个节点的深度
     */
    fun deep(node: TreeNode?) : Int{
        if (null == node) return 0
        var l =  deep(node.left)
        var r =  deep(node.right)
        var currNodeAnswer = l + r
        answer543 = answer543.coerceAtLeast(currNodeAnswer)
        return l.coerceAtLeast(r) + 1
    }

    /**
     * 617. 合并二叉树
     *
     * 给你两棵二叉树： root1 和 root2 。
        想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
        返回合并后的二叉树。

        具体题意上leetCode看，有图。

        常规思路，递归合并，合并root1和root2，把root2的值合并到root1上，然后合并root1.left,root2.left; root1.right,root2.right,注意判空，
        重点注意当root1左右子节点为空而root2左右字节点不为空时，要给root1的左右节点new一个值，让它可以继续往下递归合并，不然就断了
     */
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        var r1 = root1
        var r2 = root2
        if (null == r1 && null == r2) return null
        if (null == r1 && null != r2){
            r1 = TreeNode(r2.`val`)
        }else{
            r2?.let {
                r1!!.`val` = r1.`val` + it.`val`
            }
        }

        //当root1左右子节点为空而root2左右字节点不为空时，要给root1的左右节点new一个值，
        // 让它可以继续往下递归合并
        if (r1?.left == null && r2?.left != null){
            r1?.left = TreeNode(0)
        }

        if (r1?.right == null && r2?.right != null){
            r1?.right = TreeNode(0)
        }

        mergeTrees(r1?.left,r2?.left)
        mergeTrees(r1?.right,r2?.right)
        return r1
    }

    /**
     * 上面的做法有冗余的操作，给root1的左右节点new值的时候，new完再继续往下合并，其实没必要，如果roo1.left为空，root2.left不为空，直接让roo1.left = root2.left，
     * 把root2的左节点挂到root1那边对应的位置上去就行了，不需要再继续对比下面的分支，这样会少一些操作
     *
     * 下面这个递归处理操作了当前节点的值，还把左右节点的值也合并了，直接合并了三个节点的值，多了在root1出现空节点时直接将root2的左节点挂到root1的操作，节省步骤
     */
    fun mergeTrees1(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (null == root1 && null == root2) return null
        if (null == root1) return root2
        if (null == root2) return root1

        root1.`val` = root1.`val` + root2.`val`
        root1.left = mergeTrees1(root1.left,root2.left)
        root1.right = mergeTrees1(root1.right,root2.right)

        return root1
    }
}





























