import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class Main : JFrame(){
    init{
        title = "Music Vis"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = BorderLayout()

        // create music structure and panels
        val musicStruct = Scale(rootNote = "C", modeType = "Major/Ionian")
        val relativeMajor = Scale(rootNote = "C", modeType = "Major/Ionian")
        val displayPanel = DisplayPanel(musicStruct, relativeMajor)
        val selectionPanel = SelectionPanel(musicStruct, relativeMajor, displayPanel)
        val infoPanel = InfoPanel(musicStruct, displayPanel)

        // set panel sizes
        selectionPanel.preferredSize = Dimension(850, 60)
        displayPanel.preferredSize = Dimension(850, 600)
        selectionPanel.preferredSize = Dimension(850, 60)
        infoPanel.preferredSize = Dimension(850, 45)

        // add panels to frame
        add(selectionPanel, BorderLayout.NORTH)
        add(infoPanel, BorderLayout.CENTER)
        add(displayPanel, BorderLayout.SOUTH)

        pack()
        setLocationRelativeTo(null) // center frame on screen

        isResizable = false

        isVisible = true
    }
}

fun main() {
    Main()
}