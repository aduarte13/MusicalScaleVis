import java.awt.Color
import java.awt.Graphics

class TextDisplay(
    private val scale: Scale,

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
    private  val specialNoteColor: Color,

    private val topTextNotesXDist: Int = 60,

    private val textColor: Color = Color(235, 235, 235),
    ) {

    // C D E F ...
    fun drawTopTextNotes(g: Graphics) {

        g.color = regNoteColor

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicNotes().size){

            if (scale.getDiatonicIntervals()[i].length > 1)
                g.color = specialNoteColor

            val note: String = scale.getCleanNotes()[i]
            g.drawString(note, 50 + (i * topTextNotesXDist), 50)

            g.color = regNoteColor
        }
    }

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

    // R W M3 P4 ...
    fun drawTopTextIntraScaleIntervals(g: Graphics){

        g.color = Color.white

        var intervalTotal = 0

        g.drawString("R", 50, 105)

        for (i in 0 until scale.getFormulaInts().size-1){
            intervalTotal += scale.getFormulaInts()[i]
            g.drawString(scale.getIntervalStrings()[intervalTotal]!!, 50 + ((i+1) * topTextNotesXDist), 105)
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