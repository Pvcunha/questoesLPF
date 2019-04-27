fun menorDe2(a: Int, b:Int): Int = when{
    a<b -> a
    else -> b
}

fun menorDe3(a: Int, b:Int, c: Int): Int = when{
    a<b && a<c -> a
    b<a && b<c -> b
    else -> c
}

fun fat(n: Int): Int = when(n){
    1 -> 1
    else -> n*fat(n-1)
}

fun imprime(f:(Int)-> Int) = println(f(2))

fun sucessor(n: Int) = n + 1

fun somaN(n: Int): (Int) -> Int {
    return {(m: Int) -> m + n}
}
fun main(args: Array<String>) {
    //var f = ::sucessor
    var f = somaN(8)
    imprime(f)
}