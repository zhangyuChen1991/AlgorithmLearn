package com.example.myapplication

import android.util.Log
import org.w3c.dom.Node
import kotlin.math.min

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
        for (i in nums.indices){
            if (nums[i] != value) {
                nums[j] = nums[i]
                j++
            }
            ret = j
        }
        return ret
    }
}