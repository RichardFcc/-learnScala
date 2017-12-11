package scala02.triat_demo

//todo: trait继承class

class MyUtil {
  def printMsg(msg: String) = println(msg)
}

trait Logger_Two extends MyUtil {
  def log(msg: String) = this.printMsg("log: " + msg)
}

class Person_Three(val name: String) extends Logger_Two {
    def sayHello {
        this.log("Hi, I'm " + this.name)
        this.printMsg("Hello, I'm " + this.name)
  }
}

object Person_Three{
  def main(args: Array[String]) {
      val p=new Person_Three("Tom")
      p.sayHello
    //执行结果：
//      log: Hi, I'm Tom
//      Hello, I'm Tom
  }
}
