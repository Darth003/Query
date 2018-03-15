package com.devdefiance.android.query.Objects;

/**
 * Created by darth on 3/15/18.
 */

public class SourceModel {

    private String sourceName;
    private int sourceImage;
    private boolean isSelected = true;

    public SourceModel(String sourceName, int sourceImage) {
        this.sourceName = sourceName;
        this.sourceImage = sourceImage;
    }

    public int getSourceImage() {
        return sourceImage;
    }

    public String getSourceName() {
        return sourceName;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
