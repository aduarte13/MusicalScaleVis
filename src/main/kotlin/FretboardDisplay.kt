import java.awt.Color
import java.awt.Graphics

class FretboardDisplay {

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

    private fun drawFretboardNotes(
            g: Graphics,
            guitarString: Array<String>,
            guitarStringNum: Int
    ){
        val yOffset = guitarStringNum * fretboard_note_y_dist

        for (i in 0..12){
            // HIGHLIGHT ROOT
            if (guitarString[i] == scale.getRoot()){
                drawGuitarNote(
                        g,
                        root_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }
            // REGULAR DIATONIC NOTES
            else if (guitarString[i] in scale.getDiatonicNotes()){
                drawGuitarNote(
                        g,
                        reg_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }

            // BLUE NOTES
            if (scale.getMode() == "Minor Blues" && guitarString[i] == scale.getDiatonicNotes()[3]) {
                drawGuitarNote(
                        g,
                        blue_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }
            if (scale.getMode() == "Major Blues" && guitarString[i] == scale.getDiatonicNotes()[2]) {
                drawGuitarNote(
                        g,
                        blue_note_color,
                        fretboard_x_offset + i * fretboard_note_x_dist,
                        fretboard_y_offset + yOffset
                )
            }
        }
    }

}