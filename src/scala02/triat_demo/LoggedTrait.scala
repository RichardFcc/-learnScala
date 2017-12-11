package scala02.triat_demo

//todo:在实例对象指定混入某个trait

trait LoggedTrait {
  // 该方法为实现的具体方法，只是没有输出
  def log(msg: String) = {}

}

trait MyLogger extends LoggedTrait{
  // 覆盖 log() 方法
override
def log(msg: String) = println("log: " + msg)

}

class PersonForMixTraitMethod(val name: String) extends LoggedTrait {
  def sayHello = {
    println("Hi, I'm " + this.name)
    log("sayHello method is invoked!")
  }
}

object PersonForMixTraitMethod{
  def main(args: Array[String]) {

    val tom=new PersonForMixTraitMethod("Tom")
    tom.sayHello //Hi, I'm Tom

    // 使用with关键字，指定混入MyLogger trait
    val rose = new PersonForMixTraitMethod("Rose") with MyLogger
    rose.sayHello
    //Hi, I'm Tom 后面还要走log方法，但是是不做输出的

    //Hi, I'm Rose
    // og: sayHello method is invoked!
          //不再走父类的log方法，而是走混入的trait的方法

  }
}