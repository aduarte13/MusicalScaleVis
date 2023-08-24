class Scale(
        private var rootNote: String,
        private var modeType: String,
        private var usingSharps: Boolean = true,
        private val diatonicNotes: MutableList<String> = mutableListOf<String>(),
        private val cleanNotes: MutableList<String> = mutableListOf<String>(),
        private var diatonicChords: MutableList<String> = mutableListOf<String>(),
        private var formulaStrings: MutableList<String> = mutableListOf<String>(),
        private var formulaInts: MutableList<Int> = mutableListOf<Int>(),
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
        private val pentaModes: List<String> = listOf(
        "Major Pentatonic", "Minor Pentatonic"
        ),
        private val stepsMajorModes: List<Int> = listOf(2, 2, 1, 2, 2, 2, 1),
        //private val chordsMajorModes: List<String> = listOf(
        //    "Major", "Minor", "Minor", "Major",
        //    "Major", "Minor", "Diminished"
        //),
        // val stepsNaturalMinor
        // val stepsMelodicMinor
        // val stepsByzantine
) {
    init{
        build()     // assembles scale
    }

    //
    private fun build(){
        diatonicNotes.clear()   //
        cleanNotes.clear()      //

        diatonicNotes.add(rootNote) // add the root note to scale
        cleanNotes.add(rootNote)

        setFormula(modeType)        // set formulaStrings and formulaInts to correct mode intervals
        setDiatonicChords()         //

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
                "C#/Db" -> if (usingSharps) cleanNotes[i] = "C#" else cleanNotes[i] = "Db"
                "D#/Eb" -> if (usingSharps) cleanNotes[i] = "D#" else cleanNotes[i] = "Eb"
                "F#/Gb" -> if (usingSharps) cleanNotes[i] = "F#" else cleanNotes[i] = "Gb"
                "G#/Ab" -> if (usingSharps) cleanNotes[i] = "G#" else cleanNotes[i] = "Ab"
                "A#/Bb" -> if (usingSharps) cleanNotes[i] = "A#" else cleanNotes[i] = "Bb"
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
        for (i in 0 until diatonicChords.size){
            print("${diatonicChords[i]} ")
        }
        println()
    }

    fun getRoot() = rootNote

    fun getMode() = modeType

    fun getUsingSharps() = usingSharps

    fun switchUsingSharps(){
        usingSharps = !usingSharps
    }

    fun getFormulaInts() = formulaInts

    fun getFormulaStrings() = formulaStrings

    fun getDiatonicNotes() = diatonicNotes

    fun getDiatonicChords() = diatonicChords

    fun getCleanNotes() = cleanNotes

    fun setRoot(rootNote: String){ this.rootNote = rootNote; build() }

    fun setMode(modeType: String){ this.modeType = modeType; build() }

    private fun setFormula(modeType: String) {
        formulaInts.clear()
        formulaStrings.clear()

        if (modeType in allMajorModes) {
            var offset: Int = allMajorModes.indexOf(modeType)
            for (i in stepsMajorModes.indices){
                formulaInts.add(stepsMajorModes[offset])
                formulaStrings.add(intervalStrings[formulaInts[i]]!!)
                offset += 1
                if (offset >= stepsMajorModes.size)
                    offset -= stepsMajorModes.size
            }
        }
        else if (modeType in pentaModes){
            if (modeType == "Major Pentatonic"){
                formulaInts = mutableListOf(2, 2, 3, 2, 3)
                for (i in 0 until formulaInts.size){
                    formulaStrings.add(intervalStrings[formulaInts[i]]!!)
                }
            }
            else if (modeType == "Minor Pentatonic"){
                formulaInts = mutableListOf(3, 2, 2, 3, 2)
                for (i in 0 until formulaInts.size){
                    formulaStrings.add(intervalStrings[formulaInts[i]]!!)
                }
            }
        }
        else if (modeType == "Major Blues"){
            formulaInts = mutableListOf(2, 1, 1, 3, 2, 3)
            for (i in 0 until formulaInts.size){
                formulaStrings.add(intervalStrings[formulaInts[i]]!!)
            }
        }
        else if (modeType == "Minor Blues"){
            formulaInts = mutableListOf(3, 2, 1, 1, 3, 2)
            for (i in 0 until formulaInts.size){
                formulaStrings.add(intervalStrings[formulaInts[i]]!!)
            }
        }
        else{
            println("Mode not found!!!")
        }
    }

    private fun setDiatonicChords(){
        diatonicChords.clear()  //

        when (modeType) {
            "Major/Ionian" -> diatonicChords = mutableListOf("I", "ii", "iii", "IV", "V", "vi", "vii-")
            "Dorian" -> diatonicChords = mutableListOf("i", "ii", "III", "IV", "v", "vi-", "VII")
            "Phrygian" -> diatonicChords = mutableListOf("i", "II", "III", "iv", "v-", "VI", "vii")
            "Lydian" -> diatonicChords = mutableListOf("I", "II", "iii", "iv-", "V", "vi", "vii")
            "Mixolydian" -> diatonicChords = mutableListOf("I", "ii", "iii-", "IV", "v", "vi", "VII")
            "Minor/Aeolian" -> diatonicChords = mutableListOf("i", "ii-", "III", "iv", "v", "VI", "VII")
            "Locrian" -> diatonicChords = mutableListOf("i-", "II", "iii", "iv", "V", "VI", "vii")
        }

    }
}

fun main(){
    //val scale = Scale(rootNote = "C", modeType = "Major/Ionian")
}