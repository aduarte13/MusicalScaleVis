class Scale(
    private var rootNote: String,
    private var modeType: String,
    private var flatsOrSharps: Int = 1,
    private val diatonicNotes: MutableList<String> = mutableListOf<String>(),
    private val cleanNotes: MutableList<String> = mutableListOf<String>(),
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
        diatonicNotes.clear()   //
        cleanNotes.clear()      //

        diatonicNotes.add(rootNote) // add the root note to scale
        cleanNotes.add(rootNote)

        setFormula(modeType)                // set formulaStrings and formulaInts to correct mode intervals

        // LOOP FOR BUILDING SCALE
        var offset: Int = allNotes.indexOf(rootNote)
        for (i in 0 until formulaInts.size-1){
            offset += formulaInts[i]
            if (offset >= allNotes.size)
                offset -= allNotes.size
            diatonicNotes.add(allNotes[offset])
            cleanNotes.add(allNotes[offset])
        }

        // LOOP FOR CLEANING THE SCALE
        for (i in 0 until cleanNotes.size) {
            when (cleanNotes[i]) {
                "C#/Db" -> if (flatsOrSharps == 1) cleanNotes[i] = "C#" else cleanNotes[i] = "Db"
                "D#/Eb" -> if (flatsOrSharps == 1) cleanNotes[i] = "D#" else cleanNotes[i] = "Eb"
                "F#/Gb" -> if (flatsOrSharps == 1) cleanNotes[i] = "F#" else cleanNotes[i] = "Gb"
                "G#/Ab" -> if (flatsOrSharps == 1) cleanNotes[i] = "G#" else cleanNotes[i] = "Ab"
                "A#/Bb" -> if (flatsOrSharps == 1) cleanNotes[i] = "A#" else cleanNotes[i] = "Bb"
            }
        }
        // CONSOLE LOG FOR DEBUGGING
        println("===================")
        println("$rootNote $modeType")
        for (i in 0 until diatonicNotes.size){
            print(diatonicNotes[i] + " ")
        }
        println()
        for (i in 0 until cleanNotes.size){
            print(cleanNotes[i] + " ")
        }
        println()
        for (i in 0 until formulaInts.size){
            print("${formulaInts[i]} ")
        }
        println()
        for (i in 0 until formulaStrings.size){
            print("${formulaStrings[i]} ")
        }
        println()
    }

    fun getRoot() = rootNote

    fun getMode() = modeType

    fun getFlatsOrSharps() = flatsOrSharps

    fun getFormulaInts() = formulaInts

    fun getFormulaStrings() = formulaStrings

    fun getDiatonicNotes() = diatonicNotes

    fun getCleanNotes() = cleanNotes

    fun setRoot(rootNote: String){ this.rootNote = rootNote; build() }

    fun setMode(modeType: String){ this.modeType = modeType; build() }

    private fun setFormula(modeType: String) {
        formulaInts.clear()
        formulaStrings.clear()
        if (modeType in allMajorModes) {
            var offset: Int = allMajorModes.indexOf(modeType)
            for (i in 0 until stepsMajorModes.size){
                formulaInts.add(stepsMajorModes[offset])
                formulaStrings.add(intervalStrings[formulaInts[i]]!!)
                offset += 1
                if (offset >= stepsMajorModes.size)
                    offset -= stepsMajorModes.size
            }
        }
        //else if // ADD OTHER MODES HERE
    }
}

fun main(){

}