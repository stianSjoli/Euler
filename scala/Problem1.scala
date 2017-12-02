/**
  * Created by ss on 09.09.16.
  */
import akka.routing.FromConfig
import akka.actor.{Actor, ActorRef, ActorSystem, FSM, Props}
import scala.concurrent.duration._

object AkkaProblem1 extends App {
  val system = ActorSystem("problem1")
  val master = system.actorOf(Props[Master], "master")
  master ! (1 until 10)
}

sealed trait State
case object Idle extends State
case object Processing extends State
case object Complete extends State

sealed trait Data
case object Uninitialized extends Data
case class Elements(range:Range, toProcess:Int, sum:Int) extends Data

class Master extends FSM[State, Data]{

  val router: ActorRef = context.actorOf(FromConfig.props(Props[Worker]), "router")

  when(Idle){
    case Event(range:Range, _) =>{
      goto(Processing) using Elements(range, range.size-1, 0)
    }
  }
  when(Processing){
    case Event(n:Int, Elements(range, 0, sum)) => {
      goto(Complete) using Elements(e.range, 0, e.sum + n)
    }
    case Event(n:Int, Elements(range, count, sum)) => {
      stay using Elements(e.range, e.toProcess-1, e.sum + n)
    }
    case Event(None, e:Elements) => {
      stay using Elements(e.range, e.toProcess-1, e.sum)
    }
  }

  when(Complete, stateTimeout = 1 second){
    case Event(StateTimeout, _) => goto(Idle) using Uninitialized
  }

  startWith(Idle, Uninitialized)
  initialize()

  onTransition{
    case Idle -> Processing => {
      val data = nextStateData.asInstanceOf[Elements]
      data.range foreach {number => router ! number}
    }
    case Processing -> Complete => println(nextStateData.asInstanceOf[Elements].sum)
  }
}
class Worker extends Actor{
  def receive: Receive = {
    case n:Int if n % 3 == 0 => sender ! n
    case n:Int if n % 5 == 0 => sender ! n
    case n:Int => sender() ! None
    case _ =>
  }
}
