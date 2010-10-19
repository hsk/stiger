object Parse {
	def parse(filename:String) {
		val file = new java.io.FileInputStream(filename)
		def get(a) = TextIO.input(file, a)
		val lexer = Mlex.makeLexer(get(_))
		def do_it() {
			val t = lexer()
			println(t)
			if(t.substring(0,3)=="EOF") Unit else do_it()
		}
		do_it()
		file.close()
	}
}
