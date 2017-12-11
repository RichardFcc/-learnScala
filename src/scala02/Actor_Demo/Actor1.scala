package scala02.Actor_Demo

/**
  * 怎么实现actor并发编程：
    1、定义一个class或者是object继承Actor特质，注意导包import scala.actors.Actor
    2、重写对应的act方法
    3、调用Actor的start方法执行Actor
    4、当act方法执行完成，整个程序运行结束
  */

import scala.actors.Actor

//todo:第一个例子：利用scala中的actor实现并发编程
//todo:1、定义一个类或者是object继承Actor(scala.actors.Actor)
//todo:2、重写act方法
//todo:3、创建一个actor，并且启动start
class Actor1 extends  Actor{
  //重写act方法
  override def act(): Unit = {
    for(i <- 1 to 10){
      println("actor1--"+i)
      Thread.sleep(2000)
    }

  }
}
class Actor2 extends  Actor{
  //重写act方法
  override def act(): Unit = {
    for(i <- 1 to 10){
      println("actor2--"+i)
      Thread.sleep(2000)
    }

  }
}
object MyActor {
  def main(args: Array[String]): Unit = {
    //1、创建一个Actor
    val actor1 = new Actor1
    val actor2 = new Actor2
    //2、启动actor
    actor1.start()
    actor2.start()
  println("actor执行完毕")
  }
}
/**
  * 说明：上面分别调用了两个单例对象的start()方法，他们的act()方法会被执行，相同与在java中开启了两个线程，线程的run()方法会被执行
   注意：这两个Actor是并行执行的，act()方法中的for循环执行完成后actor程序就退出了
  */
