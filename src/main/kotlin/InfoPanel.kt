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

    private val checkboxHide = JCheckBox("Hide")
    private val relativeMajorHide = JCheckBox("Relative Major")

    private val checkboxHighlightRoot = JCheckBox("Highlight Root")
    private val checkBoxHighlightDevs = JCheckBox("Highlight Deviations")
    private val checkBoxHighlightBlue = JCheckBox("Highlight Blue Note")

    init {
        layout = FlowLayout()

        highlightButtonsPanel.border = BorderFactory.createTitledBorder("Highlight Control")
        checkboxHighlightRoot.isSelected = true
        checkBoxHighlightDevs.isSelected = true
        checkBoxHighlightBlue.isSelected = true
        relativeMajorHide.isSelected = true

        // add listeners
        checkboxHighlightRoot.addActionListener(CheckBoxListener())
        checkBoxHighlightDevs.addActionListener(CheckBoxListener())
        checkBoxHighlightBlue.addActionListener(CheckBoxListener())
        checkboxHide.addActionListener(CheckBoxListener())
        relativeMajorHide.addActionListener(CheckBoxListener())

        add(relativeMajorHide)

        highlightButtonsPanel.add(checkboxHighlightRoot)
        highlightButtonsPanel.add(checkBoxHighlightDevs)
        highlightButtonsPanel.add(checkBoxHighlightBlue)

        add(highlightButtonsPanel)
        add(checkboxHide)

    }

    inner class CheckBoxListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            when (e.source) {
                checkboxHighlightRoot -> displayPanel.switchHighlightSetting('r')
                checkBoxHighlightDevs -> displayPanel.switchHighlightSetting('d')
                checkBoxHighlightBlue -> displayPanel.switchHighlightSetting('b')
                checkboxHide -> displayPanel.switchHidden()
                relativeMajorHide -> displayPanel.switchRelativeMajorHidden()
            }
            displayPanel.repaint()
        }
    }
}