package mykotlin.parser

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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

    val stream = File("src/main/kotlin/mykotlin/parser/TestClass.kt")
        .inputStream()
        .let { CharStreams.fromStream(it) }

    val parser = KotlinParser(CommonTokenStream(KotlinLexer(stream)))

    val kotlinFile = KotlinVisitor().visit(parser.kotlinFile())

    println(Json { prettyPrint = true }.encodeToString(kotlinFile))

    kotlinFile as AST.File
    kotlinFile.entries.filterIsInstance<AST.KotlinClass>()
        .map { klass -> toClassInfo(kotlinFile, klass) }
}

fun toClassInfo(file: AST.File, klass: AST.KotlinClass): ClassInfo {

    val qualifiedClassName = listOf(file.pkg ?: "", klass.name).joinToString(separator = ".")
    return ClassInfo(
        ClassName(qualifiedClassName),
        klass.methods.map { method ->
            val methodArguments = method.arguments
                .map { arg -> Argument(arg.name, TypeName.For(arg.type)) }
            MethodInfo(
                MethodName(
                    ClassName(qualifiedClassName),
                    method.name,
                    method.returnType,
                    methodArguments,
                    "" //TODO it is unclear what this parameter mean
                ),
                method.accessModifier?.toAccessParameter(),
                ClassName(qualifiedClassName),
                methodArguments,
                TypeName.For(method.returnType),
                SourceCodeSnippet(method.codeSnippet, SourceCodeLanguage.Kotlin)
            )
        },
        klass.properties.map { prop ->
            FieldInfo(
                FieldName(prop.name),
                ClassName(prop.type),
                prop.accessModifier.toAccessParameter(),
                SourceCodeSnippet(prop.codeSnippet, SourceCodeLanguage.Kotlin)
            )
        },
        klass.accessModifier.toAccessParameter(),
        klass.nestedClasses.map { nestedClass -> toClassInfo(file, klass) },
        SourceCodeSnippet(klass.codeSnippet, SourceCodeLanguage.Kotlin),
        false
    )
}

private fun String?.toAccessParameter(): AccessFlags = when (this) {
    "private" -> AccessFlags.AccPrivate
    "protected" -> AccessFlags.AccProtected
    else -> AccessFlags.AccPublic
}
