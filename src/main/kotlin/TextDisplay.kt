import java.awt.Color
import java.awt.Graphics

class TextDisplay(
    val scale: Scale,
    private val relativeMajor: Scale,
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
        for (i in 0 until scale.getFormula().size){

            /*
            if (scale.getFormula()[i].length > 1)
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
            */

            val note: String = scale.getCleanNotes()[i]
            g.drawString(
                note,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 50
            )

            g.color = regNoteColor
        }
    }

    // C MAJOR
    fun drawStructureName(g: Graphics){
        g.color = textColor

        g.drawString(
            scale.getCleanNotes()[0] + " "+ scale.getMode(),
            topTextNotesXDist - 10,
            topTextNotesYDist - 20
        )
    }

    // C D E F ...
    fun drawTopTextRelativeMajorNotes(g: Graphics) {

        g.color = textColor

        g.drawLine(0, topTextNotesYDist + 160, 850, topTextNotesYDist + 160)

        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0 until relativeMajor.getDiatonicNotes().size){

            val note: String = relativeMajor.getCleanNotes()[i]
            g.drawString(
                note,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 200
            )
        }
    }

    // W W h W ...
    fun drawTopTextIntervals(g: Graphics) {
        g.color = textColor

        for (i in 0 until scale.getSteps().size) {

            g.drawString(
                scale.getSteps()[i] + " ",
                83 + (i * topTextNotesXDist),
                topTextNotesYDist + 25
            )

        }
    }

    // 1 2 b3 4 ...
    fun drawTopTextFormula(g: Graphics){
        g.color = textColor

        for (i in 0 until scale.getFormula().size){
            /*
            if (scale.getFormula()[i].length > 1)
                g.color = specialNoteColor
            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && i == 3) {
                g.color = blueNoteColor
            }
            if (scale.getMode() == "Major Blues" && i == 2) {
                g.color = blueNoteColor
            }
            */

            val interval: String = scale.getFormula()[i]
            g.drawString(
                interval,
                50 + (i * topTextNotesXDist),
                topTextNotesYDist + 75)

            g.color = textColor
        }
    }

    fun highlightTopTextRootInfo(g: Graphics){
        g.color = rootNoteColor
        g.drawString(
            scale.getCleanNotes()[0],
            50,
            topTextNotesYDist + 50)
        g.drawString(
            "1",
            50,
            topTextNotesYDist + 75)
    }

    fun highlightTopTextDevInfo(g: Graphics){
        g.color = specialNoteColor
        for (i in 0 until scale.getFormula().size) {

            if (scale.getFormula()[i].length > 1) {

                val interval: String = scale.getFormula()[i]
                g.drawString(
                    scale.getCleanNotes()[i],
                    50 + (i * topTextNotesXDist),
                    topTextNotesYDist + 50
                )
                g.drawString(
                    interval,
                    50 + (i * topTextNotesXDist),
                    topTextNotesYDist + 75
                )
            }
        }
    }

    fun highlightTopTextBlueInfo(g: Graphics){
        g.color = blueNoteColor
        for (i in 0 until scale.getFormula().size) {

            if (scale.getMode() == "Major Blues" && i == 2
                || scale.getMode() == "Minor Blues" && i == 3) {

                val interval: String = scale.getFormula()[i]
                g.drawString(
                    scale.getCleanNotes()[i],
                    50 + (i * topTextNotesXDist),
                    topTextNotesYDist + 50
                )
                g.drawString(
                    interval,
                    50 + (i * topTextNotesXDist),
                    topTextNotesYDist + 75
                )
            }
        }
    }

    // R W M3 P4 ...
    fun drawTopTextIntraScaleIntervals(g: Graphics){

        g.color = textColor

        for (i in 0 until scale.getDiatonicIntervals().size){
            g.drawString(
                scale.getDiatonicIntervals()[i],
                50 + ((i) * topTextNotesXDist),
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