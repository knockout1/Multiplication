package com.example.multiplication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class SelectionSupporter {

    private LinearLayout multiplicationSetupLayout;

    SelectionSupporter(LinearLayout multiplicationSetupLayout){
        this.multiplicationSetupLayout = multiplicationSetupLayout;
    }

    boolean checkIfAnythingSelected(){
        return getCheckedElements().size() > 0;
    }

    ArrayList<Integer> getCheckedElements() {
        ArrayList<Integer> checkedElements = new ArrayList<>();
        int count = multiplicationSetupLayout.getChildCount();
        Switch switchElement;
        for (int i = 0; i < count; i++) {
            View view = multiplicationSetupLayout.getChildAt(i);
            if (view instanceof Switch) {
                switchElement = (Switch) multiplicationSetupLayout.getChildAt(i);
                if (switchElement.isChecked()) {
                    checkedElements.add(i+1);
                }
            }
        }
        return checkedElements;
    }

}
