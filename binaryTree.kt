data class Node(val info: Int, val left: Node?, val right: Node?)

fun compare(a:Int, b:Int) = if(a>b) a else b

fun size(root: Node?): Int{
    if(root != null){
        val left: Int = size(root.left) + 1
        val right: Int = size(root.right) + 1
        return compare(left, right)
    } else return -1 
}

fun higher(root: Node?): Int{
    if(root != null){
        if(node.right == null) 
            return node.info 
        else 
            return higher(root.right)
    } else 
        null
}

fun main(){

}