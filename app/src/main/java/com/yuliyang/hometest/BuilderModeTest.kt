package com.yuliyang.hometest

class BuilderModeTest() {

    private lateinit var name: String

    private constructor(name: String) : this() {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    inner class Builder {
        private var name: String = ""

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun build(): BuilderModeTest {
            return BuilderModeTest(name)
        }
    }
}