package scala02.triat_demo

//todo:将trait作为接口使用

trait HelloTrait {
  //无参的抽象方法
  def sayHello(): Unit
}


trait MakeFriendsTrait {
  //有参的抽象方法
  def makeFriends(c: Children): Unit
}

//多重继承 trait
//Cloneable克隆
//Serializable序列化
class Children(val name: String) extends HelloTrait with MakeFriendsTrait with Cloneable with Serializable{
  override
  def sayHello() =println("Hello, " + this.name)
  def makeFriends(c: Children) = println("Hello, my name is " + this.name + ", your name is " + c.name)
}


object Children{
  def main(args: Array[String]) {
    val c1=new Children("tom")
    val c2=new Children("jim")
    //调用的时候看，有没有参数，集体调用的时候就可以看参数找到属于主构造器还是辅助构造器
    //谁调用this就是谁
    c1.sayHello()
    c1.makeFriends(c2)
  }
}
