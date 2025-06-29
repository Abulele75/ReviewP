import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReviewT extends JFrame {

    JTable Reviewtbl;
    JButton btnAllReviews, btnPostive, btnCritical, btnMostRecent, btnHighestRating;
    JLabel lblSortby, lblStudentReviews, lblFilter;
    JPanel pnlNorth, pnlCenter, pnlSouth;

    ArrayList<Review> rvList = new ArrayList<>();
    DefaultTableModel model;


    public ReviewT() {
        super("Filter Reviews");

        btnAllReviews = new JButton("All Reviews");
        btnPostive = new JButton("Postive (4-5)");
        btnCritical = new JButton("Critical (1-3)");
        btnMostRecent = new JButton("Most Recent");
        btnHighestRating = new JButton("Highest Rating");
        btnHighestRating.setForeground(Color.YELLOW);
        lblFilter = new JLabel("Filter Reviews");
        lblFilter.setForeground(Color.YELLOW);
        lblSortby = new JLabel("Sort by");
        lblSortby.setFont(new Font("Arial", Font.BOLD, 30));
        lblStudentReviews = new JLabel("Student Reviews:");
        lblStudentReviews.setForeground(Color.WHITE);
        lblStudentReviews.setFont(new Font("Arial", Font.BOLD, 30));
        pnlNorth = new JPanel();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();


        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{"Subject", "Tutor", "Rating", "Comment"});

        Reviewtbl = new JTable(model);
        Reviewtbl.setBackground(new Color(255, 255, 170));
        Reviewtbl.setFont(new Font("Arial", Font.PLAIN, 14));
        Reviewtbl.setRowHeight(30);
        Reviewtbl.setGridColor(Color.GRAY); // subtle grid
        Reviewtbl.setShowGrid(true);



        setGUI();

    }

    private void showAllReviews() {
        filterTable(1, 5);
    }

    public void addReview(Review r) {
        rvList.add(r);
        model.addRow(new Object[]{r.subject, r.tutor, r.rating, r.comment});
    }
    private void filterTable(int minRating, int maxRating) {
        model.setRowCount(0); // Clear table

        for (Review r : rvList) {
            if (r.rating >= minRating && r.rating <= maxRating) {
                model.addRow(new Object[]{r.subject, r.tutor, r.rating, r.comment});
            }
        }
    }




    public void setGUI() {

        btnAllReviews.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable(1, 5); // Show all
            }
        });

        btnPostive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable(4, 5); // Show positive reviews only
            }
        });

        btnCritical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable(1, 3); // Show critical reviews only
            }
        });

        getContentPane().setBackground(Color.decode("#090979"));
        pnlNorth.setBackground(Color.decode("#090979"));
        pnlNorth.setOpaque(true);
        pnlCenter.setBackground(Color.decode("#090979"));
        pnlCenter.setOpaque(true);
        pnlSouth.setBackground(Color.decode("#090979"));
        pnlSouth.setOpaque(true);
        pnlNorth.setLayout(new FlowLayout());

        pnlCenter.setLayout(new FlowLayout());
        pnlSouth.setLayout(new GridLayout(0, 1));
        pnlSouth.setForeground(new Color(255, 255, 153));

        pnlNorth.add(lblFilter);
        pnlNorth.add(btnAllReviews);
        pnlNorth.add(btnPostive);
        pnlNorth.add(btnCritical);

        pnlCenter.add(lblSortby);
        pnlCenter.add(btnMostRecent);
        pnlCenter.add(btnHighestRating);

        pnlSouth.add(lblStudentReviews);
        Reviewtbl.setBackground(new Color(255, 255, 170));

        JScrollPane scroll = new JScrollPane(Reviewtbl);


        scroll.getViewport().setBackground(new Color(255, 255, 170));

        pnlSouth.add(scroll);

        this.setLayout(new BorderLayout());
        showAllReviews();

        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}




