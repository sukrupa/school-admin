package org.sukrupa.student;

public class StudentOptionsElement {
        public boolean isSelected() {
            return selected;
        }

        public String getValue() {
            return value;
        }

        private final String value;
        private final boolean selected;

        public StudentOptionsElement(String value, boolean selected) {
            this.value = value;
            this.selected = selected;
        }
}
