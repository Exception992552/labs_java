public class Comment {
    String rating;
    String text;
    String csvText;

    public Comment(String rating, String text) {
        this.rating = rating;
        this.text = text;
        setCsvText();
    }

    public Comment(String rating) {

        this.rating = rating;
    }

    public void setCsvText() {
        this.csvText = this.rating + ", '" + this.text + "'\n" ;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getCsvText() {
        return csvText;
    }
}
