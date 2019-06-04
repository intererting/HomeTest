package com.yuliyang.hometest

import androidx.annotation.IntDef
import androidx.annotation.StringDef


const val TYPE_A = "type_a"
const val TYPE_B = "type_b"

@Target(AnnotationTarget.VALUE_PARAMETER)
@StringDef("3")
annotation class TestAnnotation
