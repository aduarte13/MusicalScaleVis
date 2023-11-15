import java.awt.Color
import java.awt.Graphics

class TextDisplay(
    private val scale: Scale,

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,

    private val topTextNotesXDist: Int = 60,

    private val textColor: Color = Color(235, 235, 235),
    ) {

    // W W h W ...
    fun drawTopTextIntervals(g: Graphics) {
        g.color = textColor
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings()[i] + " ", 83 + (i * topTextNotesXDist), 25)
        }
    }
}