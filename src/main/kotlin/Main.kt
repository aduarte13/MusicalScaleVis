import java.awt.*
import javax.swing.JFrame


public class Main : JFrame(){
    init{
        title = "Music Vis"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = BorderLayout()

        // create music structure and panels
        var music_struct = Scale(rootNote = "C", modeType = "Major/Ionian")
        val displayPanel = DisplayPanel(music_struct)
        val selectionPanel = SelectionPanel(music_struct, displayPanel)

        // set panel sizes
        selectionPanel.preferredSize = Dimension(750, 160)
        displayPanel.preferredSize = Dimension(750, 500)

        // add panels to frame
        add(selectionPanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.SOUTH);

        pack()
        setLocationRelativeTo(null) // center on screen

        isResizable = false // easier on my end

        isVisible = true // idk why this needs to be said
    }
}

fun main(args: Array<String>) {
    Main()
}