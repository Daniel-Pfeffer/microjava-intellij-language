package org.example.microjava

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
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
              int GLOBAL;
              final int CONSTANT = 1;
              
              void doPrint(char x) {
                print(x);
              }
            
              void main()
                int a;
              {
            	read(a);
                doPrint('a');
            	if (a == 1) { print('='); print('='); print(','); }
            	if (a != 1) { print('!'); print('='); print(','); }
            	if (a < 1)  { print('<'); print(','); }
            	if (a <= 1) { print('<'); print('='); print(','); }
            	if (a > 1)  { print('>'); print(','); }
            	if (a >= 1) { print('>'); print('='); print(','); }
              }
            }
        """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }
}

private val DESCRIPTORS = arrayOf(
    AttributesDescriptor("Keyword", MicroJavaSyntaxHighlighter.KEYWORD),
    AttributesDescriptor("Identifier", MicroJavaSyntaxHighlighter.IDENTIFIER),
    AttributesDescriptor("Number", MicroJavaSyntaxHighlighter.NUMBER),
    AttributesDescriptor("Char const", MicroJavaSyntaxHighlighter.CHAR_CONST),
    AttributesDescriptor("Operator", MicroJavaSyntaxHighlighter.OPERATOR),
    AttributesDescriptor("Semicolon", MicroJavaSyntaxHighlighter.SEMICOLON),
    AttributesDescriptor("Dot", MicroJavaSyntaxHighlighter.DOT),
    AttributesDescriptor("Comma", MicroJavaSyntaxHighlighter.COMMA),
    AttributesDescriptor("Brace", MicroJavaSyntaxHighlighter.BRACE),
    AttributesDescriptor("Bracket", MicroJavaSyntaxHighlighter.BRAKET),
    AttributesDescriptor("Parenthesis", MicroJavaSyntaxHighlighter.PARENTHESIS),
    AttributesDescriptor("Block comment", MicroJavaSyntaxHighlighter.COMMENT),
    AttributesDescriptor("Bad character", MicroJavaSyntaxHighlighter.BAD_CHARACTER),
    AttributesDescriptor("Function call", MicroJavaSyntaxHighlighter.FUNCTION_CALL),
    AttributesDescriptor("Function declaration", MicroJavaSyntaxHighlighter.FUNCTION_DECLARATION),

    AttributesDescriptor("Local variables", MicroJavaSyntaxHighlighter.LOCAL_VARS),
    AttributesDescriptor("Global variables", MicroJavaSyntaxHighlighter.GLOBAL_VARS),
    AttributesDescriptor("Constant", MicroJavaSyntaxHighlighter.CONSTANT),
)