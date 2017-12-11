package scala02.triat_demo

//todo: trait的构造机制

class Person_One {
  println("Person's constructor!")
}
 
trait Logger_One {
  println("Logger's constructor!")
}

trait MyLogger_One extends Logger_One {
  println("MyLogger's constructor!")
}

trait TimeLogger_One extends Logger_One {
  println("TimeLogger's contructor!")
}

class Student_One extends Person_One with MyLogger_One with TimeLogger_One {
  println("Student's constructor!")
  }

object exe_one {
  def main(args: Array[String]): Unit = {
    val student = new Student_One


    //执行结果为：
//      Person's constructor!
//      Logger's constructor!
//      MyLogger's constructor!
//      TimeLogger's contructor!
//      Student's constructor!
  }
}
