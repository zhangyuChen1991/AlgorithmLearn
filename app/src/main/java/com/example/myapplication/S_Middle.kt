package com.example.myapplication

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
        addTwoNumbers(l1, l2,0)
        return l1
    }

    /**
     * j是进位
     */
    fun addTwoNumbers(l1: ListNode, l2: ListNode,j: Int) {

        var sum = l1.`val` + l2.`val` + j
        l1.`val` = sum % 10
        var nextJ = sum / 10

        if (null == l1.next && null == l2.next){
            if (nextJ!= 0){
                l1.next = ListNode(nextJ)
            }
            return
        } else if (l1.next == null && l2.next != null){
            l1.next = ListNode(0)
        }else if(l1.next != null && l2.next == null){
            l2.next = ListNode(0)
        }
        addTwoNumbers(l1.next!!,l2.next!!,nextJ)
    }
}