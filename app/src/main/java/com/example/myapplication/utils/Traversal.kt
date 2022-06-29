package com.example.myapplication.utils

import java.util.*

class Traversal {
    /*
                 45
                / \
              34   5
             / \    \
            12  2    3
           /   / \    \
          33  76  8   44
    */
    class TreeNode internal constructor(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

        //有这个就很容易计算出数组大小了
        var depth = 0
        var index = 0
    }

    /**
     * 数组转换成树
     */
    public fun arrayToTree(arr: Array<Int?>):TreeNode? {
        val root = generateTreeFromArray(arr, 0)
        return root
    }

    /**
     * 树转换成数组
     */
    fun treeToArray() {
        //生成一棵树。数组没有元素情况，本例不考虑
        val arr = arrayOf(45, 34, 5, 12, 2, null, 3, 33, null, 76, 8, null, null, null, 44)
        val root = generateTreeFromArray(arr, 0)
        //根据depth，计算数组size
        val arraySize = Math.pow(2.0, root!!.depth.toDouble()).toInt() - 1
        val dst = arrayOfNulls<Int>(arraySize)
        //再将node转换成对应的数组,根据index填充
        generateArrayFromTree(dst, root)
        println(Arrays.toString(dst)) //打印验证
    }

    companion object {
        private fun generateArrayFromTree(arr: Array<Int?>, node: TreeNode?) {
            if (node == null) {
                return
            }
            arr[node.index] = node.`val`
            generateArrayFromTree(arr, node.left)
            generateArrayFromTree(arr, node.right)
        }

        private fun generateTreeFromArray(arr: Array<Int?>, index: Int): TreeNode? {
            if (index > arr.size - 1 || arr[index] == null) {
                return null
            }
            arr[index]?.let {
                val myTreeNode = TreeNode(arr[index]!!)
                myTreeNode.left = generateTreeFromArray(arr, 2 * index + 1)
                myTreeNode.right = generateTreeFromArray(arr, 2 * index + 2)
                val leftDepth = if (myTreeNode.left == null) 0 else myTreeNode.left!!.depth
                val rightDepth = if (myTreeNode.right == null) 0 else myTreeNode.right!!.depth
                myTreeNode.depth = Math.max(leftDepth, rightDepth) + 1
                myTreeNode.index = index
                return myTreeNode
            }
           return null
        }
    }
}