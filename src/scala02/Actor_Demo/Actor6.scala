package scala02.Actor_Demo

/**
  * 结合case class样例类发送消息和接受消息
   1、将消息封装在一个样例类中
   2、通过匹配不同的样例类去执行不同的操作
   3、Actor可以返回消息给发送方。通过sender方法向当前消息发送方返回消息
  */

import scala.actors.{Actor, Future}

//todo:第五个案例：利用scala中的actor，结合case class样例类不断的接受处理消息

//定义样例类封装参数
case class SyncMessage(id:Int,message:String)   //同步消息
case class AsycMessage(id:Int,message:String)   //异步消息
case class ReplyMessage(message:String)   //返回的消息

class Actor6  extends Actor{
  override def act(): Unit ={
    loop{
      react{
        case AsycMessage(id,message)=>{
          println(s"id:$id,message:$message")

          //通过sender返回处理结果
          sender ! ReplyMessage("异步消息处理成功")
        }
        case SyncMessage(id,message)=>{
          println(s"id:$id,message:$message")
          //处理完成之后返回消息给发送发 sender表示消息的发送发
          sender ! ReplyMessage("同步消息处理成功")
        }
      }
    }
  }
}

object Actor6{
  def main(args: Array[String]): Unit = {
      //1、创建一个actor
        val actor = new Actor6
      //2、启动actor
       actor.start()
      //3、发送样例类消息
      //3.1 发送异步消息无返回值
      actor !  AsycMessage(1,"我是一个异步无返回值的消息")
      //3.2 发送一个同步消息
      val result: Any = actor !? SyncMessage(2,"我是一个同步消息")
     println(result)

      //3.3 发送一个异步有返回值的消息
      val result1: Future[Any] = actor !! AsycMessage(3,"我是一个异步有返回值的消息")
      //获取异步有返回值消息的结果
      // Future的apply方法，会一直判断future是否有值，有值就将值带来
      val result2: Any = result1.apply()
      println(result2)

  }
}
