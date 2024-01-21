import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class SelectionPanel(
    private val scale: Scale,
    private val relativeMajor: Scale,
    private val displayPanel: DisplayPanel
) : JPanel(){
    private val noteList = arrayOf("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B")
    private val modeList = arrayOf(
        // WACKY THREE
        "Chromatic", "Whole Tone", "Diminished",
        // CLASSIC SEVEN
        "Major/Ionian", "Dorian", "Phrygian", "Lydian",
        "Mixolydian", "Minor/Aeolian", "Locrian",
        // PENTA + BLUES
        "Major Pentatonic", "Minor Pentatonic", "Major Blues", "Minor Blues",
        // MELODIC MINOR
        "Melodic Minor", "Dorian b2", "Lydian #5", "Lydian b7",
        "Mixolydian b6", "Locrian nat 2", "Altered"
    )

    private val noteDropMenu = JComboBox(noteList)
    private val modeDropMenu = JComboBox(modeList)

    private val sharpsOrFlatsPanel = JPanel()
    private val sharpsRadioButton = JRadioButton("Sharps")
    private val flatsRadioButton = JRadioButton("Flats")
    private val sharpsOrFlatsButtonGroup = ButtonGroup()

    private val notesOrDegreesPanel = JPanel()
    private val notesRadioButton = JRadioButton("Notes")
    private val degreesRadioButton = JRadioButton("Degrees")
    private val notesOrDegreesButtonGroup = ButtonGroup()

    private val instrumentSelectionPanel = JPanel()
    private val fretboardRadioButton = JRadioButton("Fretboard")
    private val keyboardRadioButton = JRadioButton("Keyboard")
    private val instrumentButtonGroup = ButtonGroup()

    init {
        border = BorderFactory.createTitledBorder("Selection")
        layout = FlowLayout()

        modeDropMenu.selectedItem = "Major/Ionian"

        noteDropMenu.maximumRowCount = 5
        modeDropMenu.maximumRowCount = 5

        instrumentSelectionPanel.border = BorderFactory.createEtchedBorder()
        instrumentButtonGroup.add(keyboardRadioButton)
        instrumentButtonGroup.add(fretboardRadioButton)
        instrumentSelectionPanel.add(keyboardRadioButton)
        instrumentSelectionPanel.add(fretboardRadioButton)
        //keyboardRadioButton.isSelected = true
        fretboardRadioButton.isSelected = true
        displayPanel.setInstrument(fretboardRadioButton.isSelected && !keyboardRadioButton.isSelected)

        sharpsOrFlatsPanel.border = BorderFactory.createEtchedBorder()
        sharpsOrFlatsButtonGroup.add(sharpsRadioButton)
        sharpsOrFlatsButtonGroup.add(flatsRadioButton)
        sharpsOrFlatsPanel.add(sharpsRadioButton)
        sharpsOrFlatsPanel.add(flatsRadioButton)
        sharpsRadioButton.isSelected = true

        notesOrDegreesPanel.border = BorderFactory.createEtchedBorder()
        notesOrDegreesButtonGroup.add(notesRadioButton)
        notesOrDegreesButtonGroup.add(degreesRadioButton)
        notesOrDegreesPanel.add(notesRadioButton)
        notesOrDegreesPanel.add(degreesRadioButton)
        notesRadioButton.isSelected = true

        // add action listeners
        keyboardRadioButton.addActionListener(InstrumentSelectionListener())
        fretboardRadioButton.addActionListener(InstrumentSelectionListener())

        noteDropMenu.addActionListener(ComboBoxListener())
        modeDropMenu.addActionListener(ComboBoxListener())

        sharpsRadioButton.addActionListener(SharpsOrFlatsListener())
        flatsRadioButton.addActionListener(SharpsOrFlatsListener())

        notesRadioButton.addActionListener(NotesOrDegreesListener())
        degreesRadioButton.addActionListener(NotesOrDegreesListener())

        // add components to panels
        add(instrumentSelectionPanel)

        add(noteDropMenu)
        add(modeDropMenu)

        add(sharpsOrFlatsPanel)

        add(notesOrDegreesPanel)

    }

    inner class InstrumentSelectionListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            if (fretboardRadioButton.isSelected)
                displayPanel.setInstrument(true)
            else
                displayPanel.setInstrument(false)
            displayPanel.repaint()  // should be optimized to not redraw unnecessarily
        }
    }

    inner class SharpsOrFlatsListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (sharpsRadioButton.isSelected) {
                scale.setUsingSharps(true)
                relativeMajor.setUsingSharps(true)
            }
            else {
                scale.setUsingSharps(false)
                relativeMajor.setUsingSharps(false)
            }
            scale.build()
            relativeMajor.build()
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

    inner class ComboBoxListener : ActionListener{
        override fun actionPerformed(e: ActionEvent) {
            when(e.source){
                noteDropMenu -> {
                    val selection = noteDropMenu.selectedItem as String // get the currently selected root from the list
                    scale.setRoot(selection) // set the chord's root to the selected note
                    relativeMajor.setRoot(selection)
                    displayPanel.repaint() // update display panel
                }
                modeDropMenu -> {
                    val selection = modeDropMenu.selectedItem as String // get currently selected type from the list
                    scale.setMode(selection) // set the chord's type to the selected type
                    scale.setMode(selection)
                    displayPanel.repaint() // update display panel
                }
            }
        }
    }
}


