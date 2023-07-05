import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.BorderFactory
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JPanel


class SelectionPanel(
    private val scale: Scale,
    private val displayPanel: DisplayPanel
) : JPanel(){
    private val checkboxHide = JCheckBox("Hide")

    private val noteList = arrayOf("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")
    private val modeList = arrayOf(
            "Major/Ionian", "Dorian", "Phrygian", "Lydian",
            "Mixolydian", "Minor/Aeolian", "Locrian"
    )

    private val noteDropMenu = JComboBox<String>(noteList)

    private val modeDropMenu = JComboBox<String>(modeList)

    init {
        border = BorderFactory.createTitledBorder("Chord Selection")
        layout = FlowLayout()

        noteDropMenu.maximumRowCount = 5
        modeDropMenu.maximumRowCount = 5

        checkboxHide.addActionListener(HideCheckBoxListener())


    }


    private class HideCheckBoxListener: ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            //displayPanel.switchChordHidden()
            //displayPanel.repaint()
        }
    }


}


