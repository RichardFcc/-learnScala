package scala02.extends_demo

//todo: Scala中抽象类

abstract class Person9(val name:String) {
  //必须指出返回类型，不然默认返回为Unit
  def sayHello:String

  def sayBye:String

}

class Student9(name:String) extends Person9(name){
  //必须指出返回类型，不然默认
  //子类实例化可以写overide也可以不写
  override
  def sayHello: String = "Hello,"+name

  def sayBye: String ="Bye,"+name

} 

object Student9{
  def main(args: Array[String]) {
    val s = new Student9("tom")
    println(s.sayHello)
    println(s.sayBye)
  }
}
