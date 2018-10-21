package com.soywiz.klock

import com.soywiz.klock.internal.*

interface DateTime {
    companion object : _InternalDateTimeCompanion()

    val year: Int
    val month1: Int
    val dayOfWeekInt: Int
    val dayOfMonth: Int
    val dayOfYear: Int
    val hours: Int
    val minutes: Int
    val seconds: Int
    val milliseconds: Int
    val timeZone: String
    val unixLong: Long get() = unixDouble.toLong()
    val unixDouble: Double
    val offset: Int
    fun add(deltaMonths: Int, deltaMilliseconds: Double): DateTime

    val dayOfWeek: DayOfWeek get() = DayOfWeek[dayOfWeekInt]
    val month0: Int get() = month1 - 1
    val month: Month get() = Month[month1]

    val utc: UtcDateTime
    val adjusted: UtcDateTime
    val local get() = OffsetDateTime(this, Klock.localTimezoneOffsetMinutes(UtcDateTime(unixDouble)).minutes.toInt())

    @Deprecated("", ReplaceWith("utc"))
    fun toUtc(): DateTime = utc

    @Deprecated("", ReplaceWith("local"))
    fun toLocal() = local

    fun addOffset(offset: Int) = OffsetDateTime(this, this.offset + offset)
    fun toOffset(offset: Int) = OffsetDateTime(this, offset)

    operator fun plus(delta: DateSpan): DateTime = this.add(delta.totalMonths, 0.0)
    operator fun plus(delta: DateTimeSpan): DateTime = this.add(delta.totalMonths, delta.totalMilliseconds)
    operator fun plus(delta: TimeSpan): DateTime = add(0, delta.milliseconds)

    operator fun minus(delta: DateSpan): DateTime = this + -delta
    operator fun minus(delta: DateTimeSpan): DateTime = this + -delta
    operator fun minus(delta: TimeSpan): DateTime = this + (-delta)

    operator fun minus(other: DateTime): TimeSpan = (this.unixDouble - other.unixDouble).milliseconds

    fun toString(format: String): String = toString(SimplerDateFormat(format))
    fun toString(format: SimplerDateFormat): String = format.format(this)

    //override fun hashCode(): Int
    //override fun equals(other: Any?): Boolean
}
