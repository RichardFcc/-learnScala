package scala02.cases
//匹配类型
import scala.util.Random

object CaseDemo02 extends App{
  val arr = Array("hello", 1, 2.0, CaseDemo02)
  val v = arr(Random.nextInt(arr.length))
  println(v)
  v match {
    case x: Int => println("Int " + x)
    case y: Double if(y >= 0) => println("Double "+ y)
    case z: String => println("String " + z)
    case _ => throw new Exception("not match exception")
  }
}
