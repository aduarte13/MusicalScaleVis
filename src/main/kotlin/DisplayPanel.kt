import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import javax.swing.BorderFactory
import javax.swing.JPanel

class DisplayPanel(

    private val scale: Scale,
    private val relativeMajor: Scale,
    private val backgroundColor: Color = Color(20, 20, 20),
    private val rootNoteColor: Color = Color(220, 45, 30),
    private val regNoteColor: Color = Color(255, 180, 70),
    private val blueNoteColor: Color = Color(60, 110, 255),
    private val specialNoteColor: Color = Color(220, 80, 220),

    private var usingNoteNames: Boolean = true,
    private var relativeMajorHidden: Boolean = false,
    private var usingFretboard: Boolean = true,

    private var highlightRoot: Boolean = true,
    private var highlightDevs: Boolean = true,
    private var highlightBlue: Boolean = true,

    private var showAll: Boolean = true,
    private var showSteps: Boolean = true,
    private var showFormula: Boolean = true,
    private var showChords: Boolean = true,

    // create display objects
    private val textDisplay: TextDisplay = TextDisplay(
        scale,
        relativeMajor,
        regNoteColor = regNoteColor,
        rootNoteColor = rootNoteColor,
        blueNoteColor = blueNoteColor,
        specialNoteColor = specialNoteColor
    ),
    private val fretboardDisplay: FretboardDisplay = FretboardDisplay(
        scale,
        regNoteColor = regNoteColor,
        rootNoteColor = rootNoteColor,
        blueNoteColor = blueNoteColor,
        specialNoteColor = specialNoteColor
    ),
    private val pianoDisplay: PianoDisplay = PianoDisplay(
        scale,
        regNoteColor = regNoteColor,
        rootNoteColor = rootNoteColor,
        blueNoteColor = blueNoteColor,
        specialNoteColor = specialNoteColor
    )
) : JPanel(){

    init{
        border = BorderFactory.createEtchedBorder()
    }

    override fun paint(g: Graphics) {

        g.font = Font("SansSerif", Font.BOLD, 24)

        g.color = backgroundColor
        g.fillRect(0, 0, 860, 610) // fill background

        if (!relativeMajorHidden)
            textDisplay.drawTopTextRelativeMajorNotes(g)

        textDisplay.drawStructureName(g)                // C Major

        if (showSteps)
            textDisplay.drawTopTextIntervals(g)             // W W h W...
        if (showFormula) {
            textDisplay.drawTopTextIntraScaleIntervals(g)   // W M3 P4  ...
            textDisplay.drawTopTextFormula(g)               // 1 2 b3 4 ...
        }
        if (showChords)
            textDisplay.drawTopTextChordNumerals(g)         // I ii iii ...

        if(showAll) {
            textDisplay.drawTopTextNotes(g)                 // draw diatonic note strings   C D E ...
            if (showFormula) {
                if (highlightRoot)
                    textDisplay.highlightTopTextRootInfo(g)
                if (highlightDevs)
                    textDisplay.highlightTopTextDevInfo(g)
                if (highlightBlue)
                    textDisplay.highlightTopTextBlueInfo(g)
            }
        }
        if (showAll) {

            if (usingFretboard) {
                // FRETBOARD DISPLAY
                fretboardDisplay.drawFretboard(g)
                fretboardDisplay.drawFretboardGuitarStrings(g)  // guitar notes
                // FRETBOARD ROOTS
                if (highlightRoot) {
                    fretboardDisplay.highlightFretboardRoots(g)
                }
                if (highlightDevs) {
                    fretboardDisplay.highlightFretboardDevs(g)
                }
                if (highlightBlue) {
                    fretboardDisplay.highlightFretboardBlue(g)
                }
                // NAMES OR DEGREES
                if (usingNoteNames) {
                    fretboardDisplay.drawFretboardNoteNames(g)      // draw note names on fretboard notes
                } else {
                    fretboardDisplay.drawFretboardDegrees(g)        // draw degrees on fretboard notes
                }
            }
            else {
                // PIANO DISPLAY
                pianoDisplay.highlightPiano(g)
                pianoDisplay.drawPianoOutline(g, Color.black)
            }
        }
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

    fun switchSetting(c: Char){
        when (c){
            // HIGHLIGHT OPTIONS
            'r' -> highlightRoot = !highlightRoot
            'd' -> highlightDevs = !highlightDevs
            'b' -> highlightBlue = !highlightBlue
            // INFO OPTIONS
            'a' -> {showAll = !showAll
                if (!showAll){
                    showSteps = false
                    showFormula = false
                    showChords = false
                }
                else{
                    showSteps = true
                    showFormula = true
                    showChords = true
                }
            }
            's' -> showSteps = !showSteps
            'f' -> showFormula = !showFormula
            'c' -> showChords = !showChords
        }
    }

}