package scala02.extends_demo

//todo:Scala中getClass 和 classOf
class Person4 {}
class Student4 extends Person4

object Student4{
  def main(args: Array[String]) {

    val p:Person4=new Student4
    //判断p是否为Person4类的实例
    println(p.isInstanceOf[Person4])//true

    //判断p的类型是否为Person4类
    println(p.getClass == classOf[Person4])//false

    //判断p的类型是否为Student4类
    println(p.getClass == classOf[Student4])//true

  }
}

