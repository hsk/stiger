object slp {
	type id = String

	abstract sealed case class binop()
	case class Plus() extends binop
	case class Minus() extends binop
	case class Times() extends binop
	case class Div() extends binop

	abstract sealed class stm()
	case class CompoundStm(a:stm, b:stm) extends stm
	case class AssignStm(a:id, b:exp) extends stm
	case class PrintStm(a:List[exp]) extends stm
	
	abstract sealed class exp()
	case class IdExp(a:id) extends exp
	case class NumExp(a:Int) extends exp
	case class OpExp(a:exp, b:binop, c:exp) extends exp
	case class EseqExp(a:stm, b:exp) extends exp

	val prog:stm =
	  CompoundStm(
	    AssignStm(
	      "a",
	      OpExp(NumExp(5), Plus(), NumExp(3))
	    ),
	    CompoundStm(
	      AssignStm(
	        "b",
	        EseqExp(
	          PrintStm(
	            List(
	              IdExp("a"),
	              OpExp(IdExp("a"), Minus(),NumExp(1))
	            )
	          ),
	          OpExp(NumExp(10), Times(), IdExp("a"))
	        )
	      ),
	      PrintStm(List(IdExp("b")))
	    )
	  )
}
