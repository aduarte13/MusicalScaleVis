class Scale(
        private var rootNote: String,
        private var modeType: String,
        private var usingSharps: Boolean = true,
        private val diatonicNotes: MutableList<String> = mutableListOf<String>(),
        private val cleanNotes: MutableList<String> = mutableListOf<String>(),
        private var diatonicChords: MutableList<String> = mutableListOf<String>(),
        private var diatonicIntervals: MutableList<String> = mutableListOf<String>(),
        private var formula: MutableList<String> = mutableListOf<String>(),
        private var steps: MutableList<String> = mutableListOf<String>(),
        private val intervalStrings: Map<Int, String> = mapOf(
        // unison && octave omitted
            0 to "R",
        1 to "m2",   // half step || minor 2md
        2 to "M2",   // whole step || major 2nd
        3 to "m3",  // minor 3rd
        4 to "M3",  // major 3rd
        5 to "P4",  // perfect 4th
        6 to "TT",  // tritone
        7 to "P5",  // perfect 5th
        8 to "m6",  // minor 6th
        9 to "M6",  // major 6th
        10 to "m7", // minor 7th
        11 to "M7"  // major 7th
        ),
        private val intervalToInt: Map<String, Int> = mapOf(
            "1" to 0,
            "#1" to 1, "b2" to 1,
            "2" to 2,
            "#2" to 3, "b3" to 3,
            "3" to 4, "b4" to 4,
            "#3" to 5, "4" to 5,
            "#4" to 6, "b5" to 6,
            "5" to 7,
            "#5" to 8, "b6" to 8,
            "6" to 9,
            "#6" to 10, "b7" to 10,
            "7" to 11
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
    fun build(){
        diatonicNotes.clear()   //
        cleanNotes.clear()      //

        //diatonicNotes.add(rootNote) // add the root note to scale
        //cleanNotes.add(rootNote)

        setFormula()        // set formulaStrings and formulaInts to correct mode intervals
        setDiatonicChords()         //
        setDiatonicIntervals()      //
        setSteps()

        // LOOP FOR BUILDING SCALE
        val rootIndex: Int = allNotes.indexOf(rootNote)
        for (i in 0 until formula.size){
            var offset = rootIndex + intervalToInt[formula[i]]!!
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
        for (i in 0 until formula.size){
            print("${formula[i]} ")
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

    fun setUsingSharps(b: Boolean){
        usingSharps = b
    }

    fun getDiatonicIntervals() = diatonicIntervals

    fun getFormula() = formula

    fun getDiatonicNotes() = diatonicNotes

    fun getDiatonicChords() = diatonicChords

    fun getCleanNotes() = cleanNotes

    fun getSteps() = steps

    fun setRoot(rootNote: String){ this.rootNote = rootNote; build() }

    fun setMode(modeType: String){ this.modeType = modeType; build() }

    private fun setSteps(){
        steps.clear()

        // WACKY THREE
        when (modeType) {
            "Chromatic" -> steps = mutableListOf("h", "h", "h", "h", "h", "h", "h", "h", "h", "h", "h", "h")
            "Whole Tone" -> steps = mutableListOf("W", "W", "W", "W", "W", "W", "W")
            "Diminished" -> steps = mutableListOf("W", "h", "W", "h", "W", "h", "W", "h")
            // MAJOR MODES
            in allMajorModes -> {
                val majorSteps = mutableListOf("W", "W", "h", "W", "W", "W", "h")
                var offset = allMajorModes.indexOf(modeType)
                for (i in 0 until majorSteps.size){
                    if (i+offset >= majorSteps.size)
                        offset -= majorSteps.size
                    steps.add(majorSteps[i + offset])
                }
            }
            "Major Pentatonic" -> steps = mutableListOf("W", "W", "m3", "W", "m3")
            "Minor Pentatonic" -> steps = mutableListOf("m3", "W", "W", "m3", "W")
            "Major Blues" -> steps = mutableListOf("W", "h", "h", "m3", "W", "m3")
            "Minor Blues" -> steps = mutableListOf("m3", "W", "h", "h", "m3", "W")
        }

    }

    private fun setFormula() {
        formula.clear()

        when (modeType) {
            "Major/Ionian" -> formula = mutableListOf("1", "2", "3", "4", "5", "6", "7")
            "Dorian" -> formula = mutableListOf("1", "2", "b3", "4", "5", "6", "b7")
            "Phrygian" -> formula = mutableListOf("1", "b2", "b3", "4", "5", "b6", "b7")
            "Lydian" -> formula = mutableListOf("1", "2", "3", "#4", "5", "6", "7")
            "Mixolydian" -> formula = mutableListOf("1", "2", "3", "4", "5", "6", "b7")
            "Minor/Aeolian" -> formula = mutableListOf("1", "2", "b3", "4", "5", "b6", "b7")
            "Locrian" -> formula = mutableListOf("1", "b2", "b3", "4", "b5", "b6", "b7")

            "Major Pentatonic" -> formula = mutableListOf("1", "2", "3", "5", "6")
            "Minor Pentatonic" -> formula = mutableListOf("1", "b3", "4", "5", "b7")

            "Major Blues" -> formula = mutableListOf("1", "2", "b3", "3", "5", "6")
            "Minor Blues" -> formula = mutableListOf("1", "b3", "4", "b5", "5", "b7")
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

    private fun setDiatonicIntervals(){
        diatonicIntervals.clear()  //

        when (modeType) {
            "Major/Ionian" -> diatonicIntervals = mutableListOf("1", "2", "3", "4", "5", "6", "7")
            "Dorian" -> diatonicIntervals = mutableListOf("1", "2", "b3", "4", "5", "6", "b7")
            "Phrygian" -> diatonicIntervals = mutableListOf("1", "b2", "b3", "4", "5", "b6", "b7")
            "Lydian" -> diatonicIntervals = mutableListOf("1", "2", "3", "#4", "5", "6", "7")
            "Mixolydian" -> diatonicIntervals = mutableListOf("1", "2", "3", "4", "5", "6", "b7")
            "Minor/Aeolian" -> diatonicIntervals = mutableListOf("1", "2", "b3", "4", "5", "b6", "b7")
            "Locrian" -> diatonicIntervals = mutableListOf("1", "b2", "b3", "4", "b5", "b6", "b7")

            "Major Pentatonic" -> diatonicIntervals = mutableListOf("1", "2", "3", "5", "6")
            "Minor Pentatonic" -> diatonicIntervals = mutableListOf("1", "b3", "4", "5", "b7")

            "Major Blues" -> diatonicIntervals = mutableListOf("1", "2", "b3", "3", "5", "6")
            "Minor Blues" -> diatonicIntervals = mutableListOf("1", "b3", "4", "b5", "5", "b7")
        }
    }
}

fun main(){
    //val scale = Scale(rootNote = "C", modeType = "Major/Ionian")
}