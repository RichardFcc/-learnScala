package scala02.extends_demo

//todo: Scala中使用模式匹配进行类型判断
class Person5 {}
class Student5 extends Person5

object Student5{
  def main(args: Array[String]) {
    val p:Person5=new Student5

    p match {
      // 匹配是否为Person类或其子类
      case per:Person5 => println("This is a Person5's Object!")
      // 匹配所有剩余情况
      case _  =>println("Unknown type!")
    }
 
  }
}

