import java.awt.*
import javax.swing.*


class DisplayPanel(
    private val scale: Scale,
    private val background_color: Color = Color(40, 40, 40),
    private val text_color: Color = Color(235, 235, 235),
    private val root_note_color: Color = Color(225, 15, 0),
    private val reg_note_color: Color = Color(255, 180, 70),
    private val blue_note_color: Color = Color(60, 60, 255),

    private val fretboard_x_offset: Int = 3,        // top left of fretboard
    private val fretboard_y_offset: Int = 365,      // corner of fretboard
    private val fretboard_note_x_dist: Int = 55,     // horizontal distance between notes
    private val fretboard_note_y_dist: Int = 35,     // vertical distance between notes
    private val note_size: Int = 42,                // size of notes on fretboard

    private var usingNoteNames: Boolean = true,
) : JPanel(){
    private val eString = arrayOf("E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E")
    private val aString = arrayOf("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A")
    private val dString = arrayOf("D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D")
    private val gString = arrayOf("G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G")
    private val bString = arrayOf("B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    private var hidden: Boolean = false

    init{
        border = BorderFactory.createEtchedBorder()

    }

    override fun paint(g: Graphics) {
        g.color = background_color // set background color
        g.fillRect(0, 0, 760, 610) // draw background
        drawFretboard(g) // draw fretboard
        if (!hidden) {
            // TOP TEXT
            drawTopTextIntervals(g)             // draw the chord intervals
            drawTopTextNotes(g)                 // draw diatonic note strings
            drawTopTextChordNumerals(g)         // draw chord roman numerals
            drawTopTextIntraScaleIntervals(g)   //

            // HIGHLIGHTING FRETBOARD NOTES
            //drawFretboardNotes(g)    // highlight chord notes on fretboard
            drawFretboardNotes(g, eString, 0)
            drawFretboardNotes(g, bString, 1)
            drawFretboardNotes(g, gString, 2)
            drawFretboardNotes(g, dString, 3)
            drawFretboardNotes(g, aString, 4)
            drawFretboardNotes(g, eString, 5)

            if (usingNoteNames)
                drawFretboardNoteStrings(g)      // draw note names on fretboard notes
            else
                drawFretboardDegrees(g)         // draw degrees on fretboard notes
        }
    }

    private fun drawTopTextChordNumerals(g: Graphics){

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = Color.white

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0..scale.getDiatonicChords().size-1){

            val note: String = scale.getDiatonicChords()[i]
            g.drawString(note, 50 + i * 60, 75)

        }
    }


    private fun drawFretboardNoteStrings(g: Graphics) {

        val accidentals = listOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")
        var offset: Int   // for centering accidental strings on note

        g.color = Color.black

        for (i in 0..12) {
            if (eString[i] in scale.getDiatonicNotes()) {
                var note = eString[i]
                if (note in accidentals){
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    offset = -6
                }
                else offset = 0
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9
                )
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (5 * fretboard_note_y_dist)
                )
            }
            if (bString[i] in scale.getDiatonicNotes()) {
                //     fretboard_x_offset + i * fretboard_note_x_dist,
                //     fretboard_y_offset + fretboard_note_y_dist
                var note = bString[i]
                if (note in accidentals){
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    offset = -6
                }
                else offset = 0
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + fretboard_note_y_dist
                )
            }
            if (gString[i] in scale.getDiatonicNotes()) {
                //      fretboard_x_offset + i * fretboard_note_x_dist,
                //      fretboard_y_offset + (2 * fretboard_note_y_dist)
                var note = gString[i]
                if (note in accidentals){
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    offset = -6
                }
                else offset = 0
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (2 * fretboard_note_y_dist)
                )
            }
            if (dString[i] in scale.getDiatonicNotes()){
                //     fretboard_x_offset + i * fretboard_note_x_dist,
                //     fretboard_y_offset + (3 * fretboard_note_y_dist)
                var note = dString[i]
                if (note in accidentals){
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    offset = -6
                }
                else offset = 0
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (3 * fretboard_note_y_dist)
                )
            }
            if (aString[i] in scale.getDiatonicNotes()){
                //   fretboard_x_offset + i * fretboard_note_x_dist,
                //   fretboard_y_offset + (4 * fretboard_note_y_dist)
                var note = aString[i]
                if (note in accidentals){
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    offset = -6
                }
                else offset = 0
                g.drawString(
                        note,
                        fretboard_x_offset + i * fretboard_note_x_dist + (note_size/4) + 2 + offset,
                        fretboard_y_offset + (note_size/2) + 9 + (4 * fretboard_note_y_dist)
                )
            }
        }
    }

    private fun drawFretboardDegrees(g: Graphics) {

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

    private fun drawTopTextIntraScaleIntervals(g: Graphics){

        g.color = Color.white

        var interval_total = 0
        for (i in 0 until scale.getFormulaInts().size-1){
            interval_total += scale.getFormulaInts()[i]
            println(scale.getIntervalStrings()[interval_total])
        }
    }

    private fun drawTopTextNotes(g: Graphics) {

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = reg_note_color

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0..scale.getDiatonicNotes().size-1){

            val note: String = scale.getCleanNotes().get(i)
            g.drawString(note, 50 + i * 60, 50)

        }
    }

    private fun drawTopTextIntervals(g: Graphics) {
        g.color = text_color
        g.font = Font("Arial", Font.BOLD, 20)
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings().get(i) + " ", 85 + i * 60, 25)
        }
    }

    private fun drawFretboard(g: Graphics) {
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

    fun switchHidden() {
        hidden = !hidden
    }

    fun setUsingNoteNames(b: Boolean){
        usingNoteNames = b
    }
}