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

    // 1 2 b3 4 ...
    fun drawTopTextFormula(g: Graphics){
        g.color = Color.white

        for (i in 0 until scale.getDiatonicIntervals().size){

            val interval: String = scale.getDiatonicIntervals()[i]
            g.drawString(interval, 50 + (i * topTextNotesXDist), 75)

        }
    }

    // I ii iii IV ...
    fun drawTopTextChordNumerals(g: Graphics){

        g.color = Color.white

        for (i in 0 until scale.getDiatonicChords().size){

            val note: String = scale.getDiatonicChords()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 135)

        }
    }

}