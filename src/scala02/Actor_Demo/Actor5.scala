package scala02.Actor_Demo

/**
  * 使用关键字react代替receive去接受消息
      好处： react方式会复用线程，避免频繁的线程创建、销毁和切换。比receive更高效
      注意:  react 如果要反复执行消息处理，react外层要用loop，不能用while
  */

import scala.actors.Actor

//todo:第四个案例：利用scala中的actor实现消息的不断发送和接受
//todo: react 可以接受消息  ，想要不断的接受和发送消息必须与loop方法一起使用
class Actor5 extends Actor{
  override def act(): Unit = {
    loop {
      react {
        case "start" => println("starting....")
        case "stop" => println("stopping....")
      }
    }
  }
}

object Actor5{
  def main(args: Array[String]): Unit = {
    //1、创建actor
    val actor = new Actor5
    //2、启动actor和发送消息
    actor.start() ! "start"
    actor ! "stop"
  }
}