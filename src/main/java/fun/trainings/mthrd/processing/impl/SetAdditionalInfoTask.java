package fun.trainings.mthrd.processing.impl;

import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.ExecutationState;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;

import java.util.StringTokenizer;

public class SetAdditionalInfoTask extends AbstractTask {
    int currTry = 0;
    String input;
    SearcherAndAccessor<WordInfo> infoListAccessor;

    public SetAdditionalInfoTask(String input, SearcherAndAccessor<WordInfo> infoListAccessor) {
        this.input = input;
        this.infoListAccessor = infoListAccessor;
    }

    @Override
    public ExecutationState execute() {
        currTry++;
        StringTokenizer tokenizer = new StringTokenizer(input, "|");
        try {
            String word = tokenizer.nextToken().trim();
            WordInfo wordInfo = infoListAccessor.find(word);
            if (wordInfo == null && currTry <= MAX_RETRIES) {
                return returnState(ExecutationState.RETRY);
            } else if (currTry > MAX_RETRIES) {
                return returnState(ExecutationState.FAIL);
            }
            String additionalInfo = tokenizer.nextToken().trim();
            wordInfo.addAdditionalInfo(additionalInfo);
            return returnState(ExecutationState.COMPLETE);
        } catch (Exception e) {
            e.printStackTrace();
            return returnState(ExecutationState.FAIL);
        }
    }
}
