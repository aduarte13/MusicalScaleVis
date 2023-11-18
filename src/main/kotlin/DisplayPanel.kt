import java.awt.*
import javax.swing.*

class DisplayPanel(
    private val scale: Scale,
    private val backgroundColor: Color = Color(20, 20, 20),
    private val rootNoteColor: Color = Color(225, 15, 0),
    private val regNoteColor: Color = Color(255, 180, 70),
    private val blueNoteColor: Color = Color(60, 60, 255),
    private val specialNoteColor: Color = Color(195, 50, 195),

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
            blueNoteColor = blueNoteColor,
            specialNoteColor = specialNoteColor
        )
        val fretboardDisplay = FretboardDisplay(
            scale,
            regNoteColor = regNoteColor,
            rootNoteColor = rootNoteColor,
            blueNoteColor = blueNoteColor,
            specialNoteColor = specialNoteColor
        )
        val pianoDisplay = PianoDisplay(
            scale,
            regNoteColor = regNoteColor,
            rootNoteColor = rootNoteColor,
            blueNoteColor = blueNoteColor,
            specialNoteColor = specialNoteColor
        )

        g.color = backgroundColor
        g.fillRect(0, 0, 760, 610) // fill background

        textDisplay.drawTopTextIntervals(g)             // W W h W...
        textDisplay.drawTopTextIntraScaleIntervals(g)   // W M3 P4  ...
        textDisplay.drawTopTextFormula(g)               // 1 2 b3 4 ...

        val relativeMajor = Scale(rootNote = scale.getRoot(), modeType = "Major/Ionian")

        if (!hidden) {
            // TOP TEXT                                     // -NOTE-                       -e.g.-
            textDisplay.drawTopTextNotes(g)                 // draw diatonic note strings   C D E ...
            textDisplay.drawTopTextChordNumerals(g)         // draw chord roman numerals    I ii iii ...

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