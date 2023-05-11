package asia.fourtitude.recipe.utils.exception

interface ExceptionListener {
    fun uncaughtException(thread: Thread, throwable: Throwable)
}