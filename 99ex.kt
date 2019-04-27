abstract class List()

data class Node(val head: Int, val tail: List): List()

data class No<T>(val head: T, val tail: List) : List()

object Nil: List()

fun last(list: List) : Int = when(list) {
    is Node ->
        if(list.tail == Nil)
            list.head
        else 
            last(list.tail)
    else -> -1    
}

fun concat(l1: List, l2: List): List = when(l1) {
    is No<*> -> No(l1.head, concat(l1.tail,l2))
    else -> l2
}

fun length(l1: List) : Int = when (l1){
    is Node -> length(l1.tail) + 1
    else -> 0
}

fun get(l1: List, index: Int): Int? = when{
    l1 is Node && index != 0 -> get(l1.tail, index-1)
    l1 is Node && index == 0 -> l1.head
    else -> null
}

// questao 5
fun reverse(l1: List): List = when(l1){
    is Node-> concat(reverse(l1.tail), Node(l1.head, Nil))
    else -> Nil
} 

//questao 6
fun palindrome(l1: List): Boolean = l1 == reverse(l1)

//questao 8
fun compress(l1: List) : List = when(l1) {
    is Node ->  Node(l1.head, compressAux(l1.tail,l1.head))
    
    else -> Nil
}

fun compressAux(l1: List, info: Int) : List = when{
    l1 is Node && l1.head != info -> Node(l1.head, compressAux(l1.tail, l1.head))
    
    l1 is Node && l1.head == info -> compressAux(l1.tail, info)
    
    else -> Nil
}

//questao 7
fun flatten(l: List): List = when{
   l is No<*> && l.head is No<*> -> concat(concat(No(l.head.head, Nil), l.head.tail) , flatten(l.tail))
   
   else -> Nil
}

//questao 9
/* fun pack(l: List): List = when{
    
    l is No<*> && l.tail is No<*> && l.head == l.tail.head -> concat(makeNode(l.head),)
    l is No<*> && l.tail is No<*> && l.head != l.tail.head -> concat(pack(l.tail), Nil)
    
    else -> Nil
}

fun makeNode<T>(T info): No<T> = No()
*/
 

fun main(){
    val l1 = No<No<Int> > (No(1, No(2, Nil)), No(No(3, No(4, Nil)), No(No(5, No(6, Nil)), Nil))) 
    //val l1 = Node(1, Node(2, Node(3, Node(2, Node(1, Nil)))))
    //val l2 = Node(5, Node(5, Node(6, Node(6, Nil))))
    //val l3 = No<Int>(1, No(1, No(2, No(2, No(3, Nil)))))
    println(flatten(l1))
}