package cn.edu.gdei.filemanager.item;

public class AnnouncementItem {
    private String title;
    private String hint;
    private String time;
    private int imageId;

    public AnnouncementItem(String title, String hint, String time, int imageId) {
        this.title = title;
        this.hint = hint;
        this.time = time;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
