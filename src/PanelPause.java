import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelPause extends PacmanJPanel implements ActionListener {
    PacmanJButton continueBtn, saveBtn, exitBtn;

    private ScreenMain mainFrame;
    private PanelGame gamePanel;

    public PanelPause(ScreenMain mainFrame, PanelGame gamePanel) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.gamePanel.setSuspend(true);

        setLayout(new GridLayout(0, 1));

        // need to implement get to game, save, exit buttons with actionListener
        continueBtn = new PacmanJButton("Continue Game", pacmanFont.deriveFont(12f));
        continueBtn.addActionListener(this);
        saveBtn = new PacmanJButton("Save Game", pacmanFont.deriveFont(12f));
        saveBtn.addActionListener(this);
        exitBtn = new PacmanJButton("Exit To Main Menu (currently closes game)", pacmanFont.deriveFont(12f));
        exitBtn.addActionListener(this);

        add(continueBtn);
        add(saveBtn);
        add(exitBtn);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                Component src = (Component) e.getSource();
                src.setFocusable(true);
                src.requestFocusInWindow();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ESCAPE) {
                    gamePanel.setSuspend(false);
                    mainFrame.showPanel("gamePanel");

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == exitBtn)
            System.exit(0);

        if (clicked == continueBtn) {
            gamePanel.setSuspend(false);
            mainFrame.showPanel("gamePanel");
        }

        if (clicked == saveBtn) {
            // we always remove the panel so its okay to create a new one
            PanelDatabase saveGame = new PanelDatabase(mainFrame, "save", this.gamePanel);
            mainFrame.addPanel(saveGame, "savePanel");
            mainFrame.showPanel("savePanel");
        }
    }
}