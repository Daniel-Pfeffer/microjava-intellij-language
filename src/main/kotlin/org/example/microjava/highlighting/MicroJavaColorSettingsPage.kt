package org.example.microjava.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.example.microjava.MicroJavaIcons
import javax.swing.Icon

class MicroJavaColorSettingsPage : ColorSettingsPage {

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "MicroJava"
    }

    override fun getIcon(): Icon {
        return MicroJavaIcons.FILE
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return MicroJavaSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return """
            program Test
            {
              /* This is a comment */
              int <global>GLOBAL</global> = 1;
              final int <const>CONSTANT</const> = 1;
              
              void <function>doPrint</function>(char <param>x</param>) {
                print(<param>x</param>);
              }
              
              class <class>SomeClass</class>
            
              void <function>main</function>()
                int <local>a</local>;
                <class>SomeClass</class> <local>c</local>;
              {
                <local>c</local> = new <class>SomeClass</class>
            	read(a);
                <functionCall>doPrint</functionCall>('a');
            	if (<local>a</local> == 1) { print('='); }
                <local>a</local> += 1;
              }
            }
        """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> {
        return ADDITIONAL_DESCRIPTORS
    }
}

private val ADDITIONAL_DESCRIPTORS = mapOf(
    "global" to MicroJavaSyntaxHighlighter.GLOBAL_VARS,
    "const" to MicroJavaSyntaxHighlighter.CONSTANT,
    "function" to MicroJavaSyntaxHighlighter.FUNCTION_DECLARATION,
    "functionCall" to MicroJavaSyntaxHighlighter.FUNCTION_CALL,
    "class" to MicroJavaSyntaxHighlighter.CLASS_DECLARATION,
    "local" to MicroJavaSyntaxHighlighter.LOCAL_VARS,
    "param" to MicroJavaSyntaxHighlighter.PARAMS,
)

private val DESCRIPTORS = arrayOf(
    AttributesDescriptor("Keyword", MicroJavaSyntaxHighlighter.KEYWORD),
    AttributesDescriptor("Identifier", MicroJavaSyntaxHighlighter.IDENTIFIER),
    AttributesDescriptor("Number", MicroJavaSyntaxHighlighter.NUMBER),
    AttributesDescriptor("Char const", MicroJavaSyntaxHighlighter.CHAR_CONST),

    AttributesDescriptor("Braces and Operators//Brace", MicroJavaSyntaxHighlighter.BRACE),
    AttributesDescriptor("Braces and Operators//Bracket", MicroJavaSyntaxHighlighter.BRACKET),
    AttributesDescriptor("Braces and Operators//Parenthesis", MicroJavaSyntaxHighlighter.PARENTHESIS),
    AttributesDescriptor("Braces and Operators//Operator sign", MicroJavaSyntaxHighlighter.OPERATOR),
    AttributesDescriptor("Braces and Operators//Semicolon", MicroJavaSyntaxHighlighter.SEMICOLON),
    AttributesDescriptor("Braces and Operators//Dot", MicroJavaSyntaxHighlighter.DOT),
    AttributesDescriptor("Braces and Operators//Comma", MicroJavaSyntaxHighlighter.COMMA),

    AttributesDescriptor("Comment//Block comment", MicroJavaSyntaxHighlighter.COMMENT),

    AttributesDescriptor("Class//Class declaration", MicroJavaSyntaxHighlighter.CLASS_DECLARATION),

    AttributesDescriptor("Methods//Call", MicroJavaSyntaxHighlighter.FUNCTION_CALL),
    AttributesDescriptor("Methods//Declaration", MicroJavaSyntaxHighlighter.FUNCTION_DECLARATION),
    AttributesDescriptor("Methods//Parameter", MicroJavaSyntaxHighlighter.PARAMS),

    AttributesDescriptor("Variables//Local variables", MicroJavaSyntaxHighlighter.LOCAL_VARS),

    AttributesDescriptor("Program variables//Variables", MicroJavaSyntaxHighlighter.GLOBAL_VARS),
    AttributesDescriptor("Program variables//Constant", MicroJavaSyntaxHighlighter.CONSTANT),
)