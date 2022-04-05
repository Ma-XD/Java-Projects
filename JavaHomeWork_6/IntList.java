public class IntList {
    private final Element First = new Element(0);

    private static class Element {
        private int value;
        private Element next;
        private Element prev;

        private Element(int value) {
            this.value = value;
        }

        private int getValue() {
            return value;
        }

        private void setValue(int value) {
            this.value = value;
        }

        private Element getNext(){
            return next;
        }

        private Element getPrev(){
            return prev;
        }
    }


    private void checkFirstState() {
        if (First.getValue() == 0) {
            First.next = First;
            First.prev = First;
        }
    }

    public int size() {
        return First.getValue();
    }


    public void addFirst(int value) {
        Element element = new Element(value);
        checkFirstState();
        Element tempNext = First.next;
        First.next = element;
        tempNext.prev = element;
        element.prev = First;
        element.next = tempNext;
        First.setValue(First.getValue() + 1);
    }

    public void addLast(int value) {
        Element element = new Element(value);
        checkFirstState();
        Element tempPrev = First.prev;
        First.prev = element;
        tempPrev.next = element;
        element.next = First;
        element.prev = tempPrev;
        First.setValue(First.getValue() + 1);
    }


    public int getFirst() {
        return First.next.getValue();
    }

    public void setFirst(int value) {
        First.next.setValue(value);
    }


    public int getLast() {
        return First.prev.getValue();
    }

    public void setLast(int value) {
        First.prev.setValue(value);
    }


    public String toString(){
        StringBuilder str = new StringBuilder();
        return allElements(str, First.next).toString();
    }

    private StringBuilder allElements(StringBuilder str, Element list){
        str.append(list.getValue());
        if (list.next != First) {
            str.append(' ');
            allElements(str, list.getNext());
        }
        return str;
    }


    /** methods for  WordStatLineIndex **/

    public String lineIndex(){
        StringBuilder str = new StringBuilder();
        return LineAndNumberInLIne(str, First.next, true).toString();
    }

    private StringBuilder LineAndNumberInLIne(StringBuilder str, Element list, boolean space){
        str.append(list.getValue());
        if (list.next != First) {
            if (space) {
                str.append(' ');
            } else {
                str.append(':');
            }
            LineAndNumberInLIne(str, list.getNext(), !space);
        }
        return str;
    }
}

