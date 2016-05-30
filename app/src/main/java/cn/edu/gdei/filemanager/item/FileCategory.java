package cn.edu.gdei.filemanager.item;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class FileCategory implements ParentListItem {

    private List<FileItem> files;
    private String category;

    public FileCategory(List files, String category) {
        this.files = files;
        this.category = category;
    }

    public List<FileItem> getFiles() {
        return files;
    }

    public void setFiles(List<FileItem> files) {
        this.files = files;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public List getChildItemList() {
        return files;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
