package mykotlin.parser

import mykotlin.parser.antlr.KotlinLexer
import mykotlin.parser.antlr.KotlinParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

fun main() {

    val stream = File("src/main/kotlin/mykotlin/parser/Container.kt")
            .inputStream()
            .let { CharStreams.fromStream(it) }

    val parser = KotlinParser(CommonTokenStream(KotlinLexer(stream)))

    println(KotlinVisitor().visit(parser.kotlinFile()))
}
