package cn.edu.gdei.filemanager.Dummy;

public class AnnouncementItem {
    private String title;
    private String hint;
    private String time;

    public AnnouncementItem(String title, String hint, String time) {
        this.title = title;
        this.hint = hint;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
