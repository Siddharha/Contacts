package `in`.creativelizard.contacts.interfaces

interface SwipeActionCallback {
    fun onLeftSwipe(isDone:Boolean,position:Int)
    fun onRightSwipe(isDone:Boolean,position: Int)
    fun onMove(isDone:Boolean)
}