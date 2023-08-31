import java.awt.*
import javax.swing.*


class DisplayPanel(
        private val scale: Scale,
        private val backgroundColor: Color = Color(40, 40, 40),
        private val textColor: Color = Color(235, 235, 235),
        private val rootNoteColor: Color = Color(225, 15, 0),
        private val regNoteColor: Color = Color(255, 180, 70),
        private val blueNoteColor: Color = Color(60, 60, 255),

        private val topTextNotesXDist: Int = 60,

        private var usingNoteNames: Boolean = true,

        ) : JPanel(){

    private var hidden: Boolean = false

    init{
        border = BorderFactory.createEtchedBorder()
    }

    override fun paint(g: Graphics) {

        val fretboardDisplay = FretboardDisplay(
                scale,
                reg_note_color = regNoteColor,
                root_note_color = rootNoteColor,
                blue_note_color = blueNoteColor
        )
        //val pianoDisplay = PianoDisplay(scale)

        g.color = backgroundColor // set background color
        g.fillRect(0, 0, 760, 610) // draw background

        fretboardDisplay.drawFretboard(g) // draw fretboard

        if (!hidden) {
            // TOP TEXT
            drawTopTextIntervals(g)             // draw the chord intervals
            drawTopTextNotes(g)                 // draw diatonic note strings
            drawTopTextChordNumerals(g)         // draw chord roman numerals
            drawTopTextIntraScaleIntervals(g)   //

            // HIGHLIGHTING FRETBOARD NOTES
            //drawFretboardNotes(g)    // highlight chord notes on fretboard
            fretboardDisplay.drawFretboardGuitarStrings(g)

            if (usingNoteNames) {
                fretboardDisplay.drawFretboardNoteNames(g)      // draw note names on fretboard notes
            }
            else{
                fretboardDisplay.drawFretboardDegrees(g)         // draw degrees on fretboard notes
            }
        }
    }

    private fun drawTopTextChordNumerals(g: Graphics){

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = Color.white

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicChords().size){

            val note: String = scale.getDiatonicChords()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 75)

        }
    }

    private fun drawTopTextIntraScaleIntervals(g: Graphics){

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = Color.white

        var intervalTotal = 0

        g.drawString("R", 50, 105)

        for (i in 0 until scale.getFormulaInts().size-1){
            intervalTotal += scale.getFormulaInts()[i]
            //println(scale.getIntervalStrings()[interval_total])
            g.drawString(scale.getIntervalStrings()[intervalTotal]!!, 50 + ((i+1) * topTextNotesXDist), 105)
        }
    }

    private fun drawTopTextNotes(g: Graphics) {

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = regNoteColor

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicNotes().size){

            val note: String = scale.getCleanNotes()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 50)

        }
    }

    private fun drawTopTextIntervals(g: Graphics) {
        g.color = textColor
        g.font = Font("Arial", Font.BOLD, 20)
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings()[i] + " ", 85 + (i * topTextNotesXDist), 25)
        }
    }

    fun switchHidden() {
        hidden = !hidden
    }

    fun setUsingNoteNames(b: Boolean){
        usingNoteNames = b
    }
}