package scala02.extends_demo

//todo:Scala中override和super关键字
class Person1 {
  private val name = "leo"
  val age=50
  def getName = this.name
}

class Student1 extends Person1{
  private val score = "A"
  //子类可以覆盖父类的 val field,使用override关键字
  override
  val age=30
  def getScore = this.score
  //覆盖父类非抽象方法，必须要使用 override 关键字
  //同时调用父类的方法，使用super关键字
 
  override def getName = "your name is " + super.getName
}
