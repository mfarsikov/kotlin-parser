package mykotlin.parser

import java.time.LocalDateTime

private class TestClass(
    private val x: LocalDateTime
) {
    val y: Int = 0
    private val z = 39;

    private fun f(x: Int): Int {
        return 10
    }
    class Neested{
        private val a:Int = 10;
        private fun f():Unit{

        }
    }
}
