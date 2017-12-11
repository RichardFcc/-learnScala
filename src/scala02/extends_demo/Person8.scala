package scala02.extends_demo

//todo: Scala中匿名内部类

class Person8(val name:String) {
  def sayHello="Hello ,I'm "+name

}
class GreetDemo{
  //接受Person8参数，并规定Person8类只含有一个返回String的sayHello方法
  def greeting(p:Person8{ def sayHello:String})={
          println(p.sayHello)
  }
}

object GreetDemo {
  def main(args: Array[String]) {

    //创建匿名内部类，并将其实例
    val p=new Person8("tom")
    val g=new GreetDemo
    g.greeting(p)

  }
}






