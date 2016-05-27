package cn.edu.gdei.filemanager.Dummy;

public class FileItem {

    private String title;
    private String hint;
    private String author;
    private String time;

    public FileItem(String title, String hint, String author, String time) {
        this.title = title;
        this.hint = hint;
        this.author = author;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}