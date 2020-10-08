package mykotlin.parser

import mykotlin.parser.antlr.KotlinLexer
import mykotlin.parser.antlr.KotlinParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import primitive.AccessFlags
import primitive.Argument
import primitive.ClassInfo
import primitive.ClassName
import primitive.FieldInfo
import primitive.FieldName
import primitive.MethodInfo
import primitive.MethodName
import primitive.SourceCodeLanguage
import primitive.SourceCodeSnippet
import primitive.TypeName
import java.io.File

fun main() {

    val stream = File("src/main/kotlin/mykotlin/parser/AST.kt")
        .inputStream()
        .let { CharStreams.fromStream(it) }

    val parser = KotlinParser(CommonTokenStream(KotlinLexer(stream)))

    println(KotlinVisitor().visit(parser.kotlinFile()))

    ClassInfo(
        ClassName(""),
        listOf(
            MethodInfo(
                MethodName(
                    ClassName(""),
                    "",
                    "",
                    listOf(Argument("", TypeName.For(""))),
                    ""
                ),
                AccessFlags.AccPrivate,
                ClassName(""),
                listOf(Argument("", TypeName.For(""))),
                TypeName.For(""),
                SourceCodeSnippet("", SourceCodeLanguage.Kotlin)
            )
        ),
        listOf(FieldInfo(
            FieldName(""),
            ClassName(""),
            AccessFlags.AccPrivate,
            SourceCodeSnippet("", SourceCodeLanguage.Kotlin)
        )),
        AccessFlags.AccPrivate,
        listOf(), //recursive
        SourceCodeSnippet("", SourceCodeLanguage.Kotlin),
        false
    )
}
