package dev.austinzhu.algo.interfaces

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Algorithm(val value: String = "")