package com.rc.designpattern.pattern.behavioural.iterator;

import com.rc.designpattern.pattern.behavioural.observer.Topic;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class TopicList implements List<Topic> {
    private Topic[] topics;

    public TopicList(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public Iterator<Topic> iterator() {
        return new TopicIterator(topics);
    }
}