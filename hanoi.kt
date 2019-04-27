
fun move(source: String, destiny: String, disk: Int): Unit{
  println("disk - $disk from $source to $destiny")
}

fun hanoi(disks: Int, source: String, destiny: String , aux: String): Unit{
  if(disks > 0){
    hanoi(disks-1, source, aux, destiny)
    move(source, destiny, disks)
    hanoi(disks-1, aux, destiny, source)
  }
}

fun main(args: Array<String>){
  hanoi(3, "Haste - 1", "Haste - 2", "Haste - 3")
}
