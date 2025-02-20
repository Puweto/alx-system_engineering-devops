import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Main extends JFrame {
    private JTextArea inputTextArea, outputTextArea;
    private JButton summarizeButton;

    public Main() {
        setTitle("Text Summarizer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input text area
        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        // Output text area
        outputTextArea = new JTextArea();
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        // Summarize button
        summarizeButton = new JButton("Summarize");
        summarizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String summary = summarizeText(inputText);
                outputTextArea.setText(summary);
            }
        });

        // Add components to the frame
        add(new JLabel("Input Text:"), BorderLayout.NORTH);
        add(inputScrollPane, BorderLayout.CENTER);
        add(summarizeButton, BorderLayout.SOUTH);
        add(new JLabel("Summary:"), BorderLayout.WEST);
        add(outputScrollPane, BorderLayout.EAST);
    }

    // Method to summarize text
    private String summarizeText(String text) {
        if (text.isEmpty()) {
            return "Please enter some text to summarize.";
        }

        // Split text into sentences
        String[] sentences = text.split("[.!?]\\s*");

        // Split text into words and calculate word frequencies
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }

        // Score sentences based on word frequencies
        Map<String, Integer> sentenceScores = new HashMap<>();
        for (String sentence : sentences) {
            String[] sentenceWords = sentence.toLowerCase().split("\\s+");
            int score = 0;
            for (String word : sentenceWords) {
                score += wordFrequencies.getOrDefault(word, 0);
            }
            sentenceScores.put(sentence, score);
        }

        // Sort sentences by score in descending order
        List<Map.Entry<String, Integer>> sortedSentences = new ArrayList<>(sentenceScores.entrySet());
        sortedSentences.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Select top 3 sentences for the summary
        StringBuilder summary = new StringBuilder();
        int summaryLength = Math.min(3, sortedSentences.size());
        for (int i = 0; i < summaryLength; i++) {
            summary.append(sortedSentences.get(i).getKey()).append(". ");
        }

        return summary.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
