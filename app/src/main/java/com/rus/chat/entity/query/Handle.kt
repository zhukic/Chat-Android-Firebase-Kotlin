package com.rus.chat.entity.query

import com.rus.chat.entity.query.BaseQuery
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by RUS on 12.07.2016.
 */
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Handle(val value: KClass<out BaseQuery>)