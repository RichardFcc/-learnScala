package scala02.Actor_Demo

/**
  * 怎么实现actor发送、接受消息
    1、定义一个class或者是object继承Actor特质，注意导包import scala.actors.Actor
    2、重写对应的act方法
    3、调用Actor的start方法执行Actor
    4、通过不同发送消息的方式对actor方式消息
    5、act方法中通过receive方法接受消息并进行相应的处理
    6、act方法执行完成之后，程序退出
  */

import scala.actors.Actor

//todo:第二个案例：利用scala中的actor现实消息的发送和接受
class Actor3 extends Actor{
  //重写act
  override def act(): Unit = {
    //接受消息的
    //使用偏函数
    receive{
      case "start" => println("start...")
      case "stop"  =>println("stop...")
    }
  }
}

object Actor3{

  def main(args: Array[String]): Unit = {
      //1、创建一个actor
      val actor = new Actor3
      //2、启动actor
      actor.start()
      //3、开始发送消息
      actor ! "start"

      actor ! "stop"
  }

}