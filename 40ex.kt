//1
fun maiorde2(a: Int, b: Int) = if(a > b) a else b

//2
fun maiorDe3(a: Int, b: Int, c:Int) = if(maiorde2(a, b) > maiorde2(b, c)) maiorde2(a, b) else maiorde2(b, c)

//3
fun fat(n: Int): Int = if(n == 0) 1 else n*fat(n-1)

//4
fun fib(n: Int): Int = if(n == 0) 0 else if(n == 1) 1 else fat(n - 2) + fat(n - 1)


abstract class List<out T>
data class Node<T>(val head: T, val tail: List<T>): List<T>()
object Nil: List<Nothing>()

//5
fun <T>elemento(l: List<T>, n: Int): T? = when(l){
    is Node<T> -> if(n != 0) elemento(l.tail, n-1) else l.head
    else -> null
}

//6
fun <T>pertence(l: List<T>, e: T): Boolean = when(l){
    is Node<T> -> if(l.head == e) true else pertence(l.tail, e)
    else -> false
}

//7
fun <T>tamanho(l: List<T>): Int = if(l is Node) tamanho(l.tail) + 1 else 0

//8
fun maior(l: List<Int>): Int = if(l is Node) maiorAux(l.tail, l.head) else -1

fun maiorAux(l: List<Int>, e: Int): Int = when {
    l is Node<Int> -> if(l.head > e) maiorAux(l.tail, l.head) else maiorAux(l.tail, e) 
    else -> e
}

//9
fun <T>contaOcorrencias(l: List<T>, e: Int): Int = when(l){
    is Node<T> -> if(l.head == e) contaOcorrencias(l.tail, e) + 1 else contaOcorrencias(l.tail, e)
    else -> 0
}

//10
fun <T>unicaOcorrencia(l: List<T>, e: Int): Boolean = contaOcorrencias(l, e) == 1 

//11
fun maioresQue(l: List<Int>, e: Int): List<Int> = when(l){
    is Node -> if(l.head > e) Node(l.head, maioresQue(l.tail, e)) else maioresQue(l.tail, e)
    else -> Nil
}

//12
fun <T>concat(l: List<T>, l2: List<T>): List<T> = when(l){
    is Node-> Node(l.head, concat(l.tail, l2))
    else -> l2
}

//13
fun <T>remove(l: List<T>, e: T): List<T> = when(l){
    is Node -> if(l.head == e) remove(l.tail, e) else Node(l.head, remove(l.tail, e))
    else -> Nil
}

//14
fun <T>removeUltimo(l: List<T>): List<T> = when(l){
    is Node -> if(l.tail == Nil) removeUltimo(l.tail) else Node(l.head, removeUltimo(l.tail))
    else -> Nil
}

//15
fun <T>removeRepetido(l: List<T>): List<T> = if(l is Node<T>) Node<T>(l.head, removeRepetido(remove(l, l.head))) else Nil

//16
fun maiores(l: List<Int>, e: Int): List<Int> = when(l){
    is Node<Int> -> if(l.head > e) Node(l.head, maiores(l.tail, e)) else maiores(l.tail, e)
    else -> Nil 
}

//17
fun geraSequencia(n: Int): List<Int> = if(n>0) sequenciaAux(n, 1, Nil) else Node<Int>(0, Nil)

fun sequenciaAux(n: Int, curr: Int, l: List<Int>): List<Int> = if(n >= curr) sequenciaAux(n, curr + 1, concat(l, Node<Int>(curr, Node(-curr, Nil)))) else l

//18
fun <T>inverte(l: List<T>): List<T> = if(l is Node) concat(l.tail, Node(l.head, Nil)) else Nil

//19
fun <T>divide(l: List<T>, n: Int): List<List<T> > = when(l){
    is Node<T> -> if(n > 0) Node(Node(l.head, Nil), divide(l.tail, n-1)) else Node(Node(l.head, l.tail), Nil)
    else -> Nil
}

//20
fun <T>intercala(l1: List<T>, l2: List<T>) : List<T> = when{
    l1 is Nil -> l2
    l2 is Nil -> l1
    l1 is Node<T> -> Node(l1.head, intercala(l2, l1.tail))
    else -> Nil
}

//21
//[1, 2, 3, 4] [1, 5, 6, 6] -> [1, 2, 3, 4, 5, 6] 
fun <T>uniao(l1: List<T>, l2: List<T>): List<T> = removeRepetido(concat(removeRepetido(l1), removeRepetido(l2)))

//22
fun <T>intersecao(l1: List<T>, l2: List<T>): List<T> = when(l1){
    is Node -> if(pertence(l2, l1.head)) Node(l1.head, intersecao(l1.tail, l2)) else intersecao(l1.tail, l2)
    else -> Nil
}

