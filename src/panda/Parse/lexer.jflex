/* -*-Mode: java-*- */

package panda.Parse;

import panda.ErrorMsg.ErrorMsg;
import panda.ErrorMsg.Loc;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%public
%final
%class PandaLex
%implements panda.Parse.PandaSym
%line
%column
%cupsym panda.Parse.PandaSym
%cup

%eofval{
    return tok(EOF);
%eofval} 

%{
    private ComplexSymbolFactory symbolFactory;
    private String unit;
    private ErrorMsg errorMsg;
    
    public PandaLex(String unit, java.io.Reader input, ComplexSymbolFactory symbolFactory, ErrorMsg errorMsg) {
        this(input);
        this.symbolFactory = symbolFactory;
        this.unit = unit;
        this.errorMsg = errorMsg;
    }
    
    public String getUnit() {
        return unit;
    }
     
    private java_cup.runtime.Symbol tok(int terminalcode) {
        return tok(terminalcode, null);
    }
    
    private java_cup.runtime.Symbol tok(int terminalcode, Object val) {
      return symbolFactory.newSymbol(
        terminalNames[terminalcode],
        terminalcode,
        new Location(yyline+1, yycolumn+1),
        new Location(yyline+1, yycolumn+1+yylength()),
        val);
    }
        
    private void error(String msg) {
        errorMsg.error(
          new Loc(new Location(unit, yyline+1, yycolumn+1),
                  new Location(unit, yyline+1, yycolumn+1+yylength())),
          msg);
    }
%}

%%

<YYINITIAL>{
[ \t\f\n\r]+          { /* skip */ }

"//" .*               { /* skip */ }


0 | [1-9][0-9]*       { return tok(LITINT, new Long(yytext())); }

true | false       { return tok(LITBOOL, new Boolean(yytext())); }

if       { return tok(IF); }
then       { return tok(THEN); }
else       { return tok(ELSE); }

.                     { error("illegal character: [" + yytext() + "]"); }
}
