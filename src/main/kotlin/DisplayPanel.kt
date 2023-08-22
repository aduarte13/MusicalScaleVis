import java.awt.*
import javax.swing.*


class DisplayPanel(
    private val scale: Scale,
    private val background_color: Color = Color(40, 40, 40),
    private val text_color: Color = Color(235, 235, 235),
    private val root_note_color: Color = Color(225, 15, 0),
    private val reg_note_color: Color = Color(255, 180, 70),

    private val fretboard_x_offset: Int = 3,     // represent top left
    private val fretboard_y_offset: Int = 365,  // corner of fretboard
    private val fretboard_note_x_dist: Int = 55,  // horizontal distance between notes
    private val fretboard_note_y_dist: Int = 35,  // vertical distance between notes
    private val note_size: Int = 42             // size of notes on fretboard
) : JPanel(){
    private val eString = arrayOf("E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E")
    private val aString = arrayOf("A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A")
    private val dString = arrayOf("D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D")
    private val gString = arrayOf("G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G")
    private val bString = arrayOf("B", "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")

    private var hidden: Boolean = false

    init{
        border = BorderFactory.createEtchedBorder()

    }

    override fun paint(g: Graphics) {
        g.color = background_color // set background color
        g.fillRect(0, 0, 760, 610) // draw background
        drawFretboard(g) // draw fretboard
        if (!hidden) {
            // TOP TEXT
            drawTopTextIntervals(g)          // draw the chord intervals
            drawTopTextNotes(g)               // display chord notes

            // HIGHLIGHTING FRETBOARD
            drawFretboardNotes(g)    // highlight chord notes on fretboard
            drawFretboardRoots(g)    // highlight root notes on fretboard
            if (scale.getMode() == "Blues"){
                // highlightFretboardBlueNotes(g)
            }

            //drawFretboardDegrees(g);      // draw degree numbers on fretboard
        }
    }

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

    private fun drawFretboardRoots(g: Graphics) {

        for (i in 0..12) {
            if (eString[i] == scale.getRoot()) {
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset
                )
            }

            if (bString[i] == scale.getRoot()) {
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + fretboard_note_y_dist
                )
            }
            if (gString[i] == scale.getRoot()) {
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (2 * fretboard_note_y_dist)
                )
            }
            if (dString[i] == scale.getRoot()){
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (3 * fretboard_note_y_dist)
                )
            }
            if (aString[i] == scale.getRoot()){
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (4 * fretboard_note_y_dist)
                )
            }
            if (eString[i] == scale.getRoot()) {
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (5 * fretboard_note_y_dist)
                )
            }
        }
    }


    private fun drawFretboardNotes(g: Graphics) {

        for (i in 0..12) {
            if (eString[i] in scale.getDiatonicNotes()) {
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset
                )
            }

            if (bString[i] in scale.getDiatonicNotes()) {
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + fretboard_note_y_dist
                )
            }
            if (gString[i] in scale.getDiatonicNotes()) {
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (2 * fretboard_note_y_dist)
                )
            }
            if (dString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (3 * fretboard_note_y_dist)
                )
            }
            if (aString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (4 * fretboard_note_y_dist)
                )
            }
            if (eString[i] in scale.getDiatonicNotes()) {
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + (5 * fretboard_note_y_dist)
                )
            }
        }
    }

    private fun drawTopTextNotes(g: Graphics) {
        var flatsOrSharps = 0 // 0 = undecided; 1 = sharps; 2 = flats
        //val accidentals = arrayOf("C#/Db", "D#/Eb", "F#/Gb", "G#/Ab", "A#/Bb")

        g.font = Font("Arial", Font.BOLD, 24)
        g.color = reg_note_color

        // LOOP TO DECIDE WHICH ACCIDENTALS WILL BE USED
        var inc = 0
        do {
            var note: String = scale.getDiatonicNotes().get(inc)

            if (note == "C#/Db"){
                flatsOrSharps = if ("C" in scale.getDiatonicNotes())
                    2
                else
                    1
                break
            }
            else if(note == "D#/Eb"){
                flatsOrSharps = if ("D" in scale.getDiatonicNotes())
                    2
                else
                    1
                break
            }
            else if (note == "F#/Gb"){
                flatsOrSharps = if ("F" in scale.getDiatonicNotes())
                    2
                else
                    1
                break
            }
            else if (note == "G#/Ab"){
                flatsOrSharps = if ("G" in scale.getDiatonicNotes())
                    2
                else
                    1
                break
            }
            else if (note == "A#/Bb"){
                flatsOrSharps = if ("A" in scale.getDiatonicNotes())
                    2
                else
                    1
                break
            }

            inc++
        }while (flatsOrSharps == 0 && inc < scale.getDiatonicNotes().size)


        // LOOP FOR DRAWING NOTE STRINGS
        for (i in 0..scale.getDiatonicNotes().size-1){

            var note: String = scale.getDiatonicNotes().get(i)

            when(note){
                "C#/Db" -> if (flatsOrSharps == 1) note = "C#" else note = "Db"
                "D#/Eb" -> if (flatsOrSharps == 1) note = "D#" else note = "Eb"
                "F#/Gb" -> if (flatsOrSharps == 1) note = "F#" else note = "Gb"
                "G#/Ab" -> if (flatsOrSharps == 1) note = "G#" else note = "Ab"
                "A#/Bb" -> if (flatsOrSharps == 1) note = "A#" else note = "Bb"
            }

            g.drawString(note, 50 + i * 60, 50)

        }
    }

    private fun drawTopTextIntervals(g: Graphics) {
        g.color = text_color
        g.font = Font("Arial", Font.BOLD, 20)
        for (i in 0 until scale.getFormulaStrings().size) {
            g.drawString(scale.getFormulaStrings().get(i) + " ", 80 + i * 60, 25)
        }
    }

    private fun drawFretboard(g: Graphics) {
        g.color = Color.lightGray
        g.drawRect(                     // perimeter of fretboard, includes e strings
                fretboard_x_offset + (note_size),   // CAREFUL W/ TRUNCATION
                fretboard_y_offset + (note_size / 2),
                (fretboard_note_x_dist * 12) + 4,
                fretboard_note_y_dist * 5
        )
        for (i in 0..3)         // other guitar strings
            g.drawLine(
                    fretboard_x_offset + (note_size / 2) + 29,
                    fretboard_y_offset + note_size + 15 + (i * fretboard_note_y_dist),
                    fretboard_x_offset + (note_size / 2) + (fretboard_note_x_dist * 12) + 24,
                    fretboard_y_offset + note_size + 15 + (i * fretboard_note_y_dist)
                    )
        // draw fret lines for fretboard
        for (i in 0..11)
            g.drawLine(
                    fretboard_x_offset + fretboard_note_x_dist * i + note_size + 7,
                    fretboard_y_offset + (note_size / 2),
                    fretboard_x_offset + fretboard_note_x_dist * i + note_size + 7,
                    fretboard_y_offset + (note_size / 2) + (fretboard_note_y_dist * 5)
            )
        // draw fret markings
        for (i in 0..3)  // draw circles for fret markings on fret 3, 5, 7, and 9
            //g.fillOval(181 + i * 110, 250, 11, 11)
            g.fillOval(
                    fretboard_note_x_dist + (fretboard_note_x_dist * 2) + (note_size / 2) + (fretboard_note_x_dist * 2 * i) - 2,
                    fretboard_y_offset - (note_size / 2),
                    11,
                    11
            )
        g.fillOval(667, fretboard_y_offset - (note_size / 2), 11, 11) // draw circles for fret
        g.fillOval(687, fretboard_y_offset - (note_size / 2), 11, 11) // markings on fret 12
    }

    fun switchHidden() {
        hidden = !hidden
    }
}