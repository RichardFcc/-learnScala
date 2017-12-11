package scala02.triat_demo

// todo: trait field的初始化
//lazy 特性来实现
//trait对field进行初始化
trait SayHello_One {

 lazy val msg: String=null
  println(this.msg)
}

class Person_Two extends SayHello_One {
  override lazy val msg: String = "Tom"
}

object Person_Two{
  def main(args: Array[String]) {
    val p = new Person_Two
    p.msg
  }
}

