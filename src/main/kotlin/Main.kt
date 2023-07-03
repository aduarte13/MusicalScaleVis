import javax.swing.JFrame
import java.awt.*
public class Main : JFrame(){
    init{
        title = "Scale Visualizer"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = BorderLayout()

        // create panels

        // set panel sizes

        // add panels to frame

        // create panels

        // set panel sizes

        // add panels to frame
        pack()
        setLocationRelativeTo(null) // center on screen

        isResizable = false // easier on my end

        isVisible = true // idk why this needs to be said
    }
}

fun main(args: Array<String>) {
    Main()
}