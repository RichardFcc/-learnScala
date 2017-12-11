package scala02.triat_demo

//todo :trait 调用链
trait HandlerTrait {
  def handle(data: String) = {println("last one")}
}
 
trait DataValidHandlerTrait extends HandlerTrait {
  override def handle(data: String) = {
              println("check data: " + data)
              super.handle(data)
}
}

trait SignatureValidHandlerTrait extends HandlerTrait {
  override def handle(data: String) = {
          println("check signature: " + data)
            super.handle(data)
  }
}

class PersonForRespLine(val name: String) extends SignatureValidHandlerTrait with DataValidHandlerTrait {
  def sayHello = {
        println("Hello, " + this.name)
        this.handle(this.name)
  }
}
object PersonForRespLine{
  def main(args: Array[String]) {
     val p=new PersonForRespLine("tom")
      p.sayHello
      //执行结果：
//    Hello, tom
//    check data: tom
//    check signature: tom
//    last one
  }
}
