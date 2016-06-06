package cn.edu.gdei.filemanager.item;

public class HomeAnnouncementItem {
    private String title;
    private String hint;
    private int imageId;

    public HomeAnnouncementItem(String title, String hint, int imageId) {
        this.title = title;
        this.hint = hint;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
