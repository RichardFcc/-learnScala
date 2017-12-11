package scala02.triat_demo

trait SuperLogger {
  def log(msg: String): Unit
}

trait MyLoggers extends SuperLogger {
  //这里必须加上abstract override  否则报错
  abstract override def log(msg: String) = {
      println("log: " + msg)
      super.log(msg)
    }
}
