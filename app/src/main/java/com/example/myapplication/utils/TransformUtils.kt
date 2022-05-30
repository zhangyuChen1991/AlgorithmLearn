package com.example.myapplication.utils

import com.example.myapplication.ListNode


/**
 * Created by zhangyu on 2022/5/16.
 */
class TransformUtils {
    companion object{
        fun arrayToListNode(array: IntArray) : ListNode?{
            if (null == array || array.isEmpty()) return null
            var head = ListNode(array[0])
            var currNode = head
            for (i in 1 until array.size){
                currNode.next = ListNode(array[i])
                currNode = currNode.next!!
            }
            return head
        }
    }
}