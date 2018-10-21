package com.soywiz.klock

inline val Int.years get() = DateSpan(12 * this)
inline val Int.months get() = DateSpan(this)

inline class DateSpan(val totalMonths: Int) : Comparable<DateSpan> {
    operator fun unaryMinus() = DateSpan(-totalMonths)

    operator fun plus(other: TimeSpan) = DateTimeSpan(this, other)
    operator fun plus(other: DateSpan) = DateSpan(totalMonths + other.totalMonths)
    operator fun plus(other: DateTimeSpan) = DateTimeSpan(other.dateSpan + this, other.timeSpan)

    operator fun minus(other: TimeSpan) = this + -other
    operator fun minus(other: DateSpan) = this + -other
    operator fun minus(other: DateTimeSpan) = this + -other

    inline operator fun times(times: Double) = DateSpan((totalMonths * times).toInt())
    inline operator fun div(times: Double) = DateSpan((totalMonths / times).toInt())

    override fun compareTo(other: DateSpan): Int = this.totalMonths.compareTo(other.totalMonths)

    override fun toString(): String {
        val list = arrayListOf<String>()
        if (years != 0) list.add("${years}Y")
        if (months != 0) list.add("${months}M")
        return list.joinToString(" ")
    }
}

val DateSpan.years: Int get() = totalMonths / 12
val DateSpan.months: Int get() = totalMonths % 12
