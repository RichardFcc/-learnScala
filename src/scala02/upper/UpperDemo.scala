package scala02.upper

//todo:小案例：在派对上交朋友
//上边界 Bounds 语法 T <: 父类

class SuperPerson(name:String)
class Person(val name: String)  extends  SuperPerson(name:String){

  def sayHello = println("Hello, I'm " + this.name)
  def makeFriends(p: Person) = {
    sayHello
    p.sayHello
  }

}

class Student(name: String) extends Person(name)

//接受的参数只能为person子类
class Party1[T <: Person](val p1: T, val p2: T)  {

  def play = p1.makeFriends(p2)
}

object UpperDemo {
  def main(args: Array[String]) {
    new Party1(new Student("lisi"),new Student("zhangsan")).play

    new Party1(new Person("lisi"),new Student("zhangsan")).play

    //new Party1(new SuperPerson("lisi"),new SuperPerson("zhangsan")).play
  }
}

