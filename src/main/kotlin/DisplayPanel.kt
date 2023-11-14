import java.awt.*
import javax.swing.*

class DisplayPanel(
    private val scale: Scale,
    private val backgroundColor: Color = Color(20, 20, 20),
    private val textColor: Color = Color(235, 235, 235),
    private val rootNoteColor: Color = Color(225, 15, 0),
    private val regNoteColor: Color = Color(255, 180, 70),
    private val blueNoteColor: Color = Color(60, 60, 255),

    private val topTextNotesXDist: Int = 60,

    private var usingNoteNames: Boolean = true,
    private var hidden: Boolean = false,
    private var usingFretboard: Boolean = true
    ) : JPanel(){

    init{
        border = BorderFactory.createEtchedBorder()
    }

    override fun paint(g: Graphics) {

        g.font = Font("American Typewriter", Font.BOLD, 24)

        // create display objects
        val textDisplay = TextDisplay(
            scale,
            regNoteColor = regNoteColor,
            rootNoteColor = rootNoteColor,
            blueNoteColor = blueNoteColor
        )
        val fretboardDisplay = FretboardDisplay(
            scale,
            reg_note_color = regNoteColor,
            root_note_color = rootNoteColor,
            blue_note_color = blueNoteColor
        )
        val pianoDisplay = PianoDisplay(
            scale,
            regNoteColor = regNoteColor,
            rootNoteColor = rootNoteColor,
            blueNoteColor = blueNoteColor
        )

        g.color = backgroundColor
        g.fillRect(0, 0, 760, 610) // fill background

        drawTopTextIntervals(g)             // W W h W...
        drawTopTextIntraScaleIntervals(g)   // W M3 P4  ...
        drawTopTextFormula(g)               // 1 2 b3 4 ...

        if (!hidden) {
            // TOP TEXT                         // -NOTE-                       -e.g.-
            drawTopTextNotes(g)                 // draw diatonic note strings   C D E ...
            drawTopTextChordNumerals(g)         // draw chord roman numerals    I ii iii ...

            if (usingFretboard) {
                // FRETBOARD DISPLAY
                fretboardDisplay.drawFretboard(g)               // fretboard
                fretboardDisplay.drawFretboardGuitarStrings(g)  // guitar notes

                if (usingNoteNames) {
                    fretboardDisplay.drawFretboardNoteNames(g)      // draw note names on fretboard notes
                } else {
                    fretboardDisplay.drawFretboardDegrees(g)        // draw degrees on fretboard notes
                }
            }
            else {
                // PIANO DISPLAY
                pianoDisplay.highlightPiano(g)

                pianoDisplay.drawPianoOutline(g)

            }
        }
    }

    private fun drawTopTextFormula(g: Graphics){   // 1 2 b3 4 ...
        g.color = Color.white

        for (i in 0 until scale.getDiatonicIntervals().size){

            val interval: String = scale.getDiatonicIntervals()[i]
            g.drawString(interval, 50 + (i * topTextNotesXDist), 75)

        }
    }

    private fun drawTopTextChordNumerals(g: Graphics){   // I ii iii IV ...

        g.color = Color.white

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicChords().size){

            val note: String = scale.getDiatonicChords()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 135)

        }
    }

    private fun drawTopTextIntraScaleIntervals(g: Graphics){    // R W M3 P4 ...

        g.color = Color.white

        var intervalTotal = 0

        g.drawString("R", 50, 105)

        for (i in 0 until scale.getFormulaInts().size-1){
            intervalTotal += scale.getFormulaInts()[i]
            g.drawString(scale.getIntervalStrings()[intervalTotal]!!, 50 + ((i+1) * topTextNotesXDist), 105)
        }
    }

    private fun drawTopTextNotes(g: Graphics) {   // C D E F ...

        g.color = regNoteColor

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicNotes().size){

            val note: String = scale.getCleanNotes()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 50)

        }
    }

    private fun drawTopTextIntervals(g: Graphics) {   // W W h W ...
        g.color = textColor
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings()[i] + " ", 83 + (i * topTextNotesXDist), 25)
        }
    }

    fun switchHidden() {
        hidden = !hidden
    }

    fun setInstrument(b: Boolean){
        usingFretboard = b
    }

    fun setUsingNoteNames(b: Boolean){
        usingNoteNames = b
    }
}