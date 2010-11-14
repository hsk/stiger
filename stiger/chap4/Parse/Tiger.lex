package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}       


%%
" "	{}
(\n|\r\n|\r)	{newline();}
"function" { return tok(sym.FUNCTION, null); }
[0-9]+ {return tok(sym.INT, new Integer(yytext()));}
">" { return tok(sym.GT, null); }
"/" { return tok(sym.DIVIDE, null); }
":" { return tok(sym.COLON, null); }
"else" { return tok(sym.ELSE, null); }
"|" { return tok(sym.OR, null); }
"nil" { return tok(sym.NIL, null); }
"do" { return tok(sym.DO, null); }
">=" { return tok(sym.GE, null); }
"error" { return tok(sym.error, null); }
"<" { return tok(sym.LT, null); }
"of" { return tok(sym.OF, null); }
"-" { return tok(sym.MINUS, null); }
"array" { return tok(sym.ARRAY, null); }
"type" { return tok(sym.TYPE, null); }
"for" { return tok(sym.FOR, null); }
"to" { return tok(sym.TO, null); }
"*" { return tok(sym.TIMES, null); }
"," { return tok(sym.COMMA, null); }
"<=" { return tok(sym.LE, null); }
"in" { return tok(sym.IN, null); }
"end" { return tok(sym.END, null); }
":=" { return tok(sym.ASSIGN, null); }
\"[^\"]*\" { return tok(sym.STRING, null); }
"\." { return tok(sym.DOT, null); }
"(" { return tok(sym.LPAREN, null); }
")" { return tok(sym.RPAREN, null); }
"if" { return tok(sym.IF, null); }
";" { return tok(sym.SEMICOLON, null); }
"while" { return tok(sym.WHILE, null); }
"[" { return tok(sym.LBRACK, null); }
"]" { return tok(sym.RBRACK, null); }
"<>" { return tok(sym.NEQ, null); }
"var" {return tok(sym.VAR, null);}
"break" { return tok(sym.BREAK, null); }
"&" { return tok(sym.AND, null); }
"+" { return tok(sym.PLUS, null); }
"{" { return tok(sym.LBRACE, null); }
"}" { return tok(sym.RBRACE, null); }
"let" { return tok(sym.LET, null); }
"then" { return tok(sym.THEN, null); }
[a-zA-Z][a-zA-Z0-9]* { return tok(sym.ID, yytext()); }
"=" { return tok(sym.EQ, null); }
"." { err("Illegal character: "+yytext());}

