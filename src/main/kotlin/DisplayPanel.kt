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
    private var hidden: Boolean = false,
    private var usingFretboard: Boolean = false
    ) : JPanel(){

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
        val pianoDisplay = PianoDisplay(
            scale,
            regNoteColor = regNoteColor,
            rootNoteColor = rootNoteColor,
            blueNoteColor = blueNoteColor
        )

        g.color = backgroundColor
        g.fillRect(0, 0, 760, 610) // fill background

        if (!hidden) {
            // TOP TEXT                         // -NOTE-                       -e.g.-
            drawTopTextIntervals(g)             // draw the formula intervals   W W h ...
            drawTopTextNotes(g)                 // draw diatonic note strings   C D E ...
            drawTopTextChordNumerals(g)         // draw chord roman numerals    I ii iii ...
            drawTopTextIntraScaleIntervals(g)   // draw intervals from root     W M3 P4  ...

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

    fun setInstrument(b: Boolean){
        usingFretboard = b
    }

    fun setUsingNoteNames(b: Boolean){
        usingNoteNames = b
    }
}