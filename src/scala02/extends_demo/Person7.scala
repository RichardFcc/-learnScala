package scala02.extends_demo

//todo: Scala中调用父类的constructor
class Person7(val name:String,val age:Int){
  var score :Double=0.0
  var address:String="beijing"

  
  def this(name:String,score:Double)={
    //每个辅助constructor的第一行都必须调用其他辅助constructor或者主constructor代码
    //主constructor代码
      this(name,30)
      this.score=score
    println("name"+name,"score="+score)
  }

  //其他辅助constructor
  def this(name:String,address:String)={
      this(name,100.0)
      this.address=address
  }
}

class Student7(name:String,score:Double) extends Person7(name,score){}

object Student7{
  def main(args: Array[String]): Unit = {
    val student: Student7 = new Student7("zhangsan",100.00)
  }
}
