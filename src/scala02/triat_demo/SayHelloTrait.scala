package scala02.triat_demo

//todo:在trait中定义抽象field
/**
  * 如果父类的方法依赖与父类的抽象字段，那么子类如果想调用父类的方法，子类必须覆盖父类的抽象字段
  */

trait SayHelloTrait {
  //抽象的field
  val msg:String
  //具体的方法可以依赖抽象的field
  def sayHello(name: String) = println(msg + ", " + name)
}

class PersonForAbstractField(val name: String) extends SayHelloTrait {
  //必须覆盖抽象 field
  val msg = "Hello"

  def makeFriends(other: PersonForAbstractField) = {
    this.sayHello(other.name)
    println("I'm " + this.name + ", I want to make friends with you!!")
  }
}

object PersonForAbstractField{
  def main(args: Array[String]) {
    val p1=new PersonForAbstractField("Tom")
    val p2=new PersonForAbstractField("Rose")
    p1.makeFriends(p2)
   }
}

