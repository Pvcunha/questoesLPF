import java.util.Scanner

//2 + 3 * 4
abstract class Exp
data class Val(val valor: Int): Exp()
data class Var(val incognita: Char): Exp()
data class operacao(val esq: Exp, val operador: Char, val dir: Exp): Exp()

fun deriva(expressao: Exp, v: Char) = when(expressao){
    is operacao
} 

fun main(args: Array<String>) {
    val arvore: Exp = Soma(Val(2), Mult(Val(3), Val(4)))
    println(define(arvore))
}