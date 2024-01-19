import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.BorderFactory
import javax.swing.JCheckBox
import javax.swing.JPanel

class InfoPanel(
    private val scale: Scale,
    private val displayPanel: DisplayPanel
): JPanel() {
    private val highlightButtonsPanel = JPanel()
    private val hideOptionsPanel = JPanel()

    private val relativeMajorHide = JCheckBox("Relative Major")
    private val checkboxHideAll = JCheckBox("Hide All")
    private val checkboxHideSteps = JCheckBox("Hide Steps")
    private val checkboxHideFormula = JCheckBox("Hide Formula")
    private val checkboxHideChords = JCheckBox("Hide Chords")

    private val checkboxHighlightRoot = JCheckBox("Highlight Root")
    private val checkBoxHighlightDevs = JCheckBox("Highlight Deviations")
    private val checkBoxHighlightBlue = JCheckBox("Highlight Blue Note")

    init {
        layout = FlowLayout()

        highlightButtonsPanel.border = BorderFactory.createTitledBorder("Highlight Control")
        hideOptionsPanel.border = BorderFactory.createTitledBorder("Hide Options")

        checkboxHighlightRoot.isSelected = true
        checkBoxHighlightDevs.isSelected = true
        checkBoxHighlightBlue.isSelected = true
        relativeMajorHide.isSelected = true

        // add listeners
        checkboxHighlightRoot.addActionListener(CheckBoxListener())
        checkBoxHighlightDevs.addActionListener(CheckBoxListener())
        checkBoxHighlightBlue.addActionListener(CheckBoxListener())
        checkboxHideAll.addActionListener(CheckBoxListener())
        relativeMajorHide.addActionListener(CheckBoxListener())

        // add components to appropriate
        highlightButtonsPanel.add(checkboxHighlightRoot)
        highlightButtonsPanel.add(checkBoxHighlightDevs)
        highlightButtonsPanel.add(checkBoxHighlightBlue)
        hideOptionsPanel.add(checkboxHideAll)
        hideOptionsPanel.add(checkboxHideSteps)
        hideOptionsPanel.add(checkboxHideFormula)
        hideOptionsPanel.add(checkboxHideChords)

        add(highlightButtonsPanel)
        add(hideOptionsPanel)
        add(relativeMajorHide)
    }

    inner class CheckBoxListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            when (e.source) {
                checkboxHighlightRoot -> displayPanel.switchHighlightSetting('r')
                checkBoxHighlightDevs -> displayPanel.switchHighlightSetting('d')
                checkBoxHighlightBlue -> displayPanel.switchHighlightSetting('b')
                checkboxHideAll -> displayPanel.switchHidden()
                relativeMajorHide -> displayPanel.switchRelativeMajorHidden()
            }
            displayPanel.repaint()
        }
    }
}