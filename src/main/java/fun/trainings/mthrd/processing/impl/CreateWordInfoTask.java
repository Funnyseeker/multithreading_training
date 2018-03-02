package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;
import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.ExecutationState;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;

import java.util.StringTokenizer;

public class CreateWordInfoTask extends AbstractTask {
    String input;
    SearcherAndAccessor<WordInfo> infoListAccessor;

    public CreateWordInfoTask(String input, SearcherAndAccessor<WordInfo> infoListAccessor) {
        this.input = input;
        this.infoListAccessor = infoListAccessor;
    }


    @Override
    public ExecutationState execute() {
        StringTokenizer tokenizer = new StringTokenizer(input, "|");
        String word = tokenizer.nextToken().trim();
        WordInfo info = null;
        boolean needCreate = true;
        try {
            info = infoListAccessor.find(word);
            needCreate = (info == null);
        } catch (MethdoNotSupportedException e) {
            e.printStackTrace();
            return returnState(ExecutationState.FAIL);
        }
        if (needCreate) {
            info = new WordInfo();
            info.setWord(word);
            infoListAccessor.add(info);
        }
        info.addMorphInfo(tokenizer.nextToken().trim());
        info.addFrequency(Long.parseLong(tokenizer.nextToken().trim()));
        return returnState(ExecutationState.COMPLETE);
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
