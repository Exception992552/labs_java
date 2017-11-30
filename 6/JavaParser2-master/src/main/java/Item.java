import javax.swing.text.Document;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Item {
    Document itemHtml;
    String itemUrl;
    String itemCode;
    ArrayList<Comment> allComments;
    String csvCommenets;

    public Item(String itemUrl, String itemCode, ArrayList<Comment> allComments) {
        this.itemUrl = itemUrl;
        this.itemCode = itemCode;
        this.allComments = allComments;
    }

    public void writeToCsvFile() {
        String FILENAME = "P:/Files/Code/Java/JavaParser/scv files" + "/" + this.itemCode + ".csv";
        BufferedWriter bWriter = null;
        FileWriter fWriter = null;

        try {
            String content = new String();
            content += this.allComments;
            fWriter = new FileWriter(FILENAME);
            bWriter = new BufferedWriter(fWriter);
            bWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bWriter != null) {
                    bWriter.close();
                }
                if (fWriter != null) {
                    fWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCsvCommenets(String csvCommenets) {
        for (Comment comment : allComments) {
            this.csvCommenets += comment.csvText;
        }
    }

    public void setItemHtml(Document itemHtml) {
        this.itemHtml = itemHtml;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Document getItemHtml() {
        return itemHtml;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public String getItemCode() {
        return itemCode;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public String getCsvCommenets() {
        return csvCommenets;
    }
}
