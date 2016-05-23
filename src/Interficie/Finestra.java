
package Interficie;

import Cursos.Batxiller;
import Cursos.Curs;
import Cursos.LlistaCurs;
import Cursos.FP;
import Exceptions.NoExistException;
import Exceptions.RepeatException;
import Llista.Node;
import Assignatures.Obligatories;
import Assignatures.Opcionals;
import Assignatures.Assignatura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author Fede & Alberto
 */
public class Finestra {

    private LlistaCurs cursos;
    private JFrame finestra;

    public Finestra(String nom) {
        this.cursos = new LlistaCurs();
        finestra = new JFrame(nom);
        this.init_Componets();
        finestra.pack();
        finestra.setResizable(false);
        finestra.setLocationRelativeTo(null);
        finestra.setVisible(true);
    }

    private void init_Componets() {
        finestra.setLayout(new GridLayout());
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton InfoCurs = new JButton("Info Curs");
        JButton NouCurs = new JButton("Nou Curs");
        JButton EliminarCurs = new JButton("Eliminar Curs");

        JButton InfoAssig = new JButton("Info Assignatura");
        JButton NovaAssig = new JButton("Nova Assignatura");
        JButton EliminarAssig = new JButton("Eliminar Assignatura");
        
        InfoCurs.setBackground(Color.CYAN);
        NouCurs.setBackground(Color.CYAN);
        EliminarCurs.setBackground(Color.CYAN);
        
        InfoAssig.setBackground(Color.YELLOW);
        NovaAssig.setBackground(Color.YELLOW);
        EliminarAssig.setBackground(Color.YELLOW);

        NouCurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Curs c;
                String[] ident = PanelResposta("Curs");
                String name = ident[0];
                int id = Integer.parseInt(ident[1]);
                if (name != null && id != -1) {
                    try {
                        c = cursos.ExisteixCurs(name, id);
                        if (c == null) {
                            String opt[] = {"Batxiller", "FP"};
                            String option = (String) JOptionPane.showInputDialog(finestra,
                                    "Quin tipus de curs vol crear?",
                                    "Nou curs",
                                    JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("course.jpg"),
                                    opt,
                                    opt[0]);
                            if (option != null) {
                                if (option.equalsIgnoreCase(opt[0])) { //Bachelor
                                    opt = new String[]{"Primer", "Segon"};
                                    option = (String) JOptionPane.showInputDialog(finestra,
                                            "Quin any de Batxiller vol?",
                                            "Nou curs",
                                            JOptionPane.QUESTION_MESSAGE,
                                            new ImageIcon("course.jpg"),
                                            opt,
                                            opt[0]);
                                    if (option != null) {
                                        if (option.equalsIgnoreCase(opt[0])) {
                                            cursos.nouBatxiller(name, id, Batxiller.Primer);
                                        } else {
                                            cursos.nouBatxiller(name, id, Batxiller.Segon);
                                        }
                                    }
                                } else { // FP
                                    opt = new String[]{"Informatica", "Mecanica", "Electronica"};
                                    option = (String) JOptionPane.showInputDialog(finestra,
                                            "Quin tipus de FP vol?",
                                            "Nou curs",
                                            JOptionPane.QUESTION_MESSAGE,
                                            new ImageIcon("course.jpg"),
                                            opt,
                                            opt[0]);
                                    if (option != null) {
                                        if (option.equalsIgnoreCase(opt[0])) {
                                            cursos.nouFP(name, id, FP.Informatica);
                                        } else {
                                            if (option.equalsIgnoreCase(opt[1])) {
                                                cursos.nouFP(name, id, FP.Mecanica);
                                            } else {
                                                cursos.nouFP(name, id, FP.Electronica);
                                            }
                                        }
                                    }
                                }
                            }
                            if (option == null) {
                                name = null;
                                id = -1;
                            }
                        } else {
                            throw new RepeatException("El curs ja existeix.");
                        }
                    } catch (RepeatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
                        name = null;
                        id = -1;
                    }

                    if (name != null && id != -1) {
                        int select = 0;
                        int nc = 0;
                        c = cursos.ExisteixCurs(name, id);
                        while (select == 0) {
                            if (NovaAssignatura(c)) {
                                nc++;
                            }
                            select = JOptionPane.showOptionDialog(
                                    null,
                                    "Vol afegir una altra assignatura?", //Mensaje
                                    "Selecciona una opció", // Título
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("subject.jpg"), // null para icono por defecto.
                                    new Object[]{"Sí", "No"}, // null para YES, NO y CANCEL
                                    "Sí");
                        }
                        if (nc == 0) {
                            try {
                                cursos.EliminarCurs(c);
                            } catch (NoExistException ex) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Error al borrar el curs", "ERROR", 0, new ImageIcon("x.png"));
                            }
                        }
                    }
                }
            }
        });
        InfoCurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Node<Curs> nc = cursos.getCursos().getFirst();
                    ArrayList<Curs> ac = new ArrayList();
                    while (nc != null) {
                        ac.add(nc.getObjecte());
                        nc = nc.getNext();
                    }
                    if (!ac.isEmpty()) {
                        Curs[] courses = ac.toArray(new Curs[ac.size()]);
                        Curs c = (Curs) JOptionPane.showInputDialog(finestra,
                                "De quin curs vol veure la informació?",
                                "Info curso",
                                JOptionPane.QUESTION_MESSAGE,
                                new ImageIcon("course.jpg"),
                                courses,
                                courses[0]);
                        if (c != null) {
                            ArrayList<String[]> info = Finestra.this.cursos.infoCurs(c);
                            JDialog d = new JDialog();
                            d.setLayout(new BorderLayout());
                            d.setModal(true);
                            DefaultTableModel table = new DefaultTableModel();
                            table.addColumn("Nom");
                            table.addColumn("ID");
                            table.addColumn("");
                            table.addColumn("");
                            JTable Table = new JTable(table);
                            JScrollPane scroll = new JScrollPane(Table);
                            d.setTitle("Info del Curs(Nom: " + c.getNom()
                                    + ", Id: " + c.getCodi()
                                    + ", Tipus: " + info.get(0)[2] + " " + info.get(0)[3] + ")");
                            for (int i = 1; i < info.size(); i++) {
                                table.addRow(info.get(i));
                            }

                            d.add(scroll);
                            d.pack();
                            d.setLocationRelativeTo(null);
                            d.setVisible(true);
                        }
                    } else {
                        throw new NoExistException("No hi ha cursos.");
                    }
                } catch (NoExistException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
                }
            }
        });
        EliminarCurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Node<Curs> nc = cursos.getCursos().getFirst();
                    ArrayList<Curs> ac = new ArrayList();
                    while (nc != null) {
                        ac.add(nc.getObjecte());
                        nc = nc.getNext();
                    }
                    if (!ac.isEmpty()) {
                        Curs[] courses = ac.toArray(new Curs[ac.size()]);
                        Curs c = (Curs) JOptionPane.showInputDialog(finestra,
                                "Quin curs vol esborrar?",
                                "Eliminar curs",
                                JOptionPane.QUESTION_MESSAGE,
                                new ImageIcon("course.jpg"),
                                courses,
                                courses[0]);
                        if (c != null) {
                            try {
                                Finestra.this.cursos.EliminarCurs(c);
                            } catch (NoExistException ex) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No eliminat: " + ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));

                            }
                        }
                    } else {
                        throw new NoExistException("No hi ha cursos.");
                    }
                } catch (NoExistException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
                }
            }
        });

        NovaAssig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Node<Curs> nc = cursos.getCursos().getFirst();
                    ArrayList<Curs> ac = new ArrayList();
                    while (nc != null) {
                        ac.add(nc.getObjecte());
                        nc = nc.getNext();
                    }
                    if (!ac.isEmpty()) {
                        Curs[] courses = ac.toArray(new Curs[ac.size()]);
                        Curs c = (Curs) JOptionPane.showInputDialog(finestra,
                                "A qui curs vol afegir l'assignatura?",
                                "Nova assignatura",
                                JOptionPane.QUESTION_MESSAGE,
                                new ImageIcon("subject.jpg"),
                                courses,
                                courses[0]);
                        if (c != null) {
                            NovaAssignatura(c);
                        }
                    } else {
                        throw new NoExistException("No hi ha cursos.");
                    }
                } catch (NoExistException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));

                }
            }
        });
        InfoAssig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int select = JOptionPane.showOptionDialog(
                            null,
                            "Info de caracteristica o assignatura?", //Mensaje
                            "Selecciona una opció", // Título
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("subject.jpg"), // null para icono por defecto.
                            new Object[]{"Característica", "Assignatura"}, // null para YES, NO y CANCEL
                            "Característica");
                    if (select != -1) {
                        if (select == 1) {
                            Node<Curs> nc = cursos.getCursos().getFirst();
                            ArrayList<Curs> ac = new ArrayList();
                            while (nc != null) {
                                ac.add(nc.getObjecte());
                                nc = nc.getNext();
                            }
                            if (!ac.isEmpty()) {
                                Curs[] courses = ac.toArray(new Curs[ac.size()]);
                                Curs c = (Curs) JOptionPane.showInputDialog(finestra,
                                        "¿De que curso quieres ver la asignatura?",
                                        "Info assignatura",
                                        JOptionPane.QUESTION_MESSAGE,
                                        new ImageIcon("subject.jpg"),
                                        courses,
                                        courses[0]);
                                if (c != null) {
                                    ArrayList<Assignatura> as = new ArrayList();
                                    Node<Assignatura> ns = c.getLlistaAssig().getAssignatures().getFirst();
                                    while (ns != null) {
                                        as.add(ns.getObjecte());
                                        ns = ns.getNext();
                                    }

                                    if (!as.isEmpty()) {
                                        Assignatura[] subjects = as.toArray(new Assignatura[as.size()]);
                                        Assignatura s = (Assignatura) JOptionPane.showInputDialog(finestra,
                                                "Quina assignatura vol veure?",
                                                "Info asignatura",
                                                JOptionPane.QUESTION_MESSAGE,
                                                new ImageIcon("subject.jpg"),
                                                subjects,
                                                subjects[0]);
                                        if (s != null) {
                                            String[] info = c.getLlistaAssig().infoAssig(s);
                                            JDialog d = new JDialog();
                                            d.setLayout(new BorderLayout());
                                            d.setModal(true);
                                            d.setTitle("Info de l'assignatura");
                                            JPanel up = new JPanel(new BorderLayout());
                                            up.setBorder(BorderFactory.createTitledBorder("Info Assignatura"));
                                            JPanel up_left = new JPanel(new GridLayout(4,0));
                                            JPanel up_right = new JPanel(new GridLayout(4,0));

                                            JPanel up2 = new JPanel(new BorderLayout());
                                            up2.setBorder(BorderFactory.createTitledBorder("Info Curs"));
                                            JPanel up2_left = new JPanel(new GridLayout(4,0));
                                            JPanel up2_right = new JPanel(new GridLayout(4,0));

                                            String[] cat = new String[]{"Nom:", "ID:", "Tipus:", ""};
                                            for (int i = 0; i < info.length; i++) {
                                                if (i < 4) {
                                                    if (i == 3) {
                                                        up_left.add(new JLabel(info[2].equalsIgnoreCase("Optativa") ? "Perfil:" : "Crédits:"));
                                                    } else {
                                                        up_left.add(new JLabel(cat[i]));
                                                    }
                                                    up_right.add(new JLabel(info[i]));
                                                } else {
                                                    if (i == 7) {
                                                        up2_left.add(new JLabel(info[6].equalsIgnoreCase("FP") ? "Tipus:" : "Any:"));
                                                    } else {
                                                        up2_left.add(new JLabel(cat[i - 4]));
                                                    }
                                                    up2_right.add(new JLabel(info[i]));
                                                }

                                            }
                                            up2.add(up2_left, BorderLayout.WEST);
                                            up2.add(up2_right, BorderLayout.EAST);
                                            d.add(up2, BorderLayout.SOUTH);
                                            up.add(up_left, BorderLayout.WEST);
                                            up.add(up_right, BorderLayout.EAST);
                                            d.add(up, BorderLayout.NORTH);
                                            d.pack();
                                            d.setSize(d.getWidth()+70, d.getHeight());
                                            d.setLocationRelativeTo(null);
                                            d.setVisible(true);
                                        }
                                    } else {
                                        throw new NoExistException("No hi ha assignatures.");
                                    }

                                }
                            } else {
                                throw new NoExistException("No hi ha assignatures.");
                            }
                        } else {
                            int caract = JOptionPane.showOptionDialog(
                                    null,
                                    "Quina caracteristica?", //Mensaje
                                    "Selecciona una opció", // Título
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("subject.jpg"), // null para icono por defecto.
                                    new Object[]{"Obligatoria", "Optativa"}, // null para YES, NO y CANCEL
                                    "Obligatoria");
                            JDialog d = new JDialog();
                            d.setLayout(new BorderLayout());
                            d.setModal(true);
                            Class cl = Obligatories.class;
                            String x = "";
                            switch (caract) {
                                case 0:
                                    d.setTitle("Info de la clase obligatoria");
                                    cl = Obligatories.class;
                                    x = "Créditos";
                                    break;

                                case 1:
                                    d.setTitle("Info de la clase optativa");
                                    x = "Perfil";
                                    cl = Opcionals.class;
                            }
                            if (caract != -1) {
                                DefaultTableModel table = new DefaultTableModel();
                                table.addColumn("Nom del curs");
                                table.addColumn("ID del curs");
                                table.addColumn("Nom de l'assignatura");
                                table.addColumn("ID de l'assignatura");
                                table.addColumn(x + " de l'assignatura");
                                JTable Table = new JTable(table);
                                JScrollPane scroll = new JScrollPane(Table);
                                Node<Curs> nc = cursos.getCursos().getFirst();
                                if (nc == null) {
                                    throw new NoExistException("No hi ha assignatures.");
                                }
                                while (nc != null) {
                                    ArrayList<String[]> info = nc.getObjecte().getLlistaAssig().infoTipus(cl);
                                    for (int i = 0; i < info.size(); i++) {
                                        table.addRow(info.get(i));
                                    }
                                    nc = nc.getNext();
                                }
                                d.add(scroll);
                                d.pack();
                                d.setLocationRelativeTo(null);
                                d.setVisible(true);
                            }
                        }
                    }
                } catch (NoExistException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
                }
            }
        });
        EliminarAssig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Node<Curs> nc = cursos.getCursos().getFirst();
                    ArrayList<Curs> ac = new ArrayList();
                    while (nc != null) {
                        ac.add(nc.getObjecte());
                        nc = nc.getNext();
                    }
                    if (!ac.isEmpty()) {
                        Curs[] courses = ac.toArray(new Curs[ac.size()]);
                        Curs c = (Curs) JOptionPane.showInputDialog(finestra,
                                "De quin curs vol eliminar l'assignatura",
                                "Eliminar assignatura",
                                JOptionPane.QUESTION_MESSAGE,
                                new ImageIcon("subject.jpg"),
                                courses,
                                courses[0]);
                        if (c != null) {
                            ArrayList<Assignatura> as = new ArrayList();
                            Node<Assignatura> ns = c.getLlistaAssig().getAssignatures().getFirst();
                            while (ns != null) {
                                as.add(ns.getObjecte());
                                ns = ns.getNext();
                            }

                            if (!as.isEmpty()) {
                                Assignatura[] subjects = as.toArray(new Assignatura[as.size()]);
                                Assignatura s = (Assignatura) JOptionPane.showInputDialog(finestra,
                                        "Quina assignatura vol eliminar?",
                                        "Eliminar assignatura",
                                        JOptionPane.QUESTION_MESSAGE,
                                        new ImageIcon("subject.jpg"),
                                        subjects,
                                        subjects[0]);
                                if (s != null) {
                                    c.getLlistaAssig().EliminarAssig(s);
                                    if (c.getLlistaAssig().getAssignatures().getFirst() == null) {
                                        Finestra.this.cursos.EliminarCurs(c);
                                    }
                                }
                            } else {
                                throw new NoExistException("No hi ha assignatures.");
                            }

                        }
                    } else {
                        throw new NoExistException("No hi ha assignatures.");
                    }
                } catch (NoExistException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
                }
            }
        });


        JButton[] bCursos = {InfoCurs, NouCurs, EliminarCurs};
        JButton[] bAssignatures = {InfoAssig, NovaAssig, EliminarAssig};

        this.create_Panel("Cursos", bCursos);
        this.create_Panel("Assignatures", bAssignatures);
    }

    private void create_Panel(String nom, JButton[] botons) {
        JPanel Panel = new JPanel(new GridLayout(1, 3));
        Panel.setBorder(BorderFactory.createTitledBorder(nom));
        finestra.add(Panel, BorderLayout.EAST);
        for (int i = 0; i < botons.length; i++) {
            Panel.add(botons[i]);
        }
    }

    public String[] PanelResposta(String c) {
        JTextField nom = new JTextField();
        JTextField id = new JTextField();
        Object[] message = {
            "Nom:", nom,
            "ID:", id
        };

        String a = c.equalsIgnoreCase("Assignatura") ? "assignatura" : "curs";
        while (true) {
            int opcio = JOptionPane.showConfirmDialog(null, message, c, JOptionPane.OK_CANCEL_OPTION, 0, new ImageIcon(a.toLowerCase() + ".png"));
            if (opcio == JOptionPane.OK_OPTION) {
                try {
                    if (nom.getText().equals("")) {
                        throw new Exception();
                    }
                    if (Integer.parseInt(id.getText()) <= 0) {
                        throw new Exception();
                    }
                    return new String[]{nom.getText(), id.getText()};
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(
                            null,
                            nom.getText().equals("") ? "Inserta un nom" : "Inserta un nombre positiu", "ERROR", 0, new ImageIcon("x.png"));
                }
            } else {
                return new String[]{null, "-1"};
            }
        }
    }

    public boolean NovaAssignatura(Curs curs) {
        String[] idx = PanelResposta("Assignatura");
        String nom = idx[0];
        int id = Integer.parseInt(idx[1]);
        if (nom != null && id != -1) {
            try {
                Assignatura as = null;
                Node<Curs> nodeC = cursos.getCursos().getFirst();
                while (nodeC != null) {
                    as = nodeC.getObjecte().getLlistaAssig().ExisteixAssig(nom, id);
                    if (as != null) {
                        nodeC = nodeC.getNext();
                    } else {
                        nodeC = null;
                    }
                }
                if (as == null) {
                    String opt[] = {"Obligatoria", "Optativa"};
                    String option = (String) JOptionPane.showInputDialog(finestra,
                            "Quin tipus d'assignatura vols crear?",
                            "Nova assignatura",
                            JOptionPane.QUESTION_MESSAGE,
                            new ImageIcon("subject.jpg"),
                            opt,
                            opt[0]);
                    if (option != null) {
                        if (option.equalsIgnoreCase(opt[0])) { //Obligatory
                            String credit = "";
                            int credits = 0;
                            while (credits <= 0 && credit != null) {
                                JTextField JTFC = new JTextField();
                                Object[] message = {
                                    "Crédits:", JTFC
                                };
                                int copt = JOptionPane.showConfirmDialog(null, message, "Nova assignatura", JOptionPane.OK_CANCEL_OPTION, 0, new ImageIcon("subject.jpg"));
                                if (copt == JOptionPane.OK_OPTION) {
                                    try {
                                        credits = Integer.parseInt(JTFC.getText());
                                        if (credits <= 0) {
                                            throw new Exception();
                                        }
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                JTFC.getText().equals("") ? "Inserta el nº de credits" : "Inserta un nombre positiu", "ERROR", 0, new ImageIcon("x.png"));
                                    }
                                } else {
                                    credit = null;
                                }
                            }
                            if (credit != null) {
                                curs.getLlistaAssig().nouObligatoria(nom, id, credits, curs);
                                return true;
                            }
                        } else { // Opcionals
                            opt = new String[]{"Teoría", "Práctica"};
                            option = (String) JOptionPane.showInputDialog(finestra,
                                    "Quin tipus d'assignatura opcional vol?",
                                    "Nova assignatura",
                                    JOptionPane.QUESTION_MESSAGE,
                                    new ImageIcon("Subject.jpg"),
                                    opt,
                                    opt[0]);
                            if (option != null) {
                                if (option.equalsIgnoreCase(opt[0])) {
                                    curs.getLlistaAssig().nouOpcional(nom, id, Opcionals.Teoria, curs);
                                } else {
                                    curs.getLlistaAssig().nouOpcional(nom, id, Opcionals.Practica, curs);
                                }
                                return true;
                            }
                        }
                    }
                } else {
                    throw new RepeatException("Assignatura ja existeix.");
                }
            } catch (RepeatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(), "ERROR", 0, new ImageIcon("x.png"));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new Finestra("Escola");
    }
}
