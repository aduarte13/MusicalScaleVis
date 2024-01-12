import java.awt.Color
import java.awt.Graphics

class FretboardDisplay(
    private val scale: Scale,

    private val fretboardXOffset: Int = 60,       // top left of fretboard
    private val fretboardYOffset: Int = 365,      // top left of fretboard
    private val fretboardNoteXDist: Int = 55,     // horizontal distance between notes
    private val fretboardNoteYDist: Int = 35,     // vertical distance between notes
    private val noteSize: Int = 42,               // size of notes on fretboard

    private val rootNoteColor: Color,
    private val regNoteColor: Color,
    private val blueNoteColor: Color,
    private  val specialNoteColor: Color

    ) {

    private val eString = arrayOf("E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E")
    private val aString = arrayOf("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A")
    private val dString = arrayOf("D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D")
    private val gString = arrayOf("G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G")
    private val bString = arrayOf("B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    private val allGuitarStrings = arrayOf(
            eString, bString, gString, dString, aString, eString
    )

    /**
     *  drawFretboardGuitarStrings
     *  calls drawFretboardNotes for each guitar string array
     *  @param g: Graphics object for DisplayPanel
     */
    fun drawFretboardGuitarStrings(g: Graphics){
        for (i in allGuitarStrings.indices){
            drawFretboardNotes(g, allGuitarStrings[i], i)
        }
    }

    /**
     *  drawFretboardNoteNames
     *  calls drawFretboardNoteStrings for each guitar string array
     *  @param g: Graphics object for DisplayPanel
     */
    fun drawFretboardNoteNames(g: Graphics) {
        for (i in allGuitarStrings.indices){
            drawFretboardNoteStrings(g, allGuitarStrings[i], i)
        }
    }

    /**
     *  drawFretboardDegrees
     *  calls drawFretboardDegreeStrings for each guitar string array
     *  @param g: Graphics object for DisplayPanel
     */
    fun drawFretboardDegrees(g: Graphics){
        for (i in allGuitarStrings.indices){
            drawFretboardDegreeStrings(g, allGuitarStrings[i], i)
        }
    }

    /**
     *  drawFretboardNotes
     *  calls drawGuitarNote for each diatonic note for each guitar string
     *  @param g: Graphics object for DisplayPanel
     *  @param guitarString: string array representing guitar notes per fret
     *  @param guitarStringNum: used for drawing notes at appropriate y value
     */
    private fun drawFretboardNotes(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ){
        val yOffset = guitarStringNum * fretboardNoteYDist

        for (i in 0..12){

            val noteColor = regNoteColor
            if (guitarString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        noteColor,
                        fretboardXOffset + i * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                )
            }
        }
    }

    /**
     *  highlightFretboardRoots
     *  calls drawGuitarNote for each ROOT note for each guitar string
     *  @param g: Graphics object for DisplayPanel
     */
    fun highlightFretboardRoots(
        g: Graphics
    ){

        for (i in 0..5) {
            val yOffset = i * fretboardNoteYDist

            for (j in 0..12) {
                // HIGHLIGHT ROOT
                if (allGuitarStrings[i][j] == scale.getRoot()) {
                    drawGuitarNote(
                        g,
                        rootNoteColor,
                        fretboardXOffset + j * fretboardNoteXDist,
                        fretboardYOffset + yOffset
                    )
                }
            }

        }
    }

    /**
     *  highlightFretboardDev
     *  calls drawGuitarNote for each diatonic note and highlights it if it
     *  deviates from its relative major scale
     *  @param g: Graphics object for DisplayPanel
     */
    fun highlightFretboardDevs(g: Graphics){
        for (i in 0..5) {
            val yOffset = i * fretboardNoteYDist

            for (j in 0..12) {
                val note = allGuitarStrings[i][j]

                if (note in scale.getDiatonicNotes()) {

                    val intervalIndex = scale.getDiatonicNotes().indexOf(note)
                    val interval = scale.getFormula()[intervalIndex]

                    if (interval.length > 1) {
                        drawGuitarNote(
                            g,
                            specialNoteColor,
                            fretboardXOffset + j * fretboardNoteXDist,
                            fretboardYOffset + yOffset
                        )
                    }
                }
            }
        }
    }

    /**
     *  drawFretboardNoteStrings
     *  draws the note name as a string onto appropriate note circles
     *  @param g: Graphics object for DisplayPanel
     *  @param guitarString: string array representing guitar notes per fret
     *  @param guitarStringNum: used for drawing notes at appropriate y value
     */
    private fun drawFretboardNoteStrings(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ) {
        val accidentals = listOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")
        var xOffset: Int                                    // for centering strings of size 2
        val yOffset = guitarStringNum * fretboardNoteYDist  //

        g.color = Color.black

        for (i in 0..12) {
            if (guitarString[i] in scale.getDiatonicNotes()) {
                var note = guitarString[i]
                if (note in accidentals) {
                    when (note) {
                        "C#/Db" -> note = if (scale.getUsingSharps()) "C#" else "Db"
                        "D#/Eb" -> note = if (scale.getUsingSharps()) "D#" else "Eb"
                        "F#/Gb" -> note = if (scale.getUsingSharps()) "F#" else "Gb"
                        "G#/Ab" -> note = if (scale.getUsingSharps()) "G#" else "Ab"
                        "A#/Bb" -> note = if (scale.getUsingSharps()) "A#" else "Bb"
                    }
                    xOffset = -6
                } else xOffset = 0
                g.drawString(
                        note,
                        fretboardXOffset + i * fretboardNoteXDist + (noteSize / 4) + 2 + xOffset,
                        fretboardYOffset + (noteSize / 2) + 9 + yOffset
                )
            }
        }
    }

    /**
     *  drawFretboardDegreeStrings
     *  draws the degree of the note as a string onto appropriate note circles
     *  @param g: Graphics object for DisplayPanel
     *  @param guitarString: string array representing guitar notes per fret
     *  @param guitarStringNum: used for drawing notes at appropriate y value
     */
    private fun drawFretboardDegreeStrings(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ) {

        var offset = 0

        g.color = Color.black

        //
        for (i in 0..12) {
            if (guitarString[i] in scale.getDiatonicNotes()) {
                val note = guitarString[i]
                val noteIndex = scale.getDiatonicNotes().indexOf(note)
                val interval = scale.getFormula()[noteIndex]
                if (interval.length > 1)
                    offset -= 7

                g.drawString(
                        "" + (scale.getFormula()[noteIndex]),
                        fretboardXOffset + i * fretboardNoteXDist + (noteSize/4) + 5 + offset,
                        fretboardYOffset + (noteSize/2) + 9 + (guitarStringNum * fretboardNoteYDist)
                )
                offset = 0
            }
        }
    }

    /**
     *  drawFretboard
     *  draws the fretboard shape, fret lines, and fret markings
     *  @param g: Graphics object for DisplayPanel
     */
    fun drawFretboard(g: Graphics) {
        g.color = Color.lightGray
        g.drawRect(                     // perimeter of fretboard, includes e strings
                fretboardXOffset + (noteSize),   // CAREFUL W/ TRUNCATION
                fretboardYOffset + (noteSize / 2),
                (fretboardNoteXDist * 12) + 4,
                fretboardNoteYDist * 5
        )
        for (i in 0..3)         // other guitar strings
            g.drawLine(
                    fretboardXOffset + (noteSize / 2) + 29,
                    fretboardYOffset + noteSize + 15 + (i * fretboardNoteYDist),
                    fretboardXOffset + (noteSize / 2) + (fretboardNoteXDist * 12) + 24,
                    fretboardYOffset + noteSize + 15 + (i * fretboardNoteYDist)
            )
        // draw fret lines for fretboard
        for (i in 0..11)
            g.drawLine(
                    fretboardXOffset + fretboardNoteXDist * i + noteSize + 7,
                    fretboardYOffset + (noteSize / 2),
                    fretboardXOffset + fretboardNoteXDist * i + noteSize + 7,
                    fretboardYOffset + (noteSize / 2) + (fretboardNoteYDist * 5)
            )
        // draw fret markings
        for (i in 0..3)  // draw circles for fret markings on fret 3, 5, 7, and 9
        //g.fillOval(181 + i * 110, 250, 11, 11)
            g.fillOval(
                    fretboardNoteXDist + (fretboardNoteXDist * 2) + (noteSize / 2) + (fretboardNoteXDist * 2 * i) - 2,
                    fretboardYOffset - (noteSize / 2),
                    11,
                    11
            )
        g.fillOval(667, fretboardYOffset - (noteSize / 2), 11, 11) // draw circles for fret
        g.fillOval(687, fretboardYOffset - (noteSize / 2), 11, 11) // markings on fret 12
    }

    /**
     *  drawGuitarNote
     *  draws circle of a given color at (x, y)
     *  @param g: Graphics object for DisplayPanel
     *  @param c: color of note circle
     *  @param x: x coordinate of top left of circle
     *  @param y: y coordinate of top left of circle
     */
    private fun drawGuitarNote(g: Graphics, c: Color, x: Int, y: Int){
        val size = 42
        val borderWidth = 4

        // draw border (will be covered by other circle)
        g.color = Color.white
        g.fillOval(x, y, size, size)

        // draw inner circle
        g.color = c
        // !!! CAREFUL WITH TRUNCATION HERE
        g.fillOval(x + (borderWidth/2), y + (borderWidth/2), size - borderWidth, size - borderWidth)
    }

}