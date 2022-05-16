package com.example.myapplication.utils

import com.example.myapplication.S

/**
 * Created by zhangyu on 2022/5/16.
 */
class TransformUtils {
    companion object{
        fun arrayToListNode(array: IntArray) : S.ListNode?{
            if (null == array || array.isEmpty()) return null
            var head = S.ListNode(array[0])
            var currNode = head
            for (i in 1 until array.size){
                currNode.next = S.ListNode(array[i])
                currNode = currNode.next!!
            }
            return head
        }
    }
}