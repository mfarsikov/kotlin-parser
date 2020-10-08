package mykotlin.parser

import mykotlin.parser.antlr.KotlinParser
import mykotlin.parser.antlr.KotlinParserBaseVisitor


class KotlinVisitor: KotlinParserBaseVisitor<AST>() {
    override fun visitKotlinFile(ctx: KotlinParser.KotlinFileContext): AST {
        return AST.File(ctx.topLevelObject().map { visit(it) })
    }

    override fun visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext): AST {
        return visitDeclaration(ctx.declaration())
    }

    override fun visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext): AST {
        return AST.KotlinClass(
            name = ctx.simpleIdentifier().text,
            members = ctx
                .classBody()
                ?.classMemberDeclarations()
                ?.classMemberDeclaration()
                ?.map { visit(it) }
                ?: emptyList()
        )
    }

}
