package asia.fourtitude.recipe.utils.string

import java.util.regex.Matcher
import java.util.regex.Pattern


object StringUtils {
    fun isTextOnlySingleWord(text: String): Boolean {

        val wordPattern: Pattern = Pattern.compile("\\w+")
        val wordMatcher: Matcher = wordPattern.matcher(text)
        if (wordMatcher.matches()) {
            return true
        }
        return false
    }

}