package Parse;

object Absyn {
	type Pos = Int

	abstract case class Absyn(pos:Pos)
		abstract case class Var(override val pos:Pos) extends Absyn(pos)
			case class SimpleVar(v:Symbol,override val pos:Pos) extends Var(pos)
			case class FieldVar(v:Var, s:Symbol,override val pos:Pos) extends Var(pos)
			case class SubscriptVar(v:Var, s:Exp,override val pos:Pos) extends Var(pos)

		abstract case class Exp(override val pos:Pos) extends Absyn(pos)
			case class VarExp(v:Var, override val pos:Pos) extends Exp(pos)
			case class NilExp(override val pos:Pos) extends Exp(pos)
			case class UnitExp(override val pos:Pos) extends Exp(pos)
			case class IntExp(i:Int, override val pos:Pos) extends Exp(pos)
			case class StringExp(str:String, override val pos:Pos) extends Exp(pos)
			case class CallExp(func:Symbol, args:List[Exp],override val pos:Pos) extends Exp(pos)
			case class OpExp(left:Exp, oper:Oper, right:Exp, override val pos:Pos) extends Exp(pos)
			case class RecordExp(fields:List[(Symbol,Exp,Int)], 
			typ:Symbol, override val pos:Pos) extends Exp(pos)
			case class SeqExp(exp:List[Exp], override val pos:Pos) extends Exp(pos)
			case class IfExp(test:Exp, then1:Exp, else1:Exp,override val pos:Pos) extends Exp(pos)
			case class WhileExp(test:Exp, body:Exp,override val pos:Pos) extends Exp(pos)
			case class ForExp(var1:Symbol, var escape:Boolean, lo:Exp, hi:Exp, body:Exp, override val pos:Pos) extends Exp(pos)
			case class BreakExp(override val pos:Pos) extends Exp(pos)
			case class LetExp(decs:List[Dec], body:Exp, override val pos:Pos) extends Exp(pos)
			case class ArrayExp(typ:Symbol, size:Exp, init:Exp, override val pos:Pos) extends Exp(pos)
			case class AssignExp(typ:Var, init:Exp, override val pos:Pos) extends Exp(pos)
		abstract class Dec(override val pos:Pos) extends Absyn(pos)
			case class FunctionDec(
				name:Symbol, params:List[TyField], 
				result: Symbol,
				body: Exp, override val pos:Pos) extends Dec(pos)
			case class VarDec(name:Symbol, var escape:Boolean, 
				typ: Symbol, init:Exp, override val pos:Pos) extends Dec(pos)
			case class TypeDec(name:Symbol, ty:Ty, override val pos:Pos) extends Dec(pos)

		abstract case class Ty(override val pos:Pos) extends Absyn(pos)
			case class NameTy(sym:Symbol, override val pos:Pos) extends Ty(pos)
			case class RecordTy(l:List[TyField], override val pos:Pos) extends Ty(pos)
			case class ArrayTy(sym:Symbol, override val pos:Pos) extends Ty(pos)
		abstract case class Oper(override val pos:Pos) extends Absyn(pos)
			case class PlusOp(override val pos:Pos) extends Oper(pos)
			case class MinusOp(override val pos:Pos) extends Oper(pos)
			case class TimesOp(override val pos:Pos) extends Oper(pos)
			case class DivideOp(override val pos:Pos) extends Oper(pos)
			case class EqOp(override val pos:Pos) extends Oper(pos)
			case class NeqOp(override val pos:Pos) extends Oper(pos)
			case class LtOp(override val pos:Pos) extends Oper(pos)
			case class LeOp(override val pos:Pos) extends Oper(pos)
			case class GtOp(override val pos:Pos) extends Oper(pos)
			case class GeOp(override val pos:Pos) extends Oper(pos)
		case class TyField(var escape:Boolean, name:Symbol, typ:NameTy, override val pos:Pos) extends Absyn(pos)
		def addList(a:Exp,b:List[Exp]):List[Exp]=a::b
		def addList(a:(Symbol, Exp),b:List[(Symbol, Exp)]):List[(Symbol, Exp)]=a::b
		def list():List[Exp] = List[Exp]()
		def list(a:Exp):List[Exp] = List(a)
		def list(a:(Symbol,Exp)):List[(Symbol,Exp)] = List(a)

		def listTyFields():List[TyField] = List[TyField]()
		def list(a:TyField):List[TyField] = List(a)
		def addList(a:TyField,b:List[TyField]):List[TyField]=a::b
		

		def listDec():List[Dec] = List[Dec]()
		def addList(a:Dec,b:List[Dec]):List[Dec]=a::b
}
