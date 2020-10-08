package mykotlin.parser

import mykotlin.parser.antlr.KotlinParser
import mykotlin.parser.antlr.KotlinParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.misc.Interval


class KotlinVisitor : KotlinParserBaseVisitor<AST>() {
    override fun visitKotlinFile(ctx: KotlinParser.KotlinFileContext): AST {
        val imports = ctx.importList().importHeader().map { it.identifier().text }
        return AST.File(
            imports = imports,
            entries = ctx.topLevelObject().map { visit(it) },
            pkg = ctx.packageHeader()?.identifier()?.text
        )
    }

    override fun visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext): AST {
        return visitDeclaration(ctx.declaration())
    }

    override fun visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext): AST {
        val members = ctx
            .classBody()
            ?.classMemberDeclarations()
            ?.classMemberDeclaration()
            ?.map { visit(it) }
            ?: emptyList()

        return AST.KotlinClass(
            name = ctx.simpleIdentifier().text,
            methods = members.filterIsInstance<AST.Method>(),
            properties = members.filterIsInstance<AST.Property>(),
            nestedClasses = members.filterIsInstance<AST.KotlinClass>(),
            accessModifier = ctx.modifiers()?.modifier()?.mapNotNull { it.visibilityModifier()?.text }?.singleOrNull(),
            codeSnippet = ctx.plainText()
        )
    }

    override fun visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext): AST {
        val accessModifier = ctx.modifiers().modifier()
            .mapNotNull { it.visibilityModifier()?.text }
            .singleOrNull()

        return AST.Method(
            accessModifier = accessModifier,
            name = ctx.simpleIdentifier().text,
            returnType = ctx.type()?.text,
            arguments = ctx.functionValueParameters().functionValueParameter()?.map { visit(it) }
                ?.filterIsInstance<AST.Argument>() ?: emptyList(),
            codeSnippet = ctx.plainText()
        )
    }

    override fun visitFunctionValueParameter(ctx: KotlinParser.FunctionValueParameterContext): AST {
        return AST.Argument(ctx.parameter().simpleIdentifier().text, ctx.parameter().type().text)
    }

    override fun visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext): AST {
        return AST.Property(
            name = ctx.variableDeclaration().simpleIdentifier().text,
            type = ctx.variableDeclaration()?.type()?.text,
            accessModifier = ctx.modifiers()?.modifier()?.mapNotNull { it.visibilityModifier()?.text }?.firstOrNull(),
            codeSnippet = ctx.plainText()
        )
    }

    private fun ParserRuleContext.plainText():String{
        return start.inputStream.getText(Interval(start.startIndex, stop.stopIndex))
    }

}
