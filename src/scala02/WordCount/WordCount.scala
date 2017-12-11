package scala02.WordCount

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.io.Source


//todo:scala中的actor实战
//todo:需求：将多个文件作为输入，每个文件先局部计算，最终将所有的结果进行汇总输出

case class SubmitTask(filename:String)//提交任务
case class ReplyResult(result:Map[String,Int])// 封装处理结果的样例类
class WordCount extends Actor{
  //重写act方法
  override def act(): Unit = {
    loop{
      react{
        case SubmitTask(filename)=>{
          //todo:1.1、获取文件中的数据,在这里有一个Source类，可以通过调用fromFile方法获取文件中的数据
          // bufferedSource是字节数组 val bufferedSource = Source.fromFile(filename) 需要mkString转换成String字符串
         //  下面只是读取文件的内容
            val data = Source.fromFile(filename).mkString
          //todo:1.2 按照换行符切分
            val lines: Array[String] = data.split("\r\n")
          //todo:1.3 按照空格切分
            val words: Array[String] = lines.flatMap(_.split(" "))
          //todo:每个单词记为1
            val wordAndOne: Array[(String, Int)] = words.map((_,1))
          //todo:1.5 相同单词进行合并
            val result: Map[String, Array[(String, Int)]] = wordAndOne.groupBy(_._1)
          //todo:统计相同单词出现的次数
          val finalResult: Map[String, Int] = result.mapValues(_.length)
           println(finalResult.toList)

          //结果返回
          sender ! ReplyResult(finalResult)


        }
      }
    }
  }
}

object  WordCount{
  def main(args: Array[String]): Unit = {
    //定义一个set集合，存放异步返回的消息
    val replySet= new mutable.HashSet[Future[Any] ]()
   // 定义一个list机会，用于存放每次任务的真实结果数据
    val resultList = new mutable.ListBuffer[ReplyResult]
    // 定义一个数组，数组里面存放文件的路径
    val files=Array("d://data//aa.txt","d://data//bb.txt","d://data//cc.txt")
    //2.循环files提交任务
    for(f <- files){
      //2.创建actor
      val wc_actor = new WordCount
      //3.启动actror
      wc_actor.start()
      // 异步有返回消息的
      val result: Future[Any] = wc_actor !! SubmitTask(f)
      //把每次结果放入set集合
      replySet +=result
    }
    //循环编列set集合
    while(replySet.size>0){
      // 过滤出处理成功的任务
      val toComputed: mutable.HashSet[Future[Any]] = replySet.filter(_.isSet)
      // 遍历完成任务的结果数据
      for(t <- toComputed){
        //获取Future的数据
        var applyResult: Any = t.apply()
        //强转Any类型为ReplyResult类型
          val result: ReplyResult = applyResult.asInstanceOf[ReplyResult]
        //把数据存放到List中
        resultList.append(result)
        //
        replySet.remove(t)
      }
    }
     //操作最终的汇总数据数据
    println(resultList.toList)
     // List(ReplyResult(Map(hadoop -> 1, hive -> 1, scala -> 2, app -> 1, richard -> 2)), ReplyResult(Map(storm -> 1, hadoop -> 2, spark -> 1, hive -> 1, scala -> 1, app -> 1, richard -> 1)), ReplyResult(Map(storm -> 1, hadoop -> 2, spark -> 1, hive -> 1, scala -> 1)))
     // resultList.map(_.result).toList
    println(resultList.map(_.result).toList)
    //  List(Map(hadoop -> 1, hive -> 1, scala -> 2, app -> 1, richard -> 2), Map(storm -> 1, hadoop -> 2, spark -> 1, hive -> 1, scala -> 1, app -> 1, richard -> 1), Map(storm -> 1, hadoop -> 2, spark -> 1, hive -> 1, scala -> 1))
    println(resultList.map(_.result).toList.flatten)
    //  List((hadoop,1), (hive,1), (scala,2), (app,1), (richard,2), (storm,1), (hadoop,2), (spark,1), (hive,1), (scala,1), (app,1), (richard,1), (storm,1), (hadoop,2), (spark,1), (hive,1), (scala,1))
    println(resultList.map(_.result).toList.flatten.groupBy(_._1))
      //每个Map(一个key,对应一个List,一个list存一个元祖)
    // Map(storm -> List((storm,1), (storm,1)), hadoop -> List((hadoop,1), (hadoop,2), (hadoop,2)), spark -> List((spark,1), (spark,1)), hive -> List((hive,1), (hive,1), (hive,1)), scala -> List((scala,2), (scala,1), (scala,1)), app -> List((app,1), (app,1)), richard -> List((richard,2), (richard,1)))
    println(resultList.map(_.result).flatten.groupBy(_._1).mapValues(x=>x.foldLeft(0)(_+_._2)).toList)
    //List((storm,2), (hadoop,5), (spark,2), (hive,3), (scala,4), (app,2), (richard,3))
    println(resultList.map(_.result).flatten.groupBy(_._1).mapValues(x=>x.foldLeft(0)(_+_._2)).toList.sortBy(_._2))
    // List((storm,2), (spark,2), (app,2), (hive,3), (richard,3), (scala,4), (hadoop,5))
    println(resultList.map(_.result).flatten.groupBy(_._1).mapValues(x=>x.foldLeft(0)(_+_._2)).toList.sortBy(_._2).reverse)
    //List((hadoop,5), (scala,4), (richard,3), (hive,3), (app,2), (spark,2), (storm,2))
 }
}
