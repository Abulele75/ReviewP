import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ReviewP extends JFrame {

    JPanel pnlSouth, pnlNorth,pnlCenter;
    JLabel lblTitle, lblSelectTutor, lblSelectSub,lblRating, lblReview;
    JComboBox CBOsubjects, CBOtutors;
    JTextArea txtArea;
    JButton btnSubmit;
    private JLabel[] stars = new JLabel[5];
    private int currentRating = 0;
    private Icon Fullstar, Empty;
    ReviewT tableFrame = new ReviewT();

    public ReviewP() {
        super("Review Page");
        lblTitle = new JLabel("Leave a review");
        lblTitle.setForeground(Color.YELLOW);

        lblSelectSub = new JLabel("Select a Subject");
        lblSelectSub.setForeground(Color.YELLOW);
        lblSelectTutor = new JLabel("Select a Tutor");
        lblSelectTutor.setForeground(Color.YELLOW);
        lblRating = new JLabel("Rating");
        lblRating.setForeground(Color.YELLOW);
        lblReview = new JLabel("Comment");
        lblReview.setForeground(Color.YELLOW);
        txtArea = new JTextArea(2,20);


        pnlSouth = new JPanel();
        pnlNorth = new JPanel();
        pnlCenter = new JPanel();

        String[] subjects = {"Select Subject","Mathematics", "Mathematical literacy", "Physical Science", " Life science", "Agricultural Science", "Accounting"};
        CBOsubjects = new JComboBox(subjects);


        String[] tutors = {"Select Tutor","Abulele", "Tony", "Inam", "Leeroy", "Lindi"};
        CBOtutors = new JComboBox(tutors);


       /* URL urlStar = getClass().getClassLoader().getResource("Fullstar.png");
        URL urlEmptyStar = getClass().getClassLoader().getResource("Empty.png");

        System.out.println("Fullstar.png URL: " + urlStar);
        System.out.println("Empty.png URL: " + urlEmptyStar);
       // Empty = new ImageIcon(getClass().getClassLoader().getResource("Empty.png"));
       // Fullstar = new ImageIcon(getClass().getClassLoader().getResource("Fullstar.png"));

        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i] = new JLabel(Empty);
            stars[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setRating(index + 1);
                }
            });


        }*/
        JPanel starPanel = new JPanel(new FlowLayout());
        starPanel.setBackground(Color.decode("#090979")); // match your GUI color
        starPanel.setPreferredSize(new Dimension(200, 40)); // Give it some size
        Image fullImg = new ImageIcon(getClass().getResource("/Fullstar.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        Fullstar = new ImageIcon(fullImg);

        Image emptyImg = new ImageIcon(getClass().getResource("/Empty.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        Empty = new ImageIcon(emptyImg);


        for (int i = 0; i < stars.length; i++) {
            final int index = i;
            stars[i] = new JLabel(Empty);
            stars[i].setPreferredSize(new Dimension(40, 40)); // make stars larger
            stars[i].setBorder(BorderFactory.createLineBorder(Color.WHITE)); // for debug
            stars[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    setRating(index + 1);
                }
            });
            starPanel.add(stars[i]);
        }

        //btnSubmit = new JButton("Submit");
        btnSubmit = new RoundedButton("Submit", 30);
        btnSubmit.setForeground(Color.BLACK);


        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CBOsubjects.getSelectedIndex()==0||CBOtutors.getSelectedIndex()==0|| txtArea.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in the missing fields");
                }
                else{
                    //To capture the review data
                  String subject = CBOsubjects.getSelectedItem().toString();
                  String tutor = CBOtutors.getSelectedItem().toString();
                  String comment = txtArea.getText();
                    int rating = currentRating;

                    Review newReview = new Review(subject, tutor, rating, comment);
                    tableFrame.addReview(newReview);

                 /*
                  System.out.println("Subject:" + " " + subject);
                    System.out.println("Tutor:" + " " + tutor);
                    System.out.println("Rating" +" " + rating);
                    System.out.println("Comment:" + " " + comment);

                    CBOsubjects.setSelectedIndex(0);
                    CBOtutors.setSelectedIndex(0);
                    txtArea.setText("");
                    setRating(0); */



                    JOptionPane.showMessageDialog(null,"Review submitted successfully");

                    CBOsubjects.setSelectedIndex(0);
                    CBOtutors.setSelectedIndex(0);

                    txtArea.setText("");

                    setRating(0);



                }

            }
        });
    }


   class RoundedButton extends JButton {
       private int radius;

       public RoundedButton(String text, int radius) {
           super(text);
           this.radius = radius;
           setContentAreaFilled(false);
           setFocusPainted(false);
           setBorderPainted(false);
           setOpaque(false); // makes corners transparent
       }

       @Override
       protected void paintComponent(Graphics g) {
           Graphics2D g2 = (Graphics2D) g.create();
           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

           // Fill background
           g2.setColor(getBackground());
           g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

           super.paintComponent(g2);
           g2.dispose();

       }

   }

    private void setRating(int rating){
        currentRating = rating;

        for (int i = 0; i < stars.length; i++) {
            stars[i].setIcon(i< rating? Fullstar:Empty);

        }


        }
        public void setGUI() {
        getContentPane().setBackground(Color.decode("#090979"));
        pnlNorth.setBackground(Color.decode("#090979"));
        pnlNorth.setOpaque(true);
        pnlCenter.setBackground(Color.decode("#090979"));
        pnlCenter.setOpaque(true);
        pnlSouth.setBackground(Color.decode("#090979"));
        pnlSouth.setOpaque(true);
            pnlNorth.setLayout(new FlowLayout());
            pnlCenter.setLayout(new GridLayout(0,1));
            pnlSouth.setLayout(new FlowLayout());

            pnlNorth.add(lblTitle);
            lblTitle.setFont(new Font("Arial", Font.BOLD, 20));


            pnlCenter.add(new JLabel(""));


            pnlCenter.add(lblSelectSub);
            pnlCenter.add(CBOsubjects);
            pnlCenter.add(lblSelectTutor);
            pnlCenter.add(CBOtutors);

            pnlCenter.add(lblReview);

            pnlCenter.add(new JScrollPane(txtArea));


            JPanel starPanel =  new JPanel( new FlowLayout());
            for (JLabel star : stars) {
                starPanel.add(star);
            }
            pnlCenter.add(lblRating);
            pnlCenter.add(starPanel);
            setRating(0);


            pnlSouth.add(btnSubmit);

            this.setLayout(new BorderLayout());
            this.add(pnlNorth, BorderLayout.NORTH);
            this.add(pnlCenter, BorderLayout.CENTER);
            this.add(pnlSouth, BorderLayout.SOUTH);

           // ReviewT tableFrame = new ReviewT();
            tableFrame.setVisible(true); //created to connect the comment section table with the comment submission page

                this.pack();
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);


            }


    public static void main(String[] args) {
        new ReviewP().setGUI();

    }
}

