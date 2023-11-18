import java.awt.Color
import java.awt.Graphics

class TextDisplay(
    private val scale: Scale,
    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
    private val specialNoteColor: Color,

    private val topTextNotesXDist: Int = 60,
    private val topTextNotesYDist: Int = 100,

    private val textColor: Color = Color(235, 235, 235),
    ) {

    // C D E F ...
    fun drawTopTextNotes(g: Graphics) {

        g.color = regNoteColor

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until scale.getDiatonicNotes().size){

            if (scale.getDiatonicIntervals()[i].length > 1)
                g.color = specialNoteColor

            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && i == 3) {
                g.color = blueNoteColor
            }
            if (scale.getMode() == "Major Blues" && i == 2) {
                g.color = blueNoteColor
            }
            if (i == 0)
                g.color = rootNoteColor

            val note: String = scale.getCleanNotes()[i]
            g.drawString(
                note,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 50
            )

            g.color = regNoteColor
        }
    }

    // C D E F ...
    fun drawTopTextRelativeMajorNotes(g: Graphics) {
        val relativeMajor = scale.getRelativeMajor()

        g.color = textColor

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until relativeMajor.getDiatonicNotes().size){

            val note: String = relativeMajor.getCleanNotes()[i]
            g.drawString(
                note,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist
            )
        }
    }

    // W W h W ...
    fun drawTopTextIntervals(g: Graphics) {
        g.color = textColor

        for (i in 0 until scale.getFormulaStrings().size) {

            g.drawString(
                scale.getFormulaStrings()[i] + " ",
                83 + (i * topTextNotesXDist),
                topTextNotesYDist + 25
            )

        }
    }

    // 1 2 b3 4 ...
    fun drawTopTextFormula(g: Graphics){
        g.color = textColor

        for (i in 0 until scale.getDiatonicIntervals().size){

            if (scale.getDiatonicIntervals()[i].length > 1)
                g.color = specialNoteColor
            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && i == 3) {
                g.color = blueNoteColor
            }
            if (scale.getMode() == "Major Blues" && i == 2) {
                g.color = blueNoteColor
            }


            val interval: String = scale.getDiatonicIntervals()[i]
            g.drawString(
                interval,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 75)

            g.color = textColor
        }
    }

    // R W M3 P4 ...
    fun drawTopTextIntraScaleIntervals(g: Graphics){

        g.color = textColor

        var intervalTotal = 0

        g.drawString(
            "R",
            topTextNotesXDist - 10,
            topTextNotesYDist + 105
        )

        for (i in 0 until scale.getFormulaInts().size-1){
            intervalTotal += scale.getFormulaInts()[i]
            g.drawString(
                scale.getIntervalStrings()[intervalTotal]!!,
                50 + ((i+1) * topTextNotesXDist),
                topTextNotesYDist + 105
            )
        }
    }

    // I ii iii IV ...
    fun drawTopTextChordNumerals(g: Graphics){

        g.color = textColor

        for (i in 0 until scale.getDiatonicChords().size){

            val note: String = scale.getDiatonicChords()[i]
            g.drawString(
                note,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 135
            )

        }
    }

}