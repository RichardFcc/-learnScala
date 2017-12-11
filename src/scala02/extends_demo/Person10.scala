package scala02.extends_demo

//todo: Scala中抽象field
abstract class Person10 (val name:String){
    val age:Int
    def get:String

}

class Student10(name: String) extends Person10(name) {
  val age: Int = 50

  override def get: String = "123"
}