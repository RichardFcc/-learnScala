Scala总结
一 基础
  1.1.声明变量
    scala 定义变量可以不用声明类型，scala可以自动识别。
          var aa:String="hello"
    等价于 var aa="hello"   
  1.2.常用类型
    Scala和Java一样，有7种数值类型
      Byte、Char、Short、Int、Long、Float和Double（无包装类型）和一个Boolean类型
  1.3.条件表达式：比较简洁，定义变量时加上if else判断条件

          var y=if(x>1) 1 else -1
          y: Int = -1
          var y=if(x>1) 1
          y: AnyVal = ()
  1.4.总结Any:相当于java中 的object，它里面有两个子类，一个是          AnyVal(),一个是AnyRef()
    AnyVal()，下面有这样几个子类：（九个子类）
         (数值类型)Byte、Char、Short、Int、Long、Float和Double
          Boolean 和unit(非数值类型)--uint有一个实例”()”
    AnyRel(引用类型)、

  1.5.块表达式：定义变量时用{}包含一系列表达式，其中块的最后一个表达式的值就是块的值
     var z={
     | if(x>0) 0 else if(x=1) 1 else 2
     | }
  1.6 循环：在scala中有for循环和while循环，用for循环比较多
for循环语法结构：for (i <- 表达式/数组/集合) 
       var  abc=1 to 10（scala 特殊的一个集合，返回下面的一个区间，可以看成一个集合）
res1: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  
     for(i <- abc) println(i)
				1
				2
				3
				4
				5
				6
				7
				8
				9
				10
  
    var arr=Array("hadoop","hive","spark")
     
       for(j <- arr) println(j)
             hadoop
              hive
             spark
 
    for(i <- 1 to 3;j <- 1 to 3 if i!=j) println(10*i+j)
				12
				13
				21
				23
				31
				32
   通过yield关键字 将一个集合遍历出来的元素通过for循环生成新的集合（容器）

     for(i <- 1 to 3) yield i*10

     res5: scala.collection.immutable.IndexedSeq[Int] = Vector(10, 20, 30)
  
  1.7 调用方法和函数 Scala中的+ - * / %等操作符的作用与Java一样，位操作符 & | ^ >> <<也一样。只是有一点特别的：这些操作符实际上是方法。例如：
             a + b
        是如下方法调用的简写：
             a.+(b)
        a 方法 b可以写成 a.方法(b)

  1.8 定义方法和函数

       定义方法
             scala>  def  m2(x:Int,y:Int)=x+y
                          m2: (x: Int, y: Int)Int
             scala>  def  f2=(x:Int,y:Int)=>x+y
                          f2: (Int, Int) => Int

        定义函数
               val func: Int => String = { x => x.toString }

               val func = (x: Int) => x.toString


    1.8.1 定义方法

         def m1(x:Int,y:Int):Int=x*y
                                  方法的表达式
            m1: (x: Int, y: Int)Int

    递归定义
           
          def m2(x:Int):Int={
     | if(x==1){
     | 1
     | }else{
     | m2(x-1)*x
     | }
     | }
   没用定义返回值类型报的错误
 <console>:16: error: recursive method m2 needs result type
       m2(x-1)*x

    1.8.2 定义函数

         val func1=(x:Int,y:Int)=>x*y
              方法体用=>表示                                                                                                                                                                                                                                                                                              
         func1: (Int, Int) => Int = <function2>
         <function2>表示两个类型参数,scala中最多允许22个参数
   
    1.8.3 方法与函数的区别
        
		在函数式编程语言中，函数是“头等公民”，它可以像 任何其他数据类型一样被传递和操作，函数是一个对象，继承自FuctionN，
		函数对象有apply,,curried,,toString,tupled这些方法。
		而方法不具有这些特性。如果想把方法转换成一个函数，可以用方法名跟上下划线的方式.
     func1.(函数是一个对象，可以点的形式)
       apply   curried   toString   tupled
     m1.m1(方法就是一方法，不能.的形式，只能被调用。但可以调用函数)

       def mm(f:(Int,Int)=>Int)=f(2,6)
       mm: (f: (Int, Int) => Int)Int
                定义一个方法可以将函数当成参数传递
       val ff=(x:Int,y:Int)=>x*y  
                定义一个函数作为上述方法的函数传参
       mm(ff)--这样就直接可以进行   方法进行函数的调用

   1.8.4 将方法转换成函数（神奇的下划线）
           将方法转换成函数，只需要在方法的后面加上一个下划线
		def m2(x:Int,y:Int)=x+y
		m2: (x: Int, y: Int)Int
		
		def f2=(x:Int,y:Int)=>x+y
		f2: (Int, Int) => Int
		
		  m2 _方法转成函数
		res11: (Int, Int) => Int = <function2>
          
