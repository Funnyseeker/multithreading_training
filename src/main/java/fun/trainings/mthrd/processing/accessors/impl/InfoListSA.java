package fun.trainings.mthrd.processing.accessors.impl;

import fun.trainings.mthrd.exceptions.MethdoNotSupportedException;
import fun.trainings.mthrd.model.impl.WordInfo;
import fun.trainings.mthrd.processing.accessors.Accessor;
import fun.trainings.mthrd.processing.accessors.Searcher;
import fun.trainings.mthrd.processing.accessors.SearcherAndAccessor;
import fun.trainings.mthrd.processing.accessors.filtering.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InfoListSA implements SearcherAndAccessor<WordInfo> {

    private final Map<String, WordInfo> infoList = new HashMap<>();

    @Override
    public WordInfo get() throws MethdoNotSupportedException {
        throw new MethdoNotSupportedException(this.getClass().getName(), "get()");
    }

    @Override
    public List<WordInfo> find(Filter<WordInfo> filter) {
        synchronized (infoList) {
            List<WordInfo> searchList = infoList.values().stream().filter(filter.getPredicate()).collect(Collectors.toList());
            return searchList;
        }
    }

    @Override
    public WordInfo find(String word) {
        synchronized (infoList) {
            return infoList.get(word);
        }
    }

    @Override
    public void add(WordInfo object) {
        synchronized (infoList) {
            infoList.put(object.getWord(), object);
        }
    }
}
