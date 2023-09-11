import java.awt.Color
import java.awt.Graphics

class PianoDisplay(
    private val scale: Scale,

    private val xOffset: Int = 30,        // top left
    private val yOffset: Int = 388,      // corner
    private val noteSize: Int = 46,      // horizontal size of note visual
    private val wNoteHeight: Int = noteSize * 4,
    private val bNoteHeight: Int = noteSize * 3 - 15,
    private val whiteToBlackSizeRatio: Int = 2,
    private val blackKeyXOffset: Int = noteSize - noteSize/4,

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
) {
    //private val allNotes = arrayOf("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    fun drawPianoOutline(g: Graphics){
        g.color = Color.lightGray
        // perimenter of piano
        g.drawRect(
            xOffset ,
            yOffset,
            (noteSize * 15),
            noteSize * 4
        )
        // vertical white note lines
        for (i in 1 until 16)
            g.drawLine(
                xOffset +  (i * noteSize),
                yOffset,
                xOffset + (i * noteSize),
                yOffset + wNoteHeight
            )
        for (i in 0 until 2) {
            // c#/db notes
            g.drawRect(
                xOffset + blackKeyXOffset + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // d#/eb notes
            g.drawRect(
                xOffset + blackKeyXOffset + noteSize + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
        }
        for(i in 0 until 2) {
            // f#/gb
            g.drawRect(
                xOffset + blackKeyXOffset + noteSize * 3 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // g#/ab
            g.drawRect(
                xOffset + blackKeyXOffset + noteSize * 4 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // a#/bb
            g.drawRect(
                xOffset + blackKeyXOffset + noteSize * 5 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
        }
    }
}