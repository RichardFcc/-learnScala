package scala02.extends_demo

//todo: 继承 extends
class Person {
  val name="super"
  def getName=this.name

}

class Student extends Person{
  //继承加上关键字
  override
  val name="sub"

  //子类可以定义自己的field和method
  val score="A"
  def getScore=this.score
}
