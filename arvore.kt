abstract class Arvore
data class Node(val head: Int, val esq: Arvore, val dir: Arvore): Arvore()
object Nil: Arvore()

fun insere(e: Int, t: Arvore): Arvore = when(t){
    is Node -> if(e > t.head) Node(t.head, t.esq, insere(e, t.dir)) else Node(t.head, insere(e, t.esq), t.dir)
    else -> Node(e, Nil, Nil)
}

fun poda(t: Arvore, e: Int): Arvore = when(t){
    is Node -> {
        if(e != t.head){
            if(e > t.head)
                Node(t.head, t.esq, poda(t.dir, e))
            else
                Node(t.head, poda(t.esq, e), t.dir)
        } else Nil 
    } else -> t
}

fun espelho(t: Arvore): Arvore = when(t){
    is Node -> Node(t.head, espelho(t.dir), espelho(t.esq))
    else -> Nil
}

fun soma(t: Arvore): Int = when(t){
    is Node -> t.head + soma(t.esq) + soma(t.dir)
    else -> 0
}

fun pertence(t: Arvore, e: Int): Boolean = when(t){
    is Node -> if(e == t.head) true else pertence(t.esq, e) || pertence(t.dir, e)
    else -> false 
}

fun buscaBinaria(t: Arvore, e: Int): Boolean = when(t){
    is Node -> if(e == t.head) true else if(e > t.head) buscaBinaria(t.dir, e) else buscaBinaria(t.esq, e)
    else -> false
}

fun profundidade(t: Arvore): Int = when(t){
    is Node->{
        val a = profundidade(t.esq) + 1
        val b = profundidade(t.dir) + 1
        if(a > b) a else b
    }
    else -> 0    
}

fun larguraEsq(t: Arvore): Int = if(t is Node) 1 + larguraEsq(t.esq) else 0
fun larguraDir(t: Arvore): Int = if(t is Node) 1 + larguraEsq(t.dir) else 0

fun largura(t: Arvore): Int = when(t){
    is Node -> 1 + larguraEsq(t.esq) + larguraDir(t.dir) 
    else -> 0
}

fun main(args: Array<String>) {
    var a: Arvore = Node(10, Node(4, Node(3, Nil, Nil), Node(5, Nil, Nil)), Node(15, Node(12, Nil, Nil), Node(18, Nil, Nil)))
    println(largura(a))    
}