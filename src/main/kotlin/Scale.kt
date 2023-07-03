class Scale(
    var rootNote: String,
    var modeType: String,
    val diatonicNotes: MutableList<String> = mutableListOf<String>()
) {
    val allNotes: List<String> = listOf(
        "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb",
        "G", "G#/Ab", "A", "A#/Bb", "B"
    )
    val stepsMajorModes: List<Int> = listOf<Int>(2, 2, 1, 2, 2, 2, 1)
    // val stepsPenta
    // val stepsNaturalMinor
    // val stepsMelodicMinor
    // val stepsByzantine

    //
    fun build(){
        diatonicNotes.clear()   //

        diatonicNotes.add(rootNote)


    }

    fun getRoot() = rootNote

    fun getMode() = modeType

    fun setNote(rootNote: String){
        this.rootNote = rootNote
    }

    fun setMode(modeType: String){
        this.modeType = modeType
    }
}

fun main(){
    val scale1 = Scale(rootNote = "C", modeType = "Major/Ionian")
}