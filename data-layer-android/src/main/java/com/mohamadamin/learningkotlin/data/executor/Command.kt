package com.mohamadamin.learningkotlin.data.executor

/**
 * @author MohamadAmin Mohamadi (mohammadi.mohamadamin@gmail.com) on 2/11/17.
 */
public interface Command<T> {
    fun execute(): T
}