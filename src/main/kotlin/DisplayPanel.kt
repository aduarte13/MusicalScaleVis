import java.awt.*
import javax.swing.*


class DisplayPanel(
    private val scale: Scale,
    private val background_color: Color = Color(40, 40, 40),
    private val text_color: Color = Color(235, 235, 235),
    private val root_note_color: Color = Color(225, 15, 0),
    private val reg_note_color: Color = Color(235, 200, 90)
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
        g.fillRect(0, 0, 760, 510) // draw background
        drawFretboard(g) // draw fretboard
        if (hidden == false) {
            drawStringIntervals(g)          // draw the chord intervals
            drawChordNotes(g)               // display chord notes
            highlightFretboardNotes(g)    // highlight chord notes on fretboard
            //highlightFretboardRoots(g)    // highlight root notes on fretboard
            //drawFretboardDegrees(g);      // draw degree numbers on fretboard
        }
    }

    private fun drawGuitarNote(g: Graphics, c: Color, x: Int, y: Int){
        val size = 42
        val border_width = 4

        // draw border (will be covered by other circle)
        g.color = Color.white
        g.fillOval(x, y, size, size)

        // draw inner circle
        g.color = c
        g.fillOval(x + (border_width/2), y + (border_width/2), size - border_width, size - border_width)
    }

    private fun highlightFretboardNotes(g: Graphics) {
        g.color = Color(20, 20, 20)

        for (i in 0..12) {
            if (eString[i] in scale.getDiatonicNotes()) {
                drawGuitarNote(g, reg_note_color, 3 + i * 55, 265,
                )
                drawGuitarNote(g, reg_note_color, 3 + i * 55, 445)
            }
            // !!! CONTINUE HERE !!!
            if (bString[i] in scale.getDiatonicNotes()) {
                g.fillRect(
                    3 + i * 55,
                    301,
                    41,
                    22
                )
            }
            g.color = Color(20, 20, 20)
            if (gString[i] in scale.getDiatonicNotes()) g.fillRect(
                3 + i * 55,
                337,
                41,
                22
            )
            if (dString[i] in scale.getDiatonicNotes()) g.fillRect(
                3 + i * 55,
                373,
                41,
                22
            )
            if (aString[i] in scale.getDiatonicNotes()) g.fillRect(
                3 + i * 55,
                409,
                41,
                22
            )
        }
        g.color = reg_note_color
        for (i in 0..12) {
            if (eString[i] in scale.getDiatonicNotes()) {
                g.fillRect(
                    4 + i * 55,
                    265,
                    39,
                    20
                )
                g.fillRect(
                    4 + i * 55,
                    445,
                    39,
                    20
                )
            }
            if (bString[i] in scale.getDiatonicNotes()) g.fillRect(
                4 + i * 55,
                301,
                39,
                20
            )
            if (gString[i] in scale.getDiatonicNotes()) g.fillRect(
                4 + i * 55 ,
                337,
                39,
                20
            )
            if (dString[i] in scale.getDiatonicNotes()) g.fillRect(
                4 + i * 55,
                373,
                39,
                20
            )
            if (aString[i] in scale.getDiatonicNotes()) g.fillRect(
                4 + i * 55,
                409,
                39,
                20
            )
        }
    }

    private fun drawChordNotes(g: Graphics) {
        var flatsOrSharps = 0 // 0 = undecided; 1 = sharps; 2 = flats
        val accidentals = arrayOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")

        // INSERT SHARPS OR FLAT DECISION LOGIC HERE

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = reg_note_color
        for (i in 0 until scale.getDiatonicNotes().size) {
            var note: String = scale.getDiatonicNotes().get(i)
            when (note) {
                "C#/Db" -> {
                    note = if (flatsOrSharps == 1) "C#" else "Db"
                }

                "D#/Eb" -> {
                    note = if (flatsOrSharps == 1) "D#" else "Eb"
                }

                "F#/Gb" -> {
                    note = if (flatsOrSharps == 1) "F#" else "Gb"
                }

                "G#/Ab" -> {
                    note = if (flatsOrSharps == 1) "G#" else "Ab"
                }

                "A#/Bb" -> {
                    note = if (flatsOrSharps == 1) "A#" else "Bb"
                }

                else -> {}
            }
            g.drawString(note, 50 + i * 60, 50)
        }
    }

    private fun drawStringIntervals(g: Graphics) {
        g.color = text_color
        g.font = Font("Arial", Font.BOLD, 20)
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings().get(i) + " ", 55 + i * 60, 25)
        }
    }

    private fun drawFretboard(g: Graphics) {
        g.color = Color.LIGHT_GRAY // set color
        g.drawRect(50, 275, 654, 180) // draw rectangle for perimeter of fretboard
        for (i in 0..3) g.drawLine(50, 311 + i * 36, 704, 311 + i * 36) // draw lines for the guitar strings
        // draw fret lines for fretboard
        for (i in 0..11) g.drawLine(50 + i * 55, 275, 50 + i * 55, 455) // draw lines for guitar frets
        // draw fret markings
        for (i in 0..3)  // draw circles for fret markings on fret 3, 5, 7, and 9
            g.fillOval(181 + i * 110, 250, 11, 11)
        g.fillOval(667, 250, 11, 11) // draw circles for fret
        g.fillOval(687, 250, 11, 11) // markings on fret 12
    }

    fun switchHidden() {
        hidden = !hidden
    }
}