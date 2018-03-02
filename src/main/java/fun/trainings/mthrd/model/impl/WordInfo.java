package fun.trainings.mthrd.model.impl;

import fun.trainings.mthrd.model.Info;

import java.util.ArrayList;
import java.util.List;

public class WordInfo implements Info {

    private String word;
    private List<String> morphInfoList = new ArrayList<>();
    private List<Long> frequencyList = new ArrayList<>();
    private List<String> additionalInfoList = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getMorphInfoList() {
        return morphInfoList;
    }

    public void setMorphInfoList(List<String> morphInfoList) {
        this.morphInfoList = morphInfoList;
    }

    public List<Long> getFrequencyList() {
        return frequencyList;
    }

    public void setFrequencyList(List<Long> frequencyList) {
        this.frequencyList = frequencyList;
    }

    public List<String> getAdditionalInfoList() {
        return additionalInfoList;
    }

    public void setAdditionalInfoList(List<String> additionalInfoList) {
        this.additionalInfoList = additionalInfoList;
    }

    public synchronized void addMorphInfo(String morphInfo) {
        morphInfoList.add(morphInfo);
    }

    public synchronized void addFrequency(Long frequency) {
        frequencyList.add(frequency);
    }

    public synchronized void addAdditionalInfo(String additionalInfo) {
        additionalInfoList.add(additionalInfo);
    }
}
