package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"): String{
   val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val different: Long = (date.time - this.time)
    return if (different >= 0 && different < 1 * SECOND) "только что"
        else if(different >= 1 * SECOND && different < 45 * SECOND) "несколько секунд назад"
        else if(different >= 45 * SECOND && different < 75 * SECOND) "минуту назад"
        else if(different >= 75 * SECOND && different < 45 * MINUTE) TimeUnits.MINUTE.plural((different/MINUTE).toInt()) + " назад"
        else if(different >= 45 * MINUTE && different < 75 * MINUTE) "час назад"
        else if(different >= 75 * MINUTE && different < 22 * HOUR) TimeUnits.HOUR.plural((different/HOUR).toInt()) + " назад"
        else if(different >= 22 * HOUR && different < 26 * HOUR) "день назад"
        else if(different >= 26 * HOUR && different < 360 * DAY) TimeUnits.DAY.plural((different/ DAY).toInt()) + " назад"
        else "более года назад"
}

interface ITimeUnitsPlural {
    fun plural(payload:Int): String
}

enum class TimeUnits : ITimeUnitsPlural {
    SECOND {
         override fun plural(payload: Int):String {
             return "$payload " + when(payload){
                 1 -> "секунда"
                 in 2..4 -> "секунды"
                 else -> "секунд"
             }
         }
           },
    MINUTE {
        override fun plural(payload: Int):String {
            return "$payload " + when(payload){
                1 -> "минута"
                in 2..4 -> "минуты"
                else -> "минут"
            }
        }
          },
    HOUR {
        override fun plural(payload: Int):String {
            return "$payload " + when(payload){
                1 -> "час"
                in 2..4 -> "часа"
                else -> "часов"
            }
        }
        },
    DAY {
        override fun plural(payload: Int):String {
            return "$payload " + when(payload){
                1 -> "день"
                in 2..4 -> "дня"
                else -> "дней"
            }
        }
    }
}