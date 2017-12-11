package scala02.cases
//todo:Option类型 包含2个子类，一个是some(表示有值),一个是none(表示没值)
object OptionDemo extends App {
  //定义一个map
    val map = Map("a" -> 1, "b" -> 2)
    val v = map.get("c")

    v match {
      case Some(i) =>  println("存在。。。"+i)
      case None    =>  println("不存在。。。")
    }
    println(v)
    //更好的方式
    val v1 = map.getOrElse("c", 0)
    println(v1)

}