//23
fun sequencia(n:Int, m: Int) = if(n>0) seqAux(n, 0, m, Nil) else Nil

fun seqAux(n: Int, curr: Int, m: Int, l: List<Int>): List<Int> = if(n > curr) seqAux(n, curr + 1, m, concat(l, Node(m+curr, Nil))) else l

//24
fun insereOrdenado(l: List<Int>, e: Int): List<Int> = when(l){
    is Node<Int> -> {
        if(e > l.head){
            Node(l.head, insereOrdenado(l.tail, e))
        } else Node(e, l)
    }
    else -> Node(e, Nil)
}

//25
fun ordenado(l: List<Int>) = ordenadoAux(l, tamanho(l))

fun ordenadoAux(l: List<Int>, n: Int): Boolean = when(l){
    is Node<Int> -> {
        if(l.tail is Node<Int>){
            if(l.head > l.tail.head)
                false
            else
                ordenadoAux(l.tail, n-1)
        }else ordenadoAux(l.tail, n-1)
    }
    else -> if(n == 0 || n == 1) true else false 
}

//26
fun ordenaLista(l: List<Int>): List<Int> = ordenaListaAux(l, Nil)

 fun ordenaListaAux(l1: List<Int>, l2: List<Int>): List<Int> = when(l1){
    is Node -> {
        println(l2) 
        ordenaListaAux(l1.tail, insereOrdenado(l2, l1.head))
    }
    else -> l2
}

//27
fun <T>rodaEsq(l: List<T>, n: Int): List<T> = when(l){
    is Node<T> -> rodaAux(l, Nil, n)
    else -> Nil
}

fun <T>rodaAux(l: List<T>, l1: List<T>, n: Int): List<T> = when(l){
    is Node -> if(n > 0) rodaAux(l.tail, add(l1, l.head), n - 1) else concat(l, l1)
    else -> Nil
}


fun <T>add(l:List<T>, e: T): List<T> = if(l is Node<T>) Node(l.head, add(l.tail, e)) else Node(e, Nil)  

/* fun <T>reverse(l: List<T>): List<T> = when(l){
    is Node-> concat(reverse(l.tail), Node(l.head, Nil))
    else -> Nil
} */

//28
/* fun <T>rodaDir: List<T>, n: Int): List<T> = when(l){
    is Node -> if(n>0) concat(rodaDir(l.tail))
}*/

//29
fun paraMaiuscula(text: String): String = paraMaiusculaAux(text, 0)

fun paraMaiusculaAux(text:String, index: Int): String = 
    if(text.length > index){
        if(text.get(index)>= 'a' && 'z' >= text.get(index))
            "${text.get(index) -32}${paraMaiusculaAux(text,index+1)}"
        else 
            "${text.get(index)}${paraMaiusculaAux(text,index+1)}"

    } else 
        ""

//30
fun primeiraMaius(text: String) = primeiraMaiusAux(text, 0)

fun primeiraMaiusAux(text: String, index: Int): String = 
    if(index == 0){
        if(text.get(index)>'a' && 'z' > text.get(index))
            "${text.get(index)-32}${primeiraMaiusAux(text, index+1)}"
        else 
            "${text.get(index)}${primeiraMaiusAux(text, index+1)}"
    }else if(text.length > index){
        if(text.get(index-1) == ' ')
            "${text.get(index)-32}${primeiraMaiusAux(text, index+1)}"
        else if((text.get(index)>'A' && 'Z' > text.get(index)))
            "${text.get(index+32)}${primeiraMaiusAux(text, index+1)}"
        else
            "${text.get(index)}${primeiraMaiusAux(text, index+1)}"
    }else 
        ""

//31
fun <T>seleciona(l1: List<T>, listIndex: List<Int>): List<T?> = when(listIndex){
    is Node<Int> -> Node(elemento(l1, listIndex.head), seleciona(l1, listIndex.tail)) 
    else -> Nil
}

//32


//33
fun primo(n: Int) = primoAux(n, 2)

fun primoAux(n: Int, curr: Int): Boolean = 
    if(n > curr){
        if(n%curr == 0) false else primoAux(n, curr+1) 
    } else true

//34
fun somaDigitos(n: Int) : Int = if(n>0) n%10 + somaDigitos(n/10) else 0


fun main(){
    var a: List<Int> = Node(1,Node(10, Node(35, Node(24, Nil))))
    //var b: List<Int> = Node(1, Node(10, Node(16, Node(25, Nil))))
    println(ordenaLista(a))
}