data class Node(val info: Int, val next: Node?)

fun length(head: Node?) = lengthAux(head)

fun lengthAux(current: Node?): Int = if(current == null) 0 else lengthAux(current.next) + 1

fun contains(head: Node?, e: Int) : Boolean = containsAux(head, e)

fun containsAux(current: Node?, e: Int): Boolean =
  if(current == null) false else if(current.info == e) true else containsAux(current.next, e)

fun concat(head1: Node?, head2: Node): Node? = concatAux(head1, head2)

fun concatAux(current: Node?, head2: Node?): Node? =
  if(current != null && head2 != null)
    if(current.next == null) Node(current.info, head2) else Node(current.info, concatAux(current.next, head2))
  else null

fun intercal(head1: Node?, head2: Node?): Node? = intercalAux(head1, head2)

fun intercalAux(curr1: Node?, curr2: Node?) :Node? =
  if(curr1 != null)
    Node(curr1.info, intercalAux(curr2, curr1.next))
  else 
    if(curr2 != null)
      Node(curr2.info, curr2.next)
    else 
      null

fun rmv(head: Node?, pos: Int): Node? = if(head == null) null else rmvAux(head, pos, 0)

fun rmvAux(curr: Node, pos: Int, i: Int): Node? = 
  if(pos == i+i) 
    Node(curr.info, curr.next?.next) 
  else 
    if(curr.next != null) 
      Node(curr.info, rmvAux(curr.next, pos, i + 1))
    else 
      null

fun add(head: Node?, value: Int, pos: Int): Node? = if(head==null) null else addAux(head, value, pos, 0)

fun addAux(curr: Node, value: Int, pos: Int, i: Int): Node = 
  if(pos == i+1) Node(curr.info, Node(value, curr.next)) else Node(curr.info, curr.next)


fun main(args: Array<String>) {
  val l1 = Node(1, Node(2, Node(3, null)))
  val l2 = Node(4, Node(5, null))
  val l3 = intercal(l1,l2)
  println(l1)
  println(l2)
  println(concat(l1,l2))
}
  
