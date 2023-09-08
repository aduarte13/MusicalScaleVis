import java.awt.Color
import java.awt.Graphics

class PianoDisplay(
    private val scale: Scale,

    private val xOffset: Int = 3,        // top left
    private val yOffset: Int = 365,      // corner
    private val noteXDist: Int = 55,     // horizontal distance between notes
    private val noteYDist: Int = 35,     // vertical distance between notes
    private val noteSize: Int = 42,      // size of note visual

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
) {
    private val allNotes = arrayOf("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    fun drawPianoOutline(g: Graphics){
        g.color = Color.lightGray
        g.drawRect(                     // perimeter of fretboard, includes e strings
            xOffset + (noteSize),   // CAREFUL W/ TRUNCATION
            yOffset + (noteSize / 2),
            (noteXDist * 12) + 4,
            noteYDist * 5
        )
    }
}