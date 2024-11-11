public class DictionaryItem {
    //Marc Sulsenti
    //I pledge my honor that I have abided by the Stevens Honor System

    private String word;
    private int count;

    public DictionaryItem(String word, int count){
        this.word=word;
        this.count=count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
