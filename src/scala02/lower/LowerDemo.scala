package scala02.lower

//todo:下边界Bounds的语法 R >: 子类
//todo:案例，领身份证
class Father(val name: String)

class Other(name:String) extends Child(name)
class Child(name: String) extends Father(name){

  def getIDCard[R >: Child](person: R) = {

    if (person.getClass == classOf[Child]){

      println("please tell me your parents' names.")

    } else if (person.getClass() == classOf[Father]) {

      println("sign your name for your child's ID card.")

    } else println("sorry, you are not allowed to get ID card.")
  }
}


object LowerDemo {
  def main(args: Array[String]) {
    new Child("lisi").getIDCard(new Father("zhangsan"))

    //new Child("lisi").getIDCard(new Other("zhangsan"))

  }
}
