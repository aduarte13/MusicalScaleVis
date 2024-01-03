import java.awt.*
import javax.swing.*
import javax.swing.text.Highlighter.Highlight

class DisplayPanel(
    private val scale: Scale,
    private val relativeMajor: Scale,
    private val backgroundColor: Color = Color(20, 20, 20),
    private val rootNoteColor: Color = Color(225, 15, 0),
    private val regNoteColor: Color = Color(255, 180, 70),
    private val blueNoteColor: Color = Color(60, 60, 255),
    private val specialNoteColor: Color = Color(195, 50, 195),

    private var usingNoteNames: Boolean = true,
    private var hidden: Boolean = false,
    private var relativeMajorHidden: Boolean = false,
    private var usingFretboard: Boolean = true,

    private var highlightRoot: Boolean = true,
    private var highlightDevs: Boolean = true,
    private var highlightBlue: Boolean = true
    ) : JPanel(){

    init{
        border = BorderFactory.createEtchedBorder()
    }

    override fun paint(g: Graphics) {

        g.font = Font("American Typewriter", Font.BOLD, 24)

        // create display objects
        val textDisplay = TextDisplay(
            scale,
            relativeMajor,
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
        g.fillRect(0, 0, 860, 610) // fill background

        if (!relativeMajorHidden)
            textDisplay.drawTopTextRelativeMajorNotes(g)
        textDisplay.drawTopTextIntervals(g)             // W W h W...
        textDisplay.drawTopTextIntraScaleIntervals(g)   // W M3 P4  ...
        textDisplay.drawTopTextFormula(g)               // 1 2 b3 4 ...

        if (!hidden) {
            // TOP TEXT                                     // -NOTE-                       -e.g.-
            textDisplay.drawTopTextNotes(g)                 // draw diatonic note strings   C D E ...
            textDisplay.drawTopTextChordNumerals(g)         // draw chord roman numerals    I ii iii ...

            textDisplay.drawStructureName(g)

            if (usingFretboard) {
                // FRETBOARD DISPLAY
                fretboardDisplay.drawFretboard(g)               // fretboard
                fretboardDisplay.drawFretboardGuitarStrings(g)  // guitar notes
                // FRETBOARD
                if (highlightRoot)
                    fretboardDisplay.highlightFretboardRoots(g)
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

    fun switchRelativeMajorHidden(){
        relativeMajorHidden = !relativeMajorHidden
    }

    fun setInstrument(b: Boolean){
        usingFretboard = b
    }

    fun setUsingNoteNames(b: Boolean){
        usingNoteNames = b
    }
}