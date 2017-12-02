/**
  * Created by ss on 09.09.16.
  */
object ProjectEulerScala extends App {
  println(problem1(0 until 1000))

  def problem1(range:Range):Int = range.filter(n => n % 3 == 0 || n % 5 == 0).sum


}
