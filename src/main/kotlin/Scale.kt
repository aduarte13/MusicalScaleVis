class Scale(
    private var rootNote: String,
    private var modeType: String,
    private val diatonicNotes: MutableList<String> = mutableListOf<String>(),
    private val formulaStrings: MutableList<String> = mutableListOf<String>(),
    private val formulaInts: MutableList<Int> = mutableListOf<Int>(),
    private val intervalStrings: Map<Int, String> = mapOf(
        1 to "h",
        2 to "W",
        3 to "m3",
        4 to "M4"
    ),
    private val allNotes: List<String> = listOf(
        "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb",
        "G", "G#/Ab", "A", "A#/Bb", "B"
    ),
    private val allMajorModes: List<String> = listOf(
        "Major/Ionian", "Dorian", "Phrygian", "Lydian",
        "Mixolydian", "Minor/Aeolian", "Locrian"
    ),
    private val stepsMajorModes: List<Int> = listOf(2, 2, 1, 2, 2, 2, 1)
    // val stepsPenta
    // val stepsNaturalMinor
    // val stepsMelodicMinor
    // val stepsByzantine
) {
    init{
        build()     // assembles scale
    }

    //
    fun build(){
        diatonicNotes.clear()   // empty diatonic notes list

        diatonicNotes.add(rootNote) // add the root note to scale

        setFormula(modeType)                // set formulaStrings and formulaInts to correct mode intervals


    }

    fun getRoot() = rootNote

    fun getMode() = modeType

    fun getFormulaInts() = formulaInts

    fun getFormulaStrings() = formulaStrings

    fun getDiatonicNotes() = diatonicNotes

    fun setNote(rootNote: String){ this.rootNote = rootNote }

    fun setMode(modeType: String){ this.modeType = modeType }

    fun setFormula(modeType: String) {
        formulaInts.clear()
        formulaStrings.clear()
        if (modeType in allMajorModes) {
            var offset: Int = allMajorModes.indexOf(modeType)
            for (i in 0 until stepsMajorModes.size){
                if (i + offset > stepsMajorModes.size){
                    offset -= stepsMajorModes.size
                }
                formulaInts.add(stepsMajorModes.get(i + offset))
                formulaStrings.add(intervalStrings[formulaInts[i]]!!)
            }
        }
        //else if // ADD OTHER MODES HERE
    }
}

fun main(){
    val scale1 = Scale(rootNote = "C", modeType = "Major/Ionian")
    println("${scale1.getRoot()} ${scale1.getMode()}")
    for (i in 0 until scale1.getDiatonicNotes().size){
        print(scale1.getDiatonicNotes().get(i) + " ")
    }
    println()
    for (i in 0 until scale1.getFormulaInts().size){
        print("${scale1.getFormulaInts().get(i)} ")
    }
    println()
    for (i in 0 until scale1.getFormulaStrings().size){
        print("${scale1.getFormulaStrings().get(i)} ")
    }
}