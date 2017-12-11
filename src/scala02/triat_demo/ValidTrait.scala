package scala02.triat_demo

//todo: 混合使用 trait 的具体方法和抽象方法

trait ValidTrait {
  def getName: String                             //抽象方法
  def valid: Boolean = {"Tom".equals(this.getName)//具体方法，具体方法的返回值依赖于抽象方法

  } 
}

class PersonForValid(val name: String) extends ValidTrait {
  def getName: String = this.name
}

object exe {
  def main(args: Array[String]): Unit = {
    val person = new PersonForValid("Tom")
    println(person.valid)
  }
}
