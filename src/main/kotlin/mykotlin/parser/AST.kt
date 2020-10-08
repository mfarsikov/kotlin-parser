package mykotlin.parser
import kotlinx.serialization.*

@Serializable
sealed class AST {
    @Serializable
    data class File(
        val pkg: String?,
        val imports: List<String>,
        val entries: List<AST>
    ) : AST()
    @Serializable
    data class KotlinClass(
        val name: String,
        val methods: List<Method>,
        val properties: List<Property>,
        val accessModifier: String?,
        val nestedClasses: List<KotlinClass>,
        val codeSnippet: String,
    ) : AST()
    @Serializable
    data class Method(
        val name: String,
        val accessModifier: String?,
        val returnType: String?,
        val arguments: List<Argument>,
        val codeSnippet: String
    ) : AST()
    @Serializable
    data class Argument(
        val name: String,
        val type: String
    ) : AST()
    @Serializable
    data class Property(
        val name: String,
        val type: String?,
        val accessModifier: String?,
        val codeSnippet: String
    ) : AST()
}