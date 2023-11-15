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
    private  val specialNoteColor: Color

) {
    private val whiteNotes = arrayOf("C", "D", "E", "F", "G", "A", "B")
    private val blackNotes = arrayOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")

    fun highlightPiano(g: Graphics){
        var noteColor: Color

        fillWhiteKeys(g)

        // white notes
        for(note in scale.getDiatonicNotes()){
            // get appropriate color
            noteColor = if (note == scale.getRoot())
                rootNoteColor
            else if (scale.getMode() == "Minor Blues" && note == scale.getDiatonicNotes()[3])
                blueNoteColor
            else if (scale.getMode() == "Major Blues" && note == scale.getDiatonicNotes()[2])
                blueNoteColor
            else
                regNoteColor

            // highlight white diatonic notes
            if (note in whiteNotes)
                highlightWhitePianoKey(g, note, noteColor)
        }

        fillBlackKeys(g)
        // black notes
        for(note in scale.getDiatonicNotes()){
            // get appropriate color
            noteColor = if (note == scale.getRoot())
                rootNoteColor
            else if (scale.getMode() == "Minor Blues" && note == scale.getDiatonicNotes()[3])
                blueNoteColor
            else if (scale.getMode() == "Major Blues" && note == scale.getDiatonicNotes()[2])
                blueNoteColor
            else
                regNoteColor

            // highlight black diatonic notes
            if (note in blackNotes)
                highlightBlackPianoKey(g, note, noteColor)

        }
    }

    private fun highlightWhitePianoKey(g: Graphics, note: String, color: Color){
        g.color = color

        val noteHeight: Int = wNoteHeight
        val noteWidth: Int = noteSize
        val keyPlace = whiteNotes.indexOf(note)

        var count = 2
        if (note == "C")
            count++

        for (i in 0 until count){
            g.fillRect(
                xOffset +  (i * 7 * noteSize) + (keyPlace * noteSize),
                yOffset,
                noteWidth,
                noteHeight
            )
        }

    }

    private fun highlightBlackPianoKey(g: Graphics, note: String, color: Color){
        g.color = color

        val noteHeight: Int = bNoteHeight
        val noteWidth: Int = noteSize / whiteToBlackSizeRatio
        var keyPlace = blackNotes.indexOf(note)

        if (keyPlace > 1)
            keyPlace++

        for (i in 0 until 2){
            g.fillRect(
                xOffset + blackKeyXOffset + (i * 7 * noteSize) + (keyPlace * noteSize),
                yOffset,
                noteWidth,
                noteHeight
            )
        }

    }

    fun drawPianoOutline(g: Graphics){
        g.color = Color.black
        // perimenter of piano
        g.drawRect(
            xOffset ,
            yOffset,
            (noteSize * 15),
            noteSize * 4
        )
        // vertical white note lines
        for (i in 1 until 16) {
            var cutoff = 0
            if (i in arrayOf(1, 2, 4, 5, 6, 8, 9, 11, 12, 13)){
                cutoff = bNoteHeight
            }
            g.drawLine(
                xOffset + (i * noteSize),
                yOffset + cutoff,
                xOffset + (i * noteSize),
                yOffset + wNoteHeight
            )
        }
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

    private fun fillWhiteKeys(g: Graphics){
        g.color = Color.white
        // fill white keys
        g.fillRect(
            xOffset ,
            yOffset,
            (noteSize * 15),
            noteSize * 4
        )
    }

    private fun fillBlackKeys(g: Graphics){
        g.color = Color.black
        for (i in 0 until 2) {
            // color c#/db piano keys
            g.fillRect(
                xOffset + blackKeyXOffset + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // color d#/eb piano keys
            g.fillRect(
                xOffset + blackKeyXOffset + noteSize + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
        }
        for(i in 0 until 2) {
            // color f#/gb piano keys
            g.fillRect(
                xOffset + blackKeyXOffset + noteSize * 3 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // color g#/ab piano keys
            g.fillRect(
                xOffset + blackKeyXOffset + noteSize * 4 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
            // color a#/bb piano keys
            g.fillRect(
                xOffset + blackKeyXOffset + noteSize * 5 + (i * 7 * noteSize),
                yOffset,
                noteSize / whiteToBlackSizeRatio,
                bNoteHeight
            )
        }
    }

}