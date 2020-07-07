import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;

    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JLabel patronymicLabel;
    private JLabel fullNameLabel;

    private JTextField firstName;
    private JTextField lastName;
    private JTextField patronymic;
    private JTextField fullName;

    private JButton collapse;
    private JButton expand;

    private String fName;
    private String lName;
    private String pName;

    public MainForm(){
        collapse.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                fName = firstName.getText();
                lName = lastName.getText();
                pName = patronymic.getText();
                if (!fName.isEmpty() && !lName.isEmpty()){
                    collapse.setVisible(false);
                    firstName.setVisible(false);
                    firstNameLabel.setVisible(false);
                    lastName.setVisible(false);
                    lastNameLabel.setVisible(false);
                    patronymic.setVisible(false);
                    patronymicLabel.setVisible(false);

                    expand.setVisible(true);
                    fullNameLabel.setVisible(true);
                    fullName.setVisible(true);
                    fullName.setText(lName + " " + fName + " " + pName);
                } else {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Не заполнены обязательные поля!",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }

            }
        });
        expand.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] line = fullName.getText().split(" ");
                if (line.length >= 2){
                    expand.setVisible(false);
                    fullNameLabel.setVisible(false);
                    fullName.setVisible(false);

                    collapse.setVisible(true);
                    firstName.setVisible(true);
                    firstNameLabel.setVisible(true);
                    firstName.setText(line[1]);
                    lastName.setVisible(true);
                    lastNameLabel.setVisible(true);
                    lastName.setText(line[0]);
                    patronymic.setVisible(true);
                    patronymicLabel.setVisible(true);

                    if (line.length == 3){
                        patronymic.setText(line[2]);
                    } else {
                        patronymic.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            mainPanel,
                            "Введенные данные не верны!",
                            "Ошибка",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        });
    }

    public JPanel getMainPanel(){
        expand.setVisible(false);
        fullNameLabel.setVisible(false);
        fullName.setVisible(false);
        return mainPanel;
    }
}
