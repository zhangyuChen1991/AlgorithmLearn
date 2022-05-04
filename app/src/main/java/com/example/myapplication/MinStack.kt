package com.example.myapplication

import java.util.*

/**
 * 155. 最小栈
 *设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

    实现 MinStack 类:

    MinStack() 初始化堆栈对象。
    void push(int val) 将元素val推入堆栈。
    void pop() 删除堆栈顶部的元素。
    int top() 获取堆栈顶部的元素。
    int getMin() 获取堆栈中的最小元素。

    这个题不是要你去重新写一个栈。主要是考察怎么样快速去获取一个栈结构里面的最小值；
    pop， push，getTop，这些操作在LinkedList里面都有现成的方法，直接用就行。

    快速获取栈中的最小值，最直接想到的是入栈的时候用一个变量来保存最小值，getMin的时候直接返回；但是一旦发生出栈操作，最小值就可能发生了变化
    此时就又要去遍历栈才能找到目标，每次出栈都要重新遍历，这样不行。
    所以，用一个栈来记录历史最小值，每当push一个值进来，算好最小值，push进这个栈，这个栈每一层的值都是对应每一层数据栈的最小值。
    当发生出栈操作时，存最小值的栈也出栈一个，栈顶的就是当前的最小值
    结构如下：
    vStack:   1,2,3,1,-1
    minStack: 1,1,1,1,-1
 * Created by zhangyu on 2022/5/4.
 */
class MinStack {
    var minStack: LinkedList<Int> = LinkedList()
    var vStack: LinkedList<Int> = LinkedList()
    fun push(`val`: Int) {
        vStack.push(`val`)
        if (`val` < minStack.peek()){
            minStack.push(`val`)
        }else{
            minStack.push(minStack.peek())
        }
    }

    fun pop() {
        vStack.pop()
        minStack.pop()
    }

    fun top(): Int {
        return vStack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }

    constructor(){
        minStack.add(Int.MAX_VALUE)
    }
}