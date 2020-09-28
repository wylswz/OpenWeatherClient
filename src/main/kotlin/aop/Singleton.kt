package com.xmbsmdsj.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Singleton(val name: String = "") {
}


@Aspect
class SingletonInterceptor {
    companion object {
        private val singletonStore: ConcurrentHashMap<String, Any> = ConcurrentHashMap()


        private fun tryRegisterSingleton(name: String, target: Any) {

            if (!singletonStore.containsKey(name)) {
                singletonStore[name] = target
            }
        }

        private fun tryGetSingleton(name: String): Any? {
            return singletonStore[name]
        }
    }

    @Pointcut("@annotation(singleton)")
    fun pointcut(singleton: Singleton) {

    }

    @Around("pointcut(singleton)")
    @Suppress("UNCHECKED_CAST")
    fun around(jp: ProceedingJoinPoint, singleton: Singleton): Any {
        val name = if (singleton.name == "") {
            jp.signature.declaringTypeName + jp.signature.name
        } else {
            singleton.name
        }

        synchronized(singletonStore) {
            var obj = singletonStore[name]
            if (obj == null) {
                obj = jp.proceed()
                tryRegisterSingleton(name, obj)
            }
        }
        return tryGetSingleton(name)?:throw IllegalStateException("Fail get and register singleton")
    }
}