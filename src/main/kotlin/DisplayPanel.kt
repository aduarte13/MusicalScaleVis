import java.awt.*
import javax.swing.*


public class DisplayPanel(
    private val scale: Scale
) : JPanel(){
    private val eString = arrayOf("E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E")
    private val aString = arrayOf("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A")
    private val dString = arrayOf("D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D")
    private val gString = arrayOf("G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G")
    private val bString = arrayOf("B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    private var hidden: Boolean = true

    init{

        setBorder(BorderFactory.createEtchedBorder());
    }

    override fun paint(g: Graphics) {
        g.color = Color.darkGray // set background color
        g.fillRect(0, 0, 760, 510) // draw background
        drawFretboard(g) // draw fretboard
        if (hidden == false) {
            drawStringIntervals(g) // draw the chord intervals
            //drawChordNotes(g) // display chord notes
            //drawChordFormulaSteps(g) // display chord formula steps
            //highlightFretboardNotes(g) // highlight chord notes on fretboard
            //highlightFretboardRoots(g) // highlight root notes on fretboard
            // drawFretboardDegrees(g);            // draw degree numbers on fretboard
        }
    }

    fun drawStringIntervals(g: Graphics) {
        g.color = Color(235, 235, 235)
        g.font = Font("Arial", Font.BOLD, 20)
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings().get(i) + " ", 55 + i * 60, 25)
        }
    }

    fun drawFretboard(g: Graphics) {
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
}