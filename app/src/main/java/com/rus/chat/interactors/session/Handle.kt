package com.rus.chat.interactors.session

import com.rus.chat.entity.session.Query
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * Created by RUS on 12.07.2016.
 */
@kotlin.annotation.Retention(kotlin.annotation.AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Handle(val value: KClass<out Query>)