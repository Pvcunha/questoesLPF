/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */
data class No(val info: Int, val prox: No?)

fun inverte(atual: No?): No?{
    when{
        atual == null -> return null
        
        else ->
        	return concat(inverte(atual.prox), No(atual.info, null))
    }
}

fun palindrome(atual: No?): Boolean{
    return atual == inverte(atual)
}

fun concat(l1: No?, l2: No?): No?{
    if(l1 == null){
        return l2
    } 
    else if(l2 == null){
        return l1
    } else {
        if(l1.prox == null)
        	return No(l1.info, l2)
        else 
        	return No(l1.info, concat(l1.prox, l2))
    }
}

fun main(args: Array<String>) {
    val l1 = No(1, No(2, No(3, No(2, No(1, null)))))
    val l2 = No(5,No(6, null))
    print(palindrome(l1))
}
