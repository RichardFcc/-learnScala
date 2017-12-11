package scala02.Actor_Demo

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

//todo:scala中的Actor实战
//todo:需求：将多个文件作为输入，每个文件先局部计算，最终将所有的结果进行汇总输出

case class SubmitTask(filename:String) //提交任务
case class ReplyResult(result:Map[String, Int])  //封装处理结果的样例类

class WordCount  extends Actor{
  //重写act方法
  override def act(): Unit = {
    loop{
      react{
        case SubmitTask(filename)=>{
          //todo:1.1、获取文件中的数据,在这里有一个Source类，可以通过调用fromFile方法获取文件中的数据
          val data: String= Source.fromFile(filename).mkString
          //todo:1.2 按照换行符f切分 window下的换行符是\r\n  linux下是\n
          val lines: Array[String] = data.split("\r\n")
          //todo:1.3 按照空格切分
          val words: Array[String] = lines.flatMap(_.split(" "))
          //todo:1.4 每个单词记为1
          val wordAndOne: Array[(String, Int)] = words.map((_,1))
          //todo:1.5 相同单词进行合并
          val result: Map[String, Array[(String, Int)]] = wordAndOne.groupBy(_._1)
          //todo:1.6 统计相同单词出现的次数
          val finalResult: Map[String, Int] = result.mapValues(_.length)
          println(finalResult.toList)

          //结果返回
          sender ! ReplyResult(finalResult)

        }
      }
    }
  }
}

object WordCount{
  def main(args: Array[String]): Unit = {
    //定义一个set集合，存放异步返回的消息
    val replySet=new mutable.HashSet[Future[Any]]()
    //定义一个list集合，用于存放每次任务的真实结果数据
    val resultList=new mutable.ListBuffer[ReplyResult]

    //1、定义一个数组，数组里面存放文件的路径
      val files=Array("d://aa.txt","d://bb.txt","d://cc.txt")

    //2、循环files 提交任务
    for( f <- files){
      //2、创建actor
      val wc_actor = new WordCount
      //3、启动actor
      wc_actor.start()
      //异步有返回消息的
      val result: Future[Any] = wc_actor !! SubmitTask(f)

      //把每次结果存放在set集合中
      replySet +=result
    }
    //循环遍历set中内容
    while (replySet.size>0){
      //过滤出处理成功的任务
      val toComputed: mutable.HashSet[Future[Any]] = replySet.filter(_.isSet)
      //遍历完成任务的结果数据
      for(t <- toComputed){
        //获取Future中的数据
        val applyResult: Any = t.apply()
        //强转Any类型为ReplyResult
        val result: ReplyResult = applyResult.asInstanceOf[ReplyResult]
        //把数据存放到List中
        resultList.append(result)
		//从set中移除处理完成的任务数据
        replySet.remove(t)

      }


    }

    //操作最终的汇总数据
    println(resultList.toList)
    println(resultList.map(_.result).flatten.groupBy(_._1).mapValues(x=>x.foldLeft(0)(_+_._2)).toList.sortBy(_._2).reverse)



  }
}