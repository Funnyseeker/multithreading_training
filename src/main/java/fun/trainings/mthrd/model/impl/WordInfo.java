package fun.trainings.mthrd.model.impl;

import fun.trainings.mthrd.model.Info;

public class WordInfo implements Info {

    private String word;
    private String morphInfo;
    private Long frequency;
    private String additionalInfo;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMorphInfo() {
        return morphInfo;
    }

    public void setMorphInfo(String morphInfo) {
        this.morphInfo = morphInfo;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
