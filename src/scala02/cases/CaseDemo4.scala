package scala02.cases

import scala.util.Random
//todo:样例类
//case class是多例的，后面要跟构造参数，case object是单例的，构建的时候没用参数。

case class SubmitTask(id: String, name: String)
case class HeartBeat(time: Long)
case object CheckTimeOutTask
case object stoptask
object CaseDemo04 extends App{
  val arr = Array(CheckTimeOutTask, HeartBeat(12000), SubmitTask("0001", "task-001"))

  arr(Random.nextInt(arr.length)) match {
    case SubmitTask(id, name) => { println(s"$id, $name")}
    case HeartBeat(time) => { println(time)}
    case CheckTimeOutTask => {println("check...")}
    case stoptask    =>{println("stop...")}
  }
}