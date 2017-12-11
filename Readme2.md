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
             scala>  def  m2(x:Int,y:Int)=x+y（
                          m2: (x: Int, y: Int)Int
             scala>  def  f2=(x:Int,y:Int)=>x+y（无法转成函数）
                          f2: (Int, Int) => Int

        定义函数
               val func: Int => String = { x => x.toString }

               val func = (x: Int) => x.toString
1.8.1 定义方法

![](https://i.imgur.com/LBYDp65.png)

方法的返回值类型可以不写，编译器可以自动推断出来，但是对于递归函数，必须指定返回类型
![](https://i.imgur.com/W1bjV52.png)

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

![](https://i.imgur.com/qzd5uJT.png)

         val func1=(x:Int,y:Int)=>x*y
              方法体用=>表示                                                                                                                                                                                                                                                                                              
         func1: (Int, Int) => Int = <function2>
         <function2>表示两个类型参数,scala中最多允许22个参数
1.8.3 方法与函数的区别

![](https://i.imgur.com/BOjY4XH.png)
        
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

![](https://i.imgur.com/paMcGWw.png)

           将方法转换成函数，只需要在方法的后面加上一个下划线

		def m2(x:Int,y:Int)=x+y
		m2: (x: Int, y: Int)Int
		
		def f2=(x:Int,y:Int)=>x+y
		f2: (Int, Int) => Int
		
		  m2 _方法转成函数
		res11: (Int, Int) => Int = <function2>
二 数组、映射、元组、集合
2.1 数组
    2.1.1 定长数组和变长数组
         
         （1）定长数组定义格式：
              val arr=new Array[T](数组长度)
              val arr=Array(1,2,3,4,5)
              val arr1=new Array[Int](5)
              arr(0):取数组第一个数
              arr(0)=1：给下标为1的数组赋1
         （2）变长数组定义格式：
              val arr = ArrayBuffer[T]() 
              注意需要导包：
              import scala.collection.mutable.ArrayBuffer
              val arr2=ArrayBuffer[Int]()
              arr2 +=1(向数组中添加一个元素){1}
              arr2+=(2,3,4,5)(向数组中{1}追加{2，3，4，5}形成{1，2，3，4，5}
              var arr3=Array(6,7)
              arr ++arr3 () 形成一个新的数组
                                 {1，2，3，4，5，6，7}
            
              val arr4=ArrayBuffer[Int]()
              arr4+=10
              arr2++ arr4将arr4追加到arr2中{12345 10}
            
              arr5=arr2+arr3
arr.(很多方法)
     arr.remove(移除的下标)
     arr.insert(开始插入的位置，数值1，数值2)
     arr.reverse(翻转)     
     arr.filter(_ %2==0)表示过滤   _:表示所有集合或者元素 
         .map(_*10)表示对每个元素进行操作
     arr.sum
     arr.map
     arr.min
     arr.sorted:升序排列
         sortBY：根据主键排序
         sortWith:根据字段排序

    2.1.2 遍历数组
          1.增强for循环    0 to 10
          2.好用的until会生成脚标，0 until 10 包含0不包含10
            var arr6=Array(2,4,15,3,8)
          for(i <- 0 until arr6.length) println(arr6(i))
			2
			4
			15
			3
			8
2.2 映射 在Scala中，把哈希表这种数据结构叫做映射（Map集合）
    2.2.1 构建映射格式
          （1） 1、val map=Map(键->值，键->值....)  immutable:定长mutable：不定长
                  val map=Map("zhangsan" -> 20 ,"lisi" -> 30,"wangwu" -> 40 )
                      map("key")获取值
               2、利用元组构建  val map=Map((键，值),(键，值),(键，值)....) 
                 val map1=Map(("zhangsan",20),("lisi",30),("wangwu",40))一个元素代表一个tuple

                 map1.getOrElse("lisi",100):表示有就取出来，没有这个key就给这个key一个默认值
                 map1+=("maliu"->25)：添加操作
   总结：
	  注意：在Scala中，有两种Map，一个是immutable包下的Map，该Map中的内容不可变；另一个是mutable包下的Map，该Map中的内容可变
	  注意：通常我们在创建一个集合是会用val这个关键字修饰一个变量（相当于java中的final），那么就意味着该变量的引用不可变，该引用中的内容是不是可变，取决于这个引用指向的集合的类型
2.3 元组 
    映射是K/V对偶的集合，对偶是元组的最简单形式，元组可以装着多个不同类型的值。
   2.3.1 创建元组
       （1）元组是不同类型的值的聚集；对偶是最简单的元组。
       （2）元组表示通过将不同的值用小括号括起来，即表示元组。
     创建元组格式：
     val tuple=(元素,元素...)
     val tuple=("scala",3.14,100)

    2.3.2 获取元组的值
         tuple: (String, Double, Int) = (scala,3.14,100)
      tuple._n--：表示取tuple里的第几个元素，从1开始

    2.3.4  将对偶的集合转换成映射
        val arr=Array(("zhangsan",20),("lisi",30),("wangwu",50))如何转换成Map

        arr.toMap   Map(zhangsan -> 20, lisi -> 30, wangwu -> 50)

    2.3.5 拉链操作
        1.使用zip命令可以将多个值绑定在一起
          val names=Array("zhangsan","lisi")
          val ages=Array(20,30)
          names.zip(ages)
          Array[(String, Int)] = Array((zhangsan,20), (lisi,30))

          val ages=Array(20,30,40)
          names.zip(ages)
          Array[(String, Int)] = Array((zhangsan,20), (lisi,30))
        2.如果其中一个元素的个数比较少，可以使用zipAll用默认的元素填充
          names.zipAll(ages,"wangwu",50)           

          注意：如果两个数组的元素个数不一致，拉链操作后生成的数组的长度为较小的那个数组的元素个数

2.3集合 
   Scala的集合有三大类：序列Seq、Set、映射Map，所有的集合都扩展自Iterable特质。
   在Scala中集合有可变（mutable）和不可变（immutable）两种类型，immutable类型的集合初始化后就不能改变了（注意与val修饰的变量进行区别）

  2.3.1 List
       (1)不可变的序列 import scala.collection.immutable._
        val list=List(1,2,3,4,5)
        list.head=1输出第一个元素
        list.tail=(2,3,4,5)输出除了第一个的元素
        
       val list1=1::Nil（将1追加到一个空的集合中）{1}
     
        list1.++(list) 从list1的尾部追加另一个列表{112345}
        list1.+:(10) 想list1头部添加一个元素
        list0 ++=list1 将list1的元素追加到list0
 
        
        (2)可变的序列 import scala.collection.mutable._
         val list2=ListBuffer[Int](1,2,3,4)
          list2+=5,向自己加入新的元素
          list2.append():可以追加元素，也可以追加元素
          list2--list1:删除单个元素
          list2--=list1：删除list列表元素
          将可变list转换成不可变的list：list.toList
          list.toArray
list常用的操作符：

		++[B](that: GenTraversableOnce[B]): List[B] 从列表的尾部添加另外一个列表
		++: [B >: A, That](that: collection.Traversable[B])(implicit bf: CanBuildFrom[List[A], B, That]): That 在列表的头部添加一个列表
		+: (elem: A): List[A] 在列表的头部添加一个元素
		:+ (elem: A): List[A] 在列表的尾部添加一个元素
		:: (x: A): List[A]     在列表的头部添加一个元素
		::: (prefix: List[A]): List[A] 在列表的头部添加另外一个列表
 2.3.1 Set
         set.num set.max set.tail  set.size    
        set $ set1 两个集合取交集 
        set ++ set1 两个集合取并集
        set -- set1 取set中单独拥有的元素
       (1)不可变的Set   import scala.collection.immutable._
	Set代表一个没有重复元素的集合；将重复元素加入Set是没有用的，而且 Set 是不保证插入顺序的，即 Set 中的元素是乱序的。
	定义：val set=Set(元素,元素,.....)
       (2)可变的Set  import scala.collection.mutable._
三 类、对象、继承、特质
3.1 类
   3.1.1 类的定义

      package com.ricahrd
	
	class Student (val name: String,val age:Int) {
	  println("主构造器执行")
	  val address = "bj"
	  val gender="male"
	
	  def this(name: String, age: Int, address: String,gender:String) {
	    this(name,age)
	    println("辅助构造器被执行")
	  }
	  def this(name: String, age: Int, address: String) {
	 this(name,age,address,gender)
	    println("辅助构造器被执行")
	      }
	}
	object Student{
	  def main (args: Array[String] ): Unit = {
	        //val richard = new Student("richard",24)
	        val richard = new Student("richard",24,"bj")
	}
	}
    
	
3.2 Scala面向对象编程之对象
   3.2.1 Scala中的object
		
object 相当于 class 的单个实例，通常在里面放一些静态的 field 或者 method；在Scala中没有静态方法和静态字段，但是可以使用object这个语法结构来达到同样的目的
		1.存放工具方法和常量
		2.高效共享单个不可变的实例
		3.单例模式

		举例说明：
        
        com.richard
        import scala.collection.mutable.ArrayBuffer

	class Session{}
	object SingletonDemo {
	  def main(args: Array[String]) {
	    //单例对象，不需要new，用【类名.方法】调用对象中的方法
	    val session = SessionFactory.getSession()
	    println(session)
	  }
	}
	object SessionFactory{
	  //该部分相当于java中的静态块
	  var counts = 5
	  val sessions = new ArrayBuffer[Session]()
	  while(counts > 0){
	    sessions += new Session
	    counts -= 1
	  }
	  //在object中的方法相当于java中的静态方法
	  def getSession(): Session ={
	    sessions.remove(0)
	  }
	}
3.2.2.  Scala中的伴生对象
		如果有一个class文件，还有一个与class同名的object文件，那么就称这个object是class的伴生对象，class是object的伴生类；
		伴生类和伴生对象必须存放在一个.scala文件中；
		伴生类和伴生对象的最大特点是，可以相互访问；
		举例说明：

        //伴生类
		class Dog {
		  val id = 1
		  private var name = "itcast"
		  def printName(): Unit ={
		    //在Dog类中可以访问伴生对象Dog的私有属性
		    println(Dog.CONSTANT + name )
		  }
		}
		//伴生对象
		object Dog {
		  //伴生对象中的私有属性
		  private val CONSTANT = "汪汪汪 : "
		  def main(args: Array[String]) {
		    val p = new Dog
		    //访问私有的字段name
		    p.name = "123"
		    p.printName()
		  }
		}
		//执行结果 汪汪汪 : 123
3.2.3  Scala中的apply方法
		object 中非常重要的一个特殊方法，就是apply方法；
		apply方法通常是在伴生对象中实现的，其目的是，通过伴生类的构造函数功能，来实现伴生对象的构造函数功能；
		通常我们会在类的伴生对象中定义apply方法，当遇到类名(参数1,...参数n)时apply方法会被调用；
		在创建伴生对象或伴生类的对象时，通常不会使用new class/class() 的方式，而是直接使用 class()，隐式的调用伴生对象的 apply 方法，这样会让对象创建的更加简洁；
		举例说明： 
       
	 /**
	 *  Array 类的伴生对象中，就实现了可接收变长参数的 apply 方法，
	 * 并通过创建一个 Array 类的实例化对象，实现了伴生对象的构造函数功能
	 */
	// 指定 T 泛型的数据类型，并使用变长参数 xs 接收传参，返回 Array[T] 数组
	// 通过 new 关键字创建 xs.length 长的 Array 数组
	// 其实就是调用Array伴生类的 constructor进行 Array对象的初始化
	//  def apply[T: ClassTag](xs: T*): Array[T] = {
	//    val array = new Array[T](xs.length)
	//    var i = 0
	//    for (x <- xs.iterator) { array(i) = x; i += 1 }
	//    array
	//  }
	
	object ApplyDemo {
	  def main(args: Array[String]) {
	    //调用了Array伴生对象的apply方法
	    //def apply(x: Int, xs: Int*): Array[Int]
	    //arr1中只有一个元素5
	    val arr1 = Array(5)
	    //new了一个长度为5的array，数组里面包含5个null
	    var arr2 = new Array(5)
	    println(arr1.toBuffer)
	  }
	}
3.2.4 Scala中的main方法
		同Java一样，如果要运行一个程序，必须要编写一个包含 main 方法的类；
		在 Scala 中，也必须要有一个 main 方法，作为入口；
		Scala 中的 main 方法定义为 def main(args: Array[String])，而且必须定义在 object 中；
		除了自己实现 main 方法之外，还可以继承 App Trait，然后，将需要写在 main 方法中运行的代码，直接作为 object 的 constructor 代码即可，而且还可以使用 args 接收传入的参数；
		案例说明：

        /1.在object中定义main方法
		object Main_Demo1 {
		  def main(args: Array[String]) {
		    if(args.length > 0){
		      println("Hello, " + args(0))
		    }else{
		      println("Hello World!")
		    }
		  }
		}
		//2.使用继承App Trait ,将需要写在 main 方法中运行的代码
		// 直接作为 object 的 constructor 代码即可，
		// 而且还可以使用 args 接收传入的参数。
		
		object Main_Demo2 extends App{
		    if(args.length > 0){
		      println("Hello, " + args(0))
		    }else{
		      println("Hello World!")
		    }

3.2.5 Scala中继承(extends)的概念
			Scala 中，让子类继承父类，与 Java 一样，也是使用 extends 关键字；
			继承就代表，子类可继承父类的 field 和 method ，然后子类还可以在自己的内部实现父类没有的，子类特有的 field 和method，使用继承可以有效复用代码；
			子类可以覆盖父类的 field 和 method，但是如果父类用 final 修饰，或者 field 和 method 用 final 修饰，则该类是无法被继承的，或者 field 和 method 是无法被覆盖的。
			private 修饰的 field 和 method 不可以被子类继承，只能在类的内部使用；
			field 必须要被定义成 val 的形式，才能被继承，并且还要使用 override 关键字。 因为 var 修饰的 field 是可变的，在子类中可直接引用被赋值，不需要被继承；即 val 修饰的才允许被继承，var 修饰的只允许被引用。继承就是改变、覆盖的意思。
			Java 中的访问控制权限，同样适用于 Scala
				  类内部本包子类外部包
			
             public	√	√	√	√
							
			protected	√	√	√	×
							
			default	√	√	×	×
							
			private	√	×	×	×

3.2.6 面向对象编程之继承
      如果实例化了子类的对象，但是将其赋予了父类类型的变量，在后续的过程中，又需要将父类类型的变量转换为子类类型的变量，应该如何做？

		首先，需要使用 isInstanceOf 判断对象是否为指定类的对象，如果是的话，则可以使用 asInstanceOf 将对象转换为指定类型；
		注意： p.isInstanceOf[XX] 判断 p 是否为 XX 对象的实例；P.asInstanceOf[XX] 把 p 转换成 XX 对象的实例
		注意：如果没有用 isInstanceOf 先判断对象是否为指定类的实例，就直接用 asInstanceOf 转换，则可能会抛出异常；
		注意：如果对象是 null，则 isInstanceOf 一定返回 false， asInstanceOf 一定返回 null；
				
		Scala与Java类型检查和转换
		
		Scala	Java
		obj.isInstanceOf[C]	obj instanceof C
		obj.asInstanceOf[C]	(C)obj
		classOf[C]	C.class
