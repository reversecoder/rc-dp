package com.rc.shapemanager.pattern.behavioral.iterator;

import com.rc.shapemanager.pattern.behavioral.observer.Topic;

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