package org.example.microjava;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.example.microjava.psi.MicroJavaTypes;
import org.example.microjava.psi.MicroJavaSingleValueTokens;
import org.example.microjava.psi.MicroJavaKeywordTokens;

import java.util.Stack;
%%

%class MicroJavaLexer
%implements FlexLexer

%{
    private static final class State {
        final int lBraceCount;
        final int state;

        public State(int state, int lBraceCount) {
            this.state = state;
            this.lBraceCount = lBraceCount;
        }

        @Override
        public String toString() {
            return "yystate = " + state + (lBraceCount == 0 ? "" : "lBraceCount = " + lBraceCount);
        }
    }

    private final Stack<State> states = new Stack<State>();
    private int lBraceCount;

    private int commentStart;
    private int commentDepth;

    private void pushState(int state) {
        states.push(new State(yystate(), lBraceCount));
        lBraceCount = 0;
        yybegin(state);
    }

    private void popState() {
        State state = states.pop();
        lBraceCount = state.lBraceCount;
        yybegin(state.state);
    }

    private IElementType commentStateToTokenType(int state) {
        switch (state) {
            case COMMENT:
                return MicroJavaTypes.COMMENT;
            default:
                throw new IllegalArgumentException("Unexpected state: " + state);
        }
    }
%}

%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%xstate COMMENT

DIGIT= [0-9]
WHITE_SPACE_CHAR=[\ \n\r\t\f]
LETTER=[:letter:]

IDENT={LETTER}({LETTER}|{DIGIT}|"_")*
NUMBER={DIGIT}+
CHAR_CONST = "'"[^\\']"'" | "'"(\\n|\\t|\\r|\\f|\\b|\\'|\\\\|\ )"'"

%state WAITING_VALUE

%%

// nested comments
"/**/" {
          return MicroJavaTypes.COMMENT;
      }
"/*" {
          pushState(COMMENT);
          commentDepth = 0;
          commentStart = getTokenStart();
      }

<COMMENT> {
      "/*" {
          commentDepth++;
      }
      <<EOF>> {
          int state = yystate();
          popState();
          zzStartRead = commentStart;
          return commentStateToTokenType(state);
      }
      "*/" {
          if (commentDepth > 0){
              commentDepth--;
          } else {
              int state = yystate();
              popState();
              zzStartRead = commentStart;
              return commentStateToTokenType(state);
          }
      }
      [\s\S] {}
}

({WHITE_SPACE_CHAR})+ { return TokenType.WHITE_SPACE; }
{NUMBER} {return MicroJavaTypes.NUMBER;}
{CHAR_CONST} {return MicroJavaSingleValueTokens.CHAR_CONST;}

"program" {return MicroJavaKeywordTokens.PROGRAM;}
"class" {return MicroJavaKeywordTokens.CLASS_TYPE;}
"if" {return MicroJavaKeywordTokens.IF;}
"else" {return MicroJavaKeywordTokens.ELSE;}
"while" {return MicroJavaKeywordTokens.WHILE;}
"read" {return MicroJavaKeywordTokens.READ;}
"print" {return MicroJavaKeywordTokens.PRINT;}
"return" {return MicroJavaKeywordTokens.RETURN;}
"break" {return MicroJavaKeywordTokens.BREAK;}
"void" {return MicroJavaKeywordTokens.VOID;}
"final" {return MicroJavaKeywordTokens.FINAL;}
"new" {return MicroJavaKeywordTokens.NEW;}
"int" {return MicroJavaKeywordTokens.INT;}
"char" {return MicroJavaKeywordTokens.CHAR;}

{IDENT} {return MicroJavaTypes.IDENT;}

"+" {return MicroJavaSingleValueTokens.PLUS;}
"-" {return MicroJavaSingleValueTokens.MINUS;}
"*" {return MicroJavaSingleValueTokens.MUL;}
"/" {return MicroJavaSingleValueTokens.DIV;}
"%" {return MicroJavaSingleValueTokens.MOD;}
"**" {return MicroJavaSingleValueTokens.EXP;}
"++" {return MicroJavaSingleValueTokens.INC;}
"--" {return MicroJavaSingleValueTokens.DEC;}
"==" {return MicroJavaSingleValueTokens.EQ;}
"!=" {return MicroJavaSingleValueTokens.NEQ;}
"<" {return MicroJavaSingleValueTokens.LT;}
"<=" {return MicroJavaSingleValueTokens.LTE;}
">" {return MicroJavaSingleValueTokens.GT;}
">=" {return MicroJavaSingleValueTokens.GTE;}
"&&" {return MicroJavaSingleValueTokens.AND;}
"||" {return MicroJavaSingleValueTokens.OR;}
"=" {return MicroJavaSingleValueTokens.ASSIGN;}
"+=" {return MicroJavaSingleValueTokens.PLUS_ASSIGN;}
"-=" {return MicroJavaSingleValueTokens.MINUS_ASSIGN;}
"*=" {return MicroJavaSingleValueTokens.MUL_ASSIGN;}
"/=" {return MicroJavaSingleValueTokens.DIV_ASSIGN;}
"%=" {return MicroJavaSingleValueTokens.MOD_ASSIGN;}
"(" {return MicroJavaSingleValueTokens.LPAREN;}
")" {return MicroJavaSingleValueTokens.RPAREN;}
"{" {return MicroJavaSingleValueTokens.LBRACE;}
"}" {return MicroJavaSingleValueTokens.RBRACE;}
"[" {return MicroJavaSingleValueTokens.LBRACKET;}
"]" {return MicroJavaSingleValueTokens.RBRACKET;}
";" {return MicroJavaSingleValueTokens.SEMICOLON;}
"," {return MicroJavaSingleValueTokens.COMMA;}
"." {return MicroJavaSingleValueTokens.DOT;}

[\s\S] { return TokenType.BAD_CHARACTER; }