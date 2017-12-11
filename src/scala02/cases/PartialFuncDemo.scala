package scala02.cases
/*偏函数

* 被包在花括号内没有match的一组case语句是一个偏函数，
* 它是PartialFunction[A, B]的一个实例，A代表参数类型，B代表返回类型，常用作输入模式匹配，
* 偏函数最大的特点就是它只接受和处理其参数定义域的一个子集。
* */
object PartialFuncDemo  {

  val func1: PartialFunction[String, Int] = {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }
  //定义一个普通方法，通过match和case实现函数逻辑
  def func2(num: String) : Int = num match {
    case "one" => 1
    case "two" => 2
    case _ => -1
  }

  def main(args: Array[String]) {
    println(func1("one"))
    println(func2("two"))
  }
}
