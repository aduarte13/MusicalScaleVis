import java.awt.Color
import java.awt.Graphics

class FretboardDisplay(
    private val scale: Scale,

    private val fretboard_x_offset: Int = 3,        // top left of fretboard
    private val fretboard_y_offset: Int = 365,      // corner of fretboard
    private val fretboard_note_x_dist: Int = 55,     // horizontal distance between notes
    private val fretboard_note_y_dist: Int = 35,     // vertical distance between notes
    private val note_size: Int = 42,                // size of notes on fretboard

    private val root_note_color: Color,
    private val reg_note_color: Color,
    private val blue_note_color: Color,

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
        //drawFretboardNotes(g, eString, 0)
        //drawFretboardNotes(g, bString, 1)
        //drawFretboardNotes(g, gString, 2)
        //drawFretboardNotes(g, dString, 3)
        //drawFretboardNotes(g, aString, 4)
        //drawFretboardNotes(g, eString, 5)

        for (i in allGuitarStrings.indices){
            drawFretboardNotes(g, allGuitarStrings[i], i)
        }
    }

    fun drawFretboardNoteNames(g: Graphics) {
        //drawFretboardNoteStrings(g, eString, 0)
        //drawFretboardNoteStrings(g, bString, 1)
        //drawFretboardNoteStrings(g, gString, 2)
        //drawFretboardNoteStrings(g, dString, 3)
        //drawFretboardNoteStrings(g, aString, 4)
        //drawFretboardNoteStrings(g, eString, 5)

        for (i in allGuitarStrings.indices){
            drawFretboardNoteStrings(g, allGuitarStrings[i], i)
        }

    }


    private fun drawFretboardNotes(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ){
        val yOffset = guitarStringNum * fretboard_note_y_dist

        for (i in 0..12){
            // HIGHLIGHT ROOT
            if (guitarString[i] == scale.getRoot()){
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }
            // REGULAR DIATONIC NOTES
            else if (guitarString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }

            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && guitarString[i] == scale.getDiatonicNotes()[3]) {
                drawGuitarNote(
                        g,
                        blue_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }
            if (scale.getMode() == "Major Blues" && guitarString[i] == scale.getDiatonicNotes()[2]) {
                drawGuitarNote(
                        g,
                        blue_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
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
        val yOffset = guitarStringNum * fretboard_note_y_dist

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
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size / 4) + 2 + xOffset,
                        fretboard_y_offset + (note_size / 2) + 9 + yOffset
                )
            }
        }
    }

    fun drawFretboardDegrees(g: Graphics) {

        val offset = 3  // for centering accidental strings on note

        g.color = Color.black

        for (i in 0..12) {
            if (eString[i] in scale.getDiatonicNotes()) {
                val note = eString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9
                )
                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (5 * fretboard_note_y_dist)
                )
            }
            if (bString[i] in scale.getDiatonicNotes()) {
                //     fretboard_x_offset + i * fretboard_note_x_dist,
                //     fretboard_y_offset + fretboard_note_y_dist
                val note = bString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + fretboard_note_y_dist
                )
            }
            if (gString[i] in scale.getDiatonicNotes()) {
                //      fretboard_x_offset + i * fretboard_note_x_dist,
                //      fretboard_y_offset + (2 * fretboard_note_y_dist)
                val note = gString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (2 * fretboard_note_y_dist)
                )
            }
            if (dString[i] in scale.getDiatonicNotes()){
                //     fretboard_x_offset + i * fretboard_note_x_dist,
                //     fretboard_y_offset + (3 * fretboard_note_y_dist)
                val note = dString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (3 * fretboard_note_y_dist)
                )
            }
            if (aString[i] in scale.getDiatonicNotes()){
                //   fretboard_x_offset + i * fretboard_note_x_dist,
                //   fretboard_y_offset + (4 * fretboard_note_y_dist)
                val note = aString[i]

                g.drawString(
                        "" + (scale.getDiatonicNotes().indexOf(note) + 1),
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (4 * fretboard_note_y_dist)
                )
            }
        }
    }

    fun drawFretboard(g: Graphics) {
        g.color = Color.lightGray
        g.drawRect(                     // perimeter of fretboard, includes e strings
                fretboard_x_offset + (note_size),   // CAREFUL W/ TRUNCATION
                fretboard_y_offset + (note_size / 2),
                (fretboard_note_x_dist * 12) + 4,
                fretboard_note_y_dist * 5
        )
        for (i in 0..3)         // other guitar strings
            g.drawLine(
                    fretboard_x_offset + (note_size / 2) + 29,
                    fretboard_y_offset + note_size + 15 + (i * fretboard_note_y_dist),
                    fretboard_x_offset + (note_size / 2) + (fretboard_note_x_dist * 12) + 24,
                    fretboard_y_offset + note_size + 15 + (i * fretboard_note_y_dist)
            )
        // draw fret lines for fretboard
        for (i in 0..11)
            g.drawLine(
                    fretboard_x_offset + fretboard_note_x_dist * i + note_size + 7,
                    fretboard_y_offset + (note_size / 2),
                    fretboard_x_offset + fretboard_note_x_dist * i + note_size + 7,
                    fretboard_y_offset + (note_size / 2) + (fretboard_note_y_dist * 5)
            )
        // draw fret markings
        for (i in 0..3)  // draw circles for fret markings on fret 3, 5, 7, and 9
        //g.fillOval(181 + i * 110, 250, 11, 11)
            g.fillOval(
                    fretboard_note_x_dist + (fretboard_note_x_dist * 2) + (note_size / 2) + (fretboard_note_x_dist * 2 * i) - 2,
                    fretboard_y_offset - (note_size / 2),
                    11,
                    11
            )
        g.fillOval(667, fretboard_y_offset - (note_size / 2), 11, 11) // draw circles for fret
        g.fillOval(687, fretboard_y_offset - (note_size / 2), 11, 11) // markings on fret 12
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