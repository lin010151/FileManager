package cn.edu.gdei.filemanager.item;

public class FileSummary {
    private int pass;
    private int pending;
    private int auditing;

    public FileSummary(int pass, int pending, int auditing) {
        this.pass = pass;
        this.pending = pending;
        this.auditing = auditing;
    }

    public String getPass() {
        return "" + pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getPending() {
        return "" + pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public String getAuditing() {
        return "" + auditing;
    }

    public void setAuditing(int auditing) {
        this.auditing = auditing;
    }
}
