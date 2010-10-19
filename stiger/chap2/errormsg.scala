abstract class ERRORMSG extends Error(
    var anyErrors : Boolean,
    var fileName : String,
    var lineNum : Int,
    var linePos : List[Int],
    val sourceStream : TextIO.instream
) {
    def error(a:Int, b:String):Unit
    def impossible(a:String):Unit   // raises Error
    def reset (): Unit
}

class ErrorMsg() extends ERRORMSG (
	false,
	"",
	1,
	List(1),
	TextIO.stdIn
) {
	def reset() {
		anyErrors = false;
		fileName = "";
		lineNum = 1;
		linePos = List(1);
		sourceStream = TextIO.stdIn
	}

	def error(pos:Int, msg:string) {
		def look(e) = e match {
			case (a::rest, n) =>
				if (a < pos) {
					println (":" + n + "." + (pos - a))
				} else {
					look(rest, n - 1)
				}
			  case (_, _) => print "0.0"; anyErrors = true
		}
		print(fileName)
		look(linePos, lineNum)
		println(":" + msg)
	}

	def reset(msg:String) {
		println("Error: Compiler bug: " + msg)
		throw new Error()
	}

}
