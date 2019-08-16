package com.example.multiplication;

import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

class SelectionSupporter {

    private LinearLayout multiplicationSetupLayout;

    SelectionSupporter(LinearLayout multiplicationSetupLayout) {
        this.multiplicationSetupLayout = multiplicationSetupLayout;
    }

    boolean checkIfAnythingSelected() {
        return getCheckedElements().size() > 0;
    }

    ArrayList<Integer> getCheckedElements() {
        ArrayList<Integer> checkedElements = new ArrayList<>();
        for (int i = 0; i < multiplicationSetupLayout.getChildCount(); i++) {
            if (multiplicationSetupLayout.getChildAt(i) instanceof Switch) {
                Switch switchElement = (Switch) multiplicationSetupLayout.getChildAt(i);
                if (switchElement.isChecked()) {
                    checkedElements.add(i + 1);
                }
            }
        }
        return checkedElements;
    }
}
