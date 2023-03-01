package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomePanel extends MainPanel {
    public HomePanel() {
        super(new GridBagLayout());

        setBorder(new LineBorder(Color.PINK, 5));

        add(new JPanel() {
            {
                // anonymous initializer (i say it because it is hard to tell w/ bracket
                // madness)
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setBorder(new LineBorder(Color.GREEN, 5));

                add(new JButton("Sandbox") {
                    {
                        // anonymous initializer
                        addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                MainFrame.getMainFrame().setMainPanel(new GamePanel());
                            }
                        });
                    }
                });
            }

            @Override
            public Dimension getPreferredSize() {
                return getMinimumSize();
            }

            @Override
            public Dimension getMaximumSize() {
                return getMinimumSize();
            }
        }, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        setVisible(true);
    }
}