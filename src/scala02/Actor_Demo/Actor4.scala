package scala02.Actor_Demo

/**
  * 怎么实现actor可以不断地接受消息：
      在act方法中可以使用while(true)的方式，不断的接受消息。发送消息可以使用 !(异步无返回)、!?(同步等待返回)、!!(异步有返回)。
      接受消息可以使用关键字receive。
      还可以使用模式匹配的方式，对接受到的不同消息的指令作出对应的操作。
  */

import scala.actors.Actor

//todo:第三个案例：利用scala中的actor，实现消息的不断发送和接受
class Actor4  extends Actor{
  //重写act方法
  override def act(): Unit = {
    //不断的接受消息需要在receive方法外面调用while（true）
    while (true) {
      receive {
        case "start" => println("starting...")
        case "stop" => println("stopping...")
      }
    }
  }

}

object Actor4{
  def main(args: Array[String]): Unit = {
    //todo:1、创建actor
   val actor = new Actor4
    //todo:2、启动actor
    actor.start()
    //todo:3、发送消息
    actor ! "start"

    actor ! "stop"
  }
}