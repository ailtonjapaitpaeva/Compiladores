/* -*-Mode: java-*- */

package Parse;

import ErrorMsg.Source;
import ErrorMsg.ErrorMsg;
import ErrorMsg.Loc;

import static Parse.PandaSym.*;

%%

%public
%final
%class PandaLex
%char
%cupsym Parse.PandaSym
%cup

%{
    private Source src;
    private ErrorMsg errorMsg;
    
    public PandaLex(java.io.Reader input, Source src, ErrorMsg errorMsg)
    {
        this(input);
        this.src = src;
        this.errorMsg = errorMsg;
    }
    
    public Source getSource()
    {
        return src;
    }
    
    private java_cup.runtime.Symbol tok(int typ, Object val)
    {
        return new java_cup.runtime.Symbol(typ, yychar, yychar + yytext().length() - 1, val);
    }

    private java_cup.runtime.Symbol tok(int typ)
    {
        return tok(typ, null);
    }
    
    private void error(String msg)
    {
        errorMsg.error(new Loc(src, yychar, yychar + yytext().length() - 1), msg);
    }
%}

%%

<YYINITIAL>{
[ \t\f\r]+            { /* skip */ }

\n                    { src.newline(yychar); }

"//" .*               { /* skip */ }



.                     { error("illegal character: " + yytext()); }
}
