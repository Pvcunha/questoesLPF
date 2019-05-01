package tree

abstract class List<out T>
data class NoLista<T>(val head: T, val tail: List<T>): List<T>()
object vazio: List<Nothing>()

//1

fun somaNos(l: List<Int>): Int = when(l){
    is NoLista<Int> -> l.head + somaNos(l.tail)
    else -> 0
}

fun <T>filter(l: List<T>, f: (T) -> Boolean): List<T> =when(l){
    is NoLista<T> -> if(f(l.head)) NoLista(l.head, filter(l.tail, f)) else filter(l.tail, f)
    else -> vazio
}

fun removePar(l: List<Int>) = filter(l, {x: Int -> x%2!=0 })

fun <T>intercala(l: List<T>, l2: List<T>): List<T> = when{
    l is NoLista<T> -> NoLista<T>(l.head, intercala(l2, l.tail))
    else -> l2
}

fun <T>verificaSequencia(l: List<T>, l2: List<T>): Boolean = when(l){
    is NoLista<T> -> if (l2 is NoLista<T>) {
        if(l2.head == l.head) checa(l, l2) else verificaSequencia(l.tail, l2)
    } else false
    else -> false
}

fun <T>checa(l1: List<T>, l2: List<T>): Boolean = when(l2){
    is NoLista<T>-> if(l1 is NoLista<T>)
         if(l1.head == l2.head) checa(l1.tail, l2.tail) else false 
        else false
    else -> true
}


abstract class Tree3<out T>
data class NoArv<T>(val i: T, val l: Tree3<T>, val m: Tree3<T>, val r: Tree3<T>): Tree3<T>()
object Nulo: Tree3<Nothing>()

fun maior(a: Int, b: Int): Int = if(a>b) a else b

fun <T>altura(t: Tree3<T>): Int = when(t){
    is NoArv ->{
        val l = altura(t.l) + 1
        val m = altura(t.m) + 1
        val r  = altura(t.r) + 1

        maior(maior(l,m), r)
    }
    else -> -1
}





fun <T>concat(l: List<T>, l2: List<T>): List<T> = when(l){
    is NoLista<T> -> NoLista(l.head, concat(l.tail, l2))
    else -> l2
}

fun <T>transforma(t: Tree3<T>, n: Int): List<Tree3<T>> = when(t){
    is NoArv<T> -> 
        if(n > 0) {
            val l = transforma(t.l, n -1)
            val m = transforma(t.m, n -1)
            val r = transforma(t.r, n -1)
            concat(concat(l,m), r)
        } else NoLista(t, vazio)
    else -> vazio
}

fun main(){
    //val t = NoArv(20, NoArv(10, NoArv(8, Nulo, Nulo, Nulo), NoArv(10, Nulo, Nulo, Nulo), NoArv(12, NoArv(11, Nulo, Nulo, Nulo), NoArv(12, Nulo, Nulo, Nulo), NoArv(15, Nulo, Nulo, Nulo))), NoArv(20,NoArv(19, Nulo, Nulo, Nulo),NoArv(20, Nulo, Nulo, Nulo),NoArv(21, Nulo, Nulo, Nulo)), NoArv(30,NoArv(25, Nulo, Nulo, Nulo),NoArv(30, Nulo, Nulo, Nulo),NoArv(40, NoArv(35, Nulo, Nulo, Nulo), NoArv(40, Nulo, Nulo, Nulo), NoArv(50, Nulo, Nulo, Nulo))))
    val a: List<Int> = NoLista(1, NoLista(2, NoLista(3, NoLista(4, NoLista(5, NoLista(6, vazio))))))
    val b: List<Int> = NoLista(3, NoLista(4, NoLista(5, vazio)))
    println(verificaSequencia(a,b))
}