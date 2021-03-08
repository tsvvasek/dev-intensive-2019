package ru.skillbranch.devintensive.extensions


fun String.truncate(numberOfSymbols: Int = 16):String{
    val additive = "\u2026"
    val subString:String = this.trimEnd()
    return if (subString.length >numberOfSymbols)
        subString.substring(0, numberOfSymbols).trimEnd()+ additive else subString
}

fun String.trimHTML():String{
    val regExPatten1: Regex = """<.*?>""".toRegex()
    val regExPatten2: Regex = """ +""".toRegex()

    return this.replace(regExPatten1,"")
            .replace("&nbsp;", "")
            .replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&quot;", "\"")
            .replace("&prime;", "\'")
            .replace(regExPatten2," ")
            .trim()
}