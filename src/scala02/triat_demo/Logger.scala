package scala02.triat_demo

/**
 * 比如 trait 中可以包含很多子类都通用的方法，例如打印日志或其他工具方法等等。
 * spark就使用trait定义了通用的日志打印方法；
 */

trait Logger {
  //定义了一个 具体的方法，有方法体
  def log(message: String): Unit = println(message)
}

class PersonForLog(val name: String) extends Logger {

  def makeFriends(other: PersonForLog) = {

    println("Hello, " + other.name + "! My name is " + this.name + ", I miss you!!")
    //todo:(this)调用父类的方法
    this.log("makeFriends method is invoked with parameter PersonForLog[name = " + other.name + "]")
  }
}

object PersonForLog{
  def main(args: Array[String]) {
    val p1=new PersonForLog("jack")
    val p2=new PersonForLog("rose")
    p1.makeFriends(p2)
    //Hello, rose! My name is jack, I miss you!!
    //makeFriens method is invoked with parameter PersonForLog[name = rose]
  }
}