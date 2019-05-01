package tree

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

abstract class List<out T>
data class NoLista<T>(val head: T, val tail: List<T>): List<T>()
object vazio: List<Nothing>()

fun <T>concat(l: List<T>, l2: List<T>): List<T> = when(l){
    is NoLista<T> -> NoLista(l.head, concat(l.tail, l2))
    else -> l2
}

fun <T>fazListaEsq(t: Tree3<T>, n: Int): List<Tree3<T>> = when(t){
    is NoArv<T> -> if(n > 0) if(n == 1) transforma(t, n -1) else fazListaEsq(t.l, n-1) else NoLista(t, vazio)
    else -> vazio
}
fun <T>fazListaMeio(t: Tree3<T>, n: Int): List<Tree3<T>> = when(t){
    is NoArv<T> -> if(n > 0) if(n == 1) transforma(t, n) else fazListaMeio(t.m, n-1) else concat(NoLista(t, vazio), concat(fazListaDir(t.m, n) , fazListaDir(t.r, n)))
    else -> vazio
}

fun <T>fazListaDir(t: Tree3<T>, n: Int): List<Tree3<T>> = when(t){
    is NoArv<T> -> if(n > 0) if(n == 1) transforma(t, n -1) else fazListaDir(t.r, n-1) else NoLista(t, vazio)
    else -> vazio
}

fun <T>transforma(t: Tree3<T>, n: Int): List<Tree3<T>> =
    if(t is NoArv<T>) concat(concat(fazListaEsq(t, n), fazListaMeio(t, n)), fazListaDir(t, n)) else vazio


fun main(){
    val t = NoArv(20, NoArv(10, NoArv(8, Nulo, Nulo, Nulo), NoArv(10, Nulo, Nulo, Nulo), NoArv(12, NoArv(11, Nulo, Nulo, Nulo), NoArv(12, Nulo, Nulo, Nulo), NoArv(15, Nulo, Nulo, Nulo))), NoArv(20,NoArv(19, Nulo, Nulo, Nulo),NoArv(20, Nulo, Nulo, Nulo),NoArv(21, Nulo, Nulo, Nulo)), NoArv(30,NoArv(25, Nulo, Nulo, Nulo),NoArv(30, Nulo, Nulo, Nulo),NoArv(40, NoArv(35, Nulo, Nulo, Nulo), NoArv(40, Nulo, Nulo, Nulo), NoArv(50, Nulo, Nulo, Nulo))))
    println(transforma(t, 2))
}