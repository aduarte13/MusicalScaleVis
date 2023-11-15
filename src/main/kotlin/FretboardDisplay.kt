import java.awt.Color
import java.awt.Graphics

class FretboardDisplay(
    private val scale: Scale,

    private val fretboardXOffset: Int = 3,        // top left of fretboard
    private val fretboardYOffset: Int = 365,      // corner of fretboard
    private val fretboardNoteXDist: Int = 55,     // horizontal distance between notes
    private val fretboardNoteYDist: Int = 35,     // vertical distance between notes
    private val noteSize: Int = 42,                // size of notes on fretboard

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
    private  val specialNoteColor: Color

    ) {

    private val eString = arrayOf("E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E")
    private val aString = arrayOf("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A")
    private val dString = arrayOf("D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D")
    private val gString = arrayOf("G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G")
    private val bString = arrayOf("B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    private val allGuitarStrings = arrayOf(
            eString, bString, gString, dString, aString, eString
    )


    fun drawFretboardGuitarStrings(g: Graphics){

        for (i in allGuitarStrings.indices){
            drawFretboardNotes(g, allGuitarStrings[i], i)
        }
    }

    fun drawFretboardNoteNames(g: Graphics) {

        for (i in allGuitarStrings.indices){
            drawFretboardNoteStrings(g, allGuitarStrings[i], i)
        }
    }

    fun drawFretboardDegrees(g: Graphics){
        for (i in allGuitarStrings.indices){
            drawFretboardDegreeStrings(g, allGuitarStrings[i], i)
        }
    }

    private fun drawFretboardNotes(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ){
        val yOffset = guitarStringNum * fretboardNoteYDist

        for (i in 0..12){
            // HIGHLIGHT ROOT
            if (guitarString[i] == scale.getRoot()){
                drawGuitarNote(
                        g,
                        rootNoteColor,
                        fretboardXOffset + i * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                )
            }
            // REGULAR DIATONIC NOTES
            else if (guitarString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        regNoteColor,
                        fretboardXOffset + i * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                )
            }

            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && guitarString[i] == scale.getDiatonicNotes()[3]) {
                drawGuitarNote(
                        g,
                        blueNoteColor,
                        fretboardXOffset + i * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                )
            }
            if (scale.getMode() == "Major Blues" && guitarString[i] == scale.getDiatonicNotes()[2]) {
                drawGuitarNote(
                        g,
                        blueNoteColor,
                        fretboardXOffset + i * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                )
            }
        }
    }


    private fun drawFretboardNoteStrings(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ) {
        val accidentals = listOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")
        var xOffset: Int
        val yOffset = guitarStringNum * fretboardNoteYDist

        g.color = Color.black

        for (i in 0..12) {
            if (guitarString[i] in scale.getDiatonicNotes()) {
                var note = guitarString[i]
                if (note in accidentals) {
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    xOffset = -6
                } else xOffset = 0
                g.drawString(
                        note,
                        fretboardXOffset + i * fretboardNoteXDist + (noteSize / 4) + 2 + xOffset,
                        fretboardYOffset + (noteSize / 2) + 9 + yOffset
                )
            }
        }
    }

    private fun drawFretboardDegreeStrings(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ) {

        val offset = 3  // for centering accidental strings on note

        g.color = Color.black

        for (i in 0..12) {
            if (guitarString[i] in scale.getDiatonicNotes()) {
                val note = guitarString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboardXOffset + i * fretboardNoteXDist + (noteSize/4) + 2 + offset,
                        fretboardYOffset + (noteSize/2) + 9 + (guitarStringNum * fretboardNoteYDist)
                )
            }
        }
    }

    fun drawFretboard(g: Graphics) {
        g.color = Color.lightGray
        g.drawRect(                     // perimeter of fretboard, includes e strings
                fretboardXOffset + (noteSize),   // CAREFUL W/ TRUNCATION
                fretboardYOffset + (noteSize / 2),
                (fretboardNoteXDist * 12) + 4,
                fretboardNoteYDist * 5
        )
        for (i in 0..3)         // other guitar strings
            g.drawLine(
                    fretboardXOffset + (noteSize / 2) + 29,
                    fretboardYOffset + noteSize + 15 + (i * fretboardNoteYDist),
                    fretboardXOffset + (noteSize / 2) + (fretboardNoteXDist * 12) + 24,
                    fretboardYOffset + noteSize + 15 + (i * fretboardNoteYDist)
            )
        // draw fret lines for fretboard
        for (i in 0..11)
            g.drawLine(
                    fretboardXOffset + fretboardNoteXDist * i + noteSize + 7,
                    fretboardYOffset + (noteSize / 2),
                    fretboardXOffset + fretboardNoteXDist * i + noteSize + 7,
                    fretboardYOffset + (noteSize / 2) + (fretboardNoteYDist * 5)
            )
        // draw fret markings
        for (i in 0..3)  // draw circles for fret markings on fret 3, 5, 7, and 9
        //g.fillOval(181 + i * 110, 250, 11, 11)
            g.fillOval(
                    fretboardNoteXDist + (fretboardNoteXDist * 2) + (noteSize / 2) + (fretboardNoteXDist * 2 * i) - 2,
                    fretboardYOffset - (noteSize / 2),
                    11,
                    11
            )
        g.fillOval(667, fretboardYOffset - (noteSize / 2), 11, 11) // draw circles for fret
        g.fillOval(687, fretboardYOffset - (noteSize / 2), 11, 11) // markings on fret 12
    }


    private fun drawGuitarNote(g: Graphics, c: Color, x: Int, y: Int){
        val size = 42
        val borderWidth = 4

        // draw border (will be covered by other circle)
        g.color = Color.white
        g.fillOval(x, y, size, size)

        // draw inner circle
        g.color = c
        // !!! CAREFUL WITH TRUNCATION HERE
        g.fillOval(x + (borderWidth/2), y + (borderWidth/2), size - borderWidth, size - borderWidth)
    }

}