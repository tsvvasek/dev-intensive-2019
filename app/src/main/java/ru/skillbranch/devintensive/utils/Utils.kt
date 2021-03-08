package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?,String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return toPair(firstName,lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val pair = toPair(firstName, lastName)
        val firstInitial: Char? = pair.first?.toUpperCase(Locale.ROOT)?.get(0)
        val secondInitial: Char? = pair.second?.toUpperCase(Locale.ROOT)?.get(0)
        return if(firstInitial!=null) "$firstInitial${secondInitial?:""}" else null
    }

    fun transliteration(payload: String, devider: String = " "): String {
        val regExPatten: Regex = """"{1}.{1}"{1}:{1} {1}"{1}[^,]{1,4}""".toRegex()
        val transliterationString:String = """
            "а": "a",

            "б": "b",

            "в": "v",

            "г": "g",

            "д": "d",

            "е": "e",

            "ё": "e",

            "ж": "zh",

            "з": "z",

            "и": "i",

            "й": "i",

            "к": "k",

            "л": "l",

            "м": "m",

            "н": "n",

            "о": "o",

            "п": "p",

            "р": "r",

            "с": "s",

            "т": "t",

            "у": "u",

            "ф": "f",

            "х": "h",

            "ц": "c",

            "ч": "ch",

            "ш": "sh",

            "щ": "sh'",

            "ъ": "",

            "ы": "i",

            "ь": "",

            "э": "e",

            "ю": "yu",

            "я": "ya",
        """.trimIndent()

        val listOfSting:List<String> = regExPatten.findAll(transliterationString).map { matchResult -> matchResult.value }
        .map { s ->  s.replace(" ", "").replace("\"", "")}.toList()

        val changeMap: MutableMap<String,String> = mutableMapOf()
        for(s:String in listOfSting){
            val l:List<String> = s.split(":")
            changeMap[l[0]] = l[1]
        }
        changeMap[" "] = devider
        var resultString = ""
        for (c:Char in payload){
            val isCapital: Boolean = c.isUpperCase()
            val s:String? = changeMap[c.toLowerCase().toString()]
            resultString += if(isCapital) s?.capitalize(Locale.ROOT) else s ?: c.toString()
        }

        return resultString
    }

    private fun toPair(firstName: String?, lastName: String?): Pair<String?,String?>{
        val expectedFirstName: String? = if(firstName.isNullOrBlank()) null else firstName
        val expectedLastName: String? = if(lastName.isNullOrBlank()) null else lastName
        return expectedFirstName to expectedLastName
    }
}