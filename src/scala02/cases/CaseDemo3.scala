package scala02.cases
// 匹配数组、元组
/*
注意：在Scala中列表要么为空（Nil表示空列表）要么是一个head元素加上一个tail列表。
9 :: List(5, 2)  :: 操作符是将给定的头和尾创建一个新的列表
注意：:: 操作符是右结合的，如9 :: 5 :: 2 :: Nil相当于 9 :: (5 :: (2 :: Nil))
*/
object CaseDemo03 extends App{
 //匹配数组
  val arr = Array(1, 3, 5)
  arr match {
    case Array(1, x, y) => println(x + " " + y)
    case Array(0) => println("only 0")
    case Array(0, _*) => println("0 ...")
    case _ => println("something else")
  }
//匹配列表
  val lst = List(3, -1)
  lst match {
      //只有
    case 0 :: Nil         => println("only 0")
    case x :: y :: Nil    => println(s"x: $x y: $y")
      //表示第一个元素为0，其他随意
    case 0 :: tail        => println("0 ...")
    case _                => println("something else")
  }
//匹配元祖
  val tup = (2, 3, 7)
  tup match {
    case (1, x, y)        => println(s"1, $x , $y")
    case (_, z, 5)        => println(z)
    case  _               => println("others")
  }
}
