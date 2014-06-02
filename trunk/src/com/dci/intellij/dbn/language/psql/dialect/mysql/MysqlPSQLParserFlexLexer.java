/* The following code was generated by JFlex 1.4.3 on 02.06.14 17:53 */

package com.dci.intellij.dbn.language.psql.dialect.mysql;

import com.dci.intellij.dbn.language.common.TokenTypeBundle;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 02.06.14 17:53 from the specification file
 * <tt>C:/Projects/DBN-13.0/src/com/dci/intellij/dbn/language/psql/dialect/mysql/PSQLParser.flex</tt>
 */
public final class MysqlPSQLParserFlexLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int DIV = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\14\1\3\1\2\1\0\1\3\1\1\16\14\4\0\1\3\1\33"+
    "\1\16\1\15\1\13\1\41\1\0\1\17\1\37\1\44\1\4\1\22"+
    "\1\32\1\6\1\24\1\5\12\23\1\27\1\45\1\40\1\30\1\34"+
    "\1\0\1\31\3\12\1\25\1\10\1\25\6\12\1\11\1\21\2\12"+
    "\1\20\1\7\10\12\1\35\1\0\1\42\1\0\1\13\1\0\3\12"+
    "\1\25\1\10\1\25\6\12\1\11\1\21\2\12\1\20\1\7\10\12"+
    "\1\36\1\26\1\43\1\0\41\14\2\0\4\13\4\0\1\13\2\0"+
    "\1\14\7\0\1\13\4\0\1\13\5\0\27\13\1\0\37\13\1\0"+
    "\u01ca\13\4\0\14\13\16\0\5\13\7\0\1\13\1\0\1\13\21\0"+
    "\160\14\5\13\1\0\2\13\2\0\4\13\10\0\1\13\1\0\3\13"+
    "\1\0\1\13\1\0\24\13\1\0\123\13\1\0\213\13\1\0\5\14"+
    "\2\0\236\13\11\0\46\13\2\0\1\13\7\0\47\13\11\0\55\14"+
    "\1\0\1\14\1\0\2\14\1\0\2\14\1\0\1\14\10\0\33\13"+
    "\5\0\3\13\15\0\4\14\7\0\1\13\4\0\13\14\5\0\53\13"+
    "\37\14\4\0\2\13\1\14\143\13\1\0\1\13\10\14\1\0\6\14"+
    "\2\13\2\14\1\0\4\14\2\13\12\14\3\13\2\0\1\13\17\0"+
    "\1\14\1\13\1\14\36\13\33\14\2\0\131\13\13\14\1\13\16\0"+
    "\12\14\41\13\11\14\2\13\4\0\1\13\5\0\26\13\4\14\1\13"+
    "\11\14\1\13\3\14\1\13\5\14\22\0\31\13\3\14\244\0\4\14"+
    "\66\13\3\14\1\13\22\14\1\13\7\14\12\13\2\14\2\0\12\14"+
    "\1\0\7\13\1\0\7\13\1\0\3\14\1\0\10\13\2\0\2\13"+
    "\2\0\26\13\1\0\7\13\1\0\1\13\3\0\4\13\2\0\1\14"+
    "\1\13\7\14\2\0\2\14\2\0\3\14\1\13\10\0\1\14\4\0"+
    "\2\13\1\0\3\13\2\14\2\0\12\14\4\13\7\0\1\13\5\0"+
    "\3\14\1\0\6\13\4\0\2\13\2\0\26\13\1\0\7\13\1\0"+
    "\2\13\1\0\2\13\1\0\2\13\2\0\1\14\1\0\5\14\4\0"+
    "\2\14\2\0\3\14\3\0\1\14\7\0\4\13\1\0\1\13\7\0"+
    "\14\14\3\13\1\14\13\0\3\14\1\0\11\13\1\0\3\13\1\0"+
    "\26\13\1\0\7\13\1\0\2\13\1\0\5\13\2\0\1\14\1\13"+
    "\10\14\1\0\3\14\1\0\3\14\2\0\1\13\17\0\2\13\2\14"+
    "\2\0\12\14\1\0\1\13\17\0\3\14\1\0\10\13\2\0\2\13"+
    "\2\0\26\13\1\0\7\13\1\0\2\13\1\0\5\13\2\0\1\14"+
    "\1\13\7\14\2\0\2\14\2\0\3\14\10\0\2\14\4\0\2\13"+
    "\1\0\3\13\2\14\2\0\12\14\1\0\1\13\20\0\1\14\1\13"+
    "\1\0\6\13\3\0\3\13\1\0\4\13\3\0\2\13\1\0\1\13"+
    "\1\0\2\13\3\0\2\13\3\0\3\13\3\0\14\13\4\0\5\14"+
    "\3\0\3\14\1\0\4\14\2\0\1\13\6\0\1\14\16\0\12\14"+
    "\11\0\1\13\7\0\3\14\1\0\10\13\1\0\3\13\1\0\27\13"+
    "\1\0\12\13\1\0\5\13\3\0\1\13\7\14\1\0\3\14\1\0"+
    "\4\14\7\0\2\14\1\0\2\13\6\0\2\13\2\14\2\0\12\14"+
    "\22\0\2\14\1\0\10\13\1\0\3\13\1\0\27\13\1\0\12\13"+
    "\1\0\5\13\2\0\1\14\1\13\7\14\1\0\3\14\1\0\4\14"+
    "\7\0\2\14\7\0\1\13\1\0\2\13\2\14\2\0\12\14\1\0"+
    "\2\13\17\0\2\14\1\0\10\13\1\0\3\13\1\0\51\13\2\0"+
    "\1\13\7\14\1\0\3\14\1\0\4\14\1\13\10\0\1\14\10\0"+
    "\2\13\2\14\2\0\12\14\12\0\6\13\2\0\2\14\1\0\22\13"+
    "\3\0\30\13\1\0\11\13\1\0\1\13\2\0\7\13\3\0\1\14"+
    "\4\0\6\14\1\0\1\14\1\0\10\14\22\0\2\14\15\0\60\13"+
    "\1\14\2\13\7\14\4\0\10\13\10\14\1\0\12\14\47\0\2\13"+
    "\1\0\1\13\2\0\2\13\1\0\1\13\2\0\1\13\6\0\4\13"+
    "\1\0\7\13\1\0\3\13\1\0\1\13\1\0\1\13\2\0\2\13"+
    "\1\0\4\13\1\14\2\13\6\14\1\0\2\14\1\13\2\0\5\13"+
    "\1\0\1\13\1\0\6\14\2\0\12\14\2\0\2\13\42\0\1\13"+
    "\27\0\2\14\6\0\12\14\13\0\1\14\1\0\1\14\1\0\1\14"+
    "\4\0\2\14\10\13\1\0\44\13\4\0\24\14\1\0\2\14\5\13"+
    "\13\14\1\0\44\14\11\0\1\14\71\0\53\13\24\14\1\13\12\14"+
    "\6\0\6\13\4\14\4\13\3\14\1\13\3\14\2\13\7\14\3\13"+
    "\4\14\15\13\14\14\1\13\17\14\2\0\46\13\12\0\53\13\1\0"+
    "\1\13\3\0\u0149\13\1\0\4\13\2\0\7\13\1\0\1\13\1\0"+
    "\4\13\2\0\51\13\1\0\4\13\2\0\41\13\1\0\4\13\2\0"+
    "\7\13\1\0\1\13\1\0\4\13\2\0\17\13\1\0\71\13\1\0"+
    "\4\13\2\0\103\13\2\0\3\14\40\0\20\13\20\0\125\13\14\0"+
    "\u026c\13\2\0\21\13\1\0\32\13\5\0\113\13\3\0\3\13\17\0"+
    "\15\13\1\0\4\13\3\14\13\0\22\13\3\14\13\0\22\13\2\14"+
    "\14\0\15\13\1\0\3\13\1\0\2\14\14\0\64\13\40\14\3\0"+
    "\1\13\3\0\2\13\1\14\2\0\12\14\41\0\3\14\2\0\12\14"+
    "\6\0\130\13\10\0\51\13\1\14\1\13\5\0\106\13\12\0\35\13"+
    "\3\0\14\14\4\0\14\14\12\0\12\14\36\13\2\0\5\13\13\0"+
    "\54\13\4\0\21\14\7\13\2\14\6\0\12\14\46\0\27\13\5\14"+
    "\4\0\65\13\12\14\1\0\35\14\2\0\13\14\6\0\12\14\15\0"+
    "\1\13\130\0\5\14\57\13\21\14\7\13\4\0\12\14\21\0\11\14"+
    "\14\0\3\14\36\13\12\14\3\0\2\13\12\14\6\0\46\13\16\14"+
    "\14\0\44\13\24\14\10\0\12\14\3\0\3\13\12\14\44\13\122\0"+
    "\3\14\1\0\25\14\4\13\1\14\4\13\1\14\15\0\300\13\47\14"+
    "\25\0\4\14\u0116\13\2\0\6\13\2\0\46\13\2\0\6\13\2\0"+
    "\10\13\1\0\1\13\1\0\1\13\1\0\1\13\1\0\37\13\2\0"+
    "\65\13\1\0\7\13\1\0\1\13\3\0\3\13\1\0\7\13\3\0"+
    "\4\13\2\0\6\13\4\0\15\13\5\0\3\13\1\0\7\13\16\0"+
    "\5\14\32\0\5\14\20\0\2\13\23\0\1\13\13\0\5\14\5\0"+
    "\6\14\1\0\1\13\15\0\1\13\20\0\15\13\3\0\32\13\26\0"+
    "\15\14\4\0\1\14\3\0\14\14\21\0\1\13\4\0\1\13\2\0"+
    "\12\13\1\0\1\13\3\0\5\13\6\0\1\13\1\0\1\13\1\0"+
    "\1\13\1\0\4\13\1\0\13\13\2\0\4\13\5\0\5\13\4\0"+
    "\1\13\21\0\51\13\u0a77\0\57\13\1\0\57\13\1\0\205\13\6\0"+
    "\4\13\3\14\16\0\46\13\12\0\66\13\11\0\1\13\17\0\1\14"+
    "\27\13\11\0\7\13\1\0\7\13\1\0\7\13\1\0\7\13\1\0"+
    "\7\13\1\0\7\13\1\0\7\13\1\0\7\13\1\0\40\14\57\0"+
    "\1\13\u01d5\0\3\13\31\0\11\13\6\14\1\0\5\13\2\0\5\13"+
    "\4\0\126\13\2\0\2\14\2\0\3\13\1\0\132\13\1\0\4\13"+
    "\5\0\51\13\3\0\136\13\21\0\33\13\65\0\20\13\u0200\0\u19b6\13"+
    "\112\0\u51cc\13\64\0\u048d\13\103\0\56\13\2\0\u010d\13\3\0\20\13"+
    "\12\14\2\13\24\0\57\13\1\14\14\0\2\14\1\0\31\13\10\0"+
    "\120\13\2\14\45\0\11\13\2\0\147\13\2\0\4\13\1\0\2\13"+
    "\16\0\12\13\120\0\10\13\1\14\3\13\1\14\4\13\1\14\27\13"+
    "\5\14\20\0\1\13\7\0\64\13\14\0\2\14\62\13\21\14\13\0"+
    "\12\14\6\0\22\14\6\13\3\0\1\13\4\0\12\14\34\13\10\14"+
    "\2\0\27\13\15\14\14\0\35\13\3\0\4\14\57\13\16\14\16\0"+
    "\1\13\12\14\46\0\51\13\16\14\11\0\3\13\1\14\10\13\2\14"+
    "\2\0\12\14\6\0\27\13\3\0\1\13\1\14\4\0\60\13\1\14"+
    "\1\13\3\14\2\13\2\14\5\13\2\14\1\13\1\14\1\13\30\0"+
    "\3\13\43\0\6\13\2\0\6\13\2\0\6\13\11\0\7\13\1\0"+
    "\7\13\221\0\43\13\10\14\1\0\2\14\2\0\12\14\6\0\u2ba4\13"+
    "\14\0\27\13\4\0\61\13\u2104\0\u012e\13\2\0\76\13\2\0\152\13"+
    "\46\0\7\13\14\0\5\13\5\0\1\13\1\14\12\13\1\0\15\13"+
    "\1\0\5\13\1\0\1\13\1\0\2\13\1\0\2\13\1\0\154\13"+
    "\41\0\u016b\13\22\0\100\13\2\0\66\13\50\0\15\13\3\0\20\14"+
    "\20\0\7\14\14\0\2\13\30\0\3\13\31\0\1\13\6\0\5\13"+
    "\1\0\207\13\2\0\1\14\4\0\1\13\13\0\12\14\7\0\32\13"+
    "\4\0\1\13\1\0\32\13\13\0\131\13\3\0\6\13\2\0\6\13"+
    "\2\0\6\13\2\0\3\13\3\0\2\13\3\0\2\13\22\0\3\14"+
    "\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\4\1\5\2\1\1\6"+
    "\1\7\1\10\2\1\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\0\1\34"+
    "\1\35\1\1\1\7\1\10\3\0\1\36\1\37\1\40"+
    "\1\41\1\0\1\34\1\1\2\0\1\12\4\0\1\35"+
    "\1\10\1\0\2\36\1\34\1\10";

  private static int [] zzUnpackAction() {
    int [] result = new int[63];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\114\0\162\0\114\0\230\0\276\0\344"+
    "\0\u010a\0\114\0\u0130\0\u0156\0\u017c\0\u01a2\0\114\0\u01c8"+
    "\0\u01ee\0\u0214\0\u023a\0\114\0\114\0\114\0\114\0\114"+
    "\0\114\0\114\0\114\0\114\0\114\0\114\0\114\0\114"+
    "\0\114\0\u0260\0\u0286\0\u02ac\0\u02d2\0\114\0\u02f8\0\u031e"+
    "\0\u0344\0\u036a\0\u0390\0\114\0\114\0\114\0\u03b6\0\u03dc"+
    "\0\u0402\0\u0428\0\u044e\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c"+
    "\0\u0532\0\u0558\0\u057e\0\u057e\0\u04c0\0\114\0\u0428";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[63];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\3\4\1\5\1\6\1\7\1\10\4\11\1\3"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\11\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\0\3\4\1\5\1\6\1\7\1\10\4\11"+
    "\1\0\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\11\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\47\0\3\4\3\0\1\42\42\0\1\43"+
    "\47\0\1\44\46\0\1\11\1\45\5\11\2\0\2\11"+
    "\1\0\1\11\1\0\1\11\27\0\7\11\2\0\2\11"+
    "\1\0\1\11\1\0\1\11\20\0\16\13\1\46\27\13"+
    "\17\14\1\47\26\14\7\0\7\11\1\0\1\50\2\11"+
    "\1\0\1\11\1\0\1\11\27\0\7\11\1\0\1\14"+
    "\1\15\1\11\1\0\1\11\1\0\1\11\30\0\1\51"+
    "\12\0\1\20\1\52\44\0\1\53\1\54\47\0\1\55"+
    "\47\0\1\56\25\0\1\57\35\0\46\60\1\44\2\0"+
    "\43\44\7\0\2\11\1\61\4\11\2\0\2\11\1\0"+
    "\1\11\1\0\1\11\37\0\1\14\26\0\17\62\1\0"+
    "\26\62\6\0\1\63\13\0\1\63\1\64\45\0\1\53"+
    "\32\0\1\65\12\0\1\53\1\0\1\66\31\0\1\67"+
    "\34\0\4\60\1\70\41\60\7\71\4\11\5\71\2\11"+
    "\3\71\1\11\20\71\17\72\1\62\26\72\23\0\1\64"+
    "\45\0\1\64\1\52\27\0\1\73\13\0\1\73\1\74"+
    "\23\0\3\75\42\0\7\71\4\0\5\71\2\0\3\71"+
    "\1\0\20\71\4\60\1\70\1\76\40\60\2\71\1\0"+
    "\43\71\17\72\1\77\26\72\23\0\1\74\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1444];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\4\1\1\11\4\1\1\11"+
    "\4\1\16\11\1\0\3\1\1\11\1\1\3\0\1\1"+
    "\3\11\1\0\2\1\2\0\1\1\4\0\2\1\1\0"+
    "\2\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[63];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private int braceCounter = 0;
    private TokenTypeBundle tt;
    public MysqlPSQLParserFlexLexer(TokenTypeBundle tt) {
        this.tt = tt;
    }


  public MysqlPSQLParserFlexLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public MysqlPSQLParserFlexLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2202) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL.charAt(zzCurrentPosL++);
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL.charAt(zzCurrentPosL++);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 4: 
          { return tt.getCharacterTokenType(19);
          }
        case 34: break;
        case 21: 
          { return tt.getCharacterTokenType(10);
          }
        case 35: break;
        case 13: 
          { return tt.getCharacterTokenType(1);
          }
        case 36: break;
        case 32: 
          { return tt.getOperatorTokenType(0);
          }
        case 37: break;
        case 11: 
          { return tt.getCharacterTokenType(3);
          }
        case 38: break;
        case 5: 
          { return tt.getCharacterTokenType(12);
          }
        case 39: break;
        case 1: 
          { yybegin(YYINITIAL); return tt.getSharedTokenTypes().getIdentifier();
          }
        case 40: break;
        case 3: 
          { return tt.getCharacterTokenType(20);
          }
        case 41: break;
        case 31: 
          { return tt.getOperatorTokenType(2);
          }
        case 42: break;
        case 9: 
          { return tt.getCharacterTokenType(14);
          }
        case 43: break;
        case 17: 
          { return tt.getCharacterTokenType(5);
          }
        case 44: break;
        case 7: 
          { yybegin(YYINITIAL); return tt.getSharedTokenTypes().getQuotedIdentifier();
          }
        case 45: break;
        case 29: 
          { return tt.getSharedTokenTypes().getLineComment();
          }
        case 46: break;
        case 25: 
          { return tt.getCharacterTokenType(16);
          }
        case 47: break;
        case 6: 
          { return tt.getCharacterTokenType(7);
          }
        case 48: break;
        case 30: 
          { return tt.getSharedTokenTypes().getNumber();
          }
        case 49: break;
        case 10: 
          { return tt.getSharedTokenTypes().getInteger();
          }
        case 50: break;
        case 27: 
          { return tt.getCharacterTokenType(18);
          }
        case 51: break;
        case 20: 
          { return tt.getCharacterTokenType(9);
          }
        case 52: break;
        case 15: 
          { return tt.getCharacterTokenType(0);
          }
        case 53: break;
        case 22: 
          { return tt.getCharacterTokenType(11);
          }
        case 54: break;
        case 16: 
          { return tt.getCharacterTokenType(2);
          }
        case 55: break;
        case 33: 
          { return tt.getOperatorTokenType(1);
          }
        case 56: break;
        case 23: 
          { return tt.getCharacterTokenType(13);
          }
        case 57: break;
        case 14: 
          { return tt.getCharacterTokenType(4);
          }
        case 58: break;
        case 8: 
          { return tt.getSharedTokenTypes().getString();
          }
        case 59: break;
        case 12: 
          { return tt.getCharacterTokenType(21);
          }
        case 60: break;
        case 24: 
          { return tt.getCharacterTokenType(15);
          }
        case 61: break;
        case 18: 
          { return tt.getCharacterTokenType(6);
          }
        case 62: break;
        case 2: 
          { return tt.getSharedTokenTypes().getWhiteSpace();
          }
        case 63: break;
        case 28: 
          { return tt.getSharedTokenTypes().getBlockComment();
          }
        case 64: break;
        case 26: 
          { return tt.getCharacterTokenType(17);
          }
        case 65: break;
        case 19: 
          { return tt.getCharacterTokenType(8);
          }
        case 66: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
