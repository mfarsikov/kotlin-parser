package mykotlin.parser

sealed class AST {
    data class File(
        val entries: List<AST>
    ):AST()
    data class KotlinClass(
        val name: String,
        val members: List<AST>
    ): AST()
}