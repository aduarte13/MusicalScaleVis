import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*


class SelectionPanel(
    private val scale: Scale,
    private val displayPanel: DisplayPanel
) : JPanel(){
    private val checkboxHide = JCheckBox("Hide")

    private val noteList = arrayOf("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")
    private val modeList = arrayOf(
            // CLASSIC SEVEN
            "Major/Ionian", "Dorian", "Phrygian", "Lydian",
            "Mixolydian", "Minor/Aeolian", "Locrian",
            // PENTA + BLUES
            "Major Pentatonic", "Minor Pentatonic", "Major Blues", "Minor Blues"
    )

    private val noteDropMenu = JComboBox(noteList)
    private val modeDropMenu = JComboBox(modeList)

    private val sharpsRadioButton = JRadioButton("Sharps")
    private val flatsRadioButton = JRadioButton("Flats")
    private val sharpsOrFlatsButtonGroup = ButtonGroup()

    private val notesRadioButton = JRadioButton("Notes")
    private val degreesRadioButton = JRadioButton("Degrees")
    private val notesOrDegreesButtonGroup = ButtonGroup()

    init {
        border = BorderFactory.createTitledBorder("Chord Selection")
        layout = FlowLayout()

        noteDropMenu.maximumRowCount = 5
        modeDropMenu.maximumRowCount = 5

        sharpsOrFlatsButtonGroup.add(sharpsRadioButton)
        sharpsOrFlatsButtonGroup.add(flatsRadioButton)
        sharpsRadioButton.isSelected = true

        notesOrDegreesButtonGroup.add(notesRadioButton)
        notesOrDegreesButtonGroup.add(degreesRadioButton)
        notesRadioButton.isSelected = true

        // add action listeners
        checkboxHide.addActionListener(HideCheckBoxListener())

        noteDropMenu.addActionListener(RootSelectionListener())

        modeDropMenu.addActionListener(ModeSelectionListener())

        sharpsRadioButton.addActionListener(SharpsOrFlatsListener())
        flatsRadioButton.addActionListener(SharpsOrFlatsListener())

        notesRadioButton.addActionListener(NotesOrDegreesListener())
        degreesRadioButton.addActionListener(NotesOrDegreesListener())

        // add components to panels
        add(noteDropMenu)
        add(modeDropMenu)

        add(checkboxHide)

        add(sharpsRadioButton)
        add(flatsRadioButton)

        add(notesRadioButton)
        add(degreesRadioButton)

    }

    inner class SharpsOrFlatsListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (sharpsRadioButton.isSelected)
                scale.setUsingSharps(true)
            else
                scale.setUsingSharps(false)
            scale.build()
            displayPanel.repaint()
        }
    }

    inner class NotesOrDegreesListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (notesRadioButton.isSelected)
                displayPanel.setUsingNoteNames(true)
            else
                displayPanel.setUsingNoteNames(false)

            displayPanel.repaint()
        }
    }

    inner class RootSelectionListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val selection = noteDropMenu.selectedItem as String // get the currently selected root from the list
            scale.setRoot(selection) // set the chord's root to the selected note
            displayPanel.repaint() // update display panel
        }
    }

    inner class ModeSelectionListener : ActionListener{
        override fun actionPerformed(e: ActionEvent) {
            val selection = modeDropMenu.selectedItem as String // get currently selected type from the list
            scale.setMode(selection) // set the chord's type to the selected tyoe
            displayPanel.repaint() // update display panel
        }
    }

    inner class HideCheckBoxListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            displayPanel.switchHidden()
            displayPanel.repaint()
        }
    }

}


