/**
  * Created by ss on 09.09.16.
  */
import org.scalatest.FunSpec

class ProjectEulerScalaTest extends FunSpec {
  describe("Problem 1"){
    describe("The given solution"){
      it("0 until 10 should sum to 23"){
        assert(ProjectEulerScala.problem1(0 until 10) == 23)
      }
    }
  }
}
