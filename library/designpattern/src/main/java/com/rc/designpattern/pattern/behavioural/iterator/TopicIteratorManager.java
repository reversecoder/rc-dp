package com.rc.designpattern.pattern.behavioural.iterator;

import com.rc.designpattern.pattern.behavioural.observer.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class TopicIteratorManager {

    private static TopicIteratorManager topicIteratorManager;
    private List<Topic> topics;

    private TopicIteratorManager() {
        topics = new ArrayList<>();
    }

    public static TopicIteratorManager getInstance() {
        if (topicIteratorManager == null) {
            topicIteratorManager = new TopicIteratorManager();
        }
        return topicIteratorManager;
    }

    private TopicIterator getIterator() {
        return new TopicIterator(topics);
    }

    public Topic getTopic(String tagName) {
        Iterator<Topic> iterator = getIterator();
        Topic topic = null;
        while (iterator.hasNext()) {
            topic = iterator.next();
            if (topic.getTopicName().equalsIgnoreCase(tagName)) {
                return topic;
            }
        }
        return topic;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

//    https://howtodoinjava.com/design-patterns/behavioral/iterator-design-pattern/

//    Topic[] topics = new Topic[5];
//    topics[0] = new Topic("topic1");
//    topics[1] = new Topic("topic2");
//    topics[2] = new Topic("topic3");
//    topics[3] = new Topic("topic4");
//    topics[4] = new Topic("topic5");
//
//    List<Topic> list = new TopicList(topics);
//
//    Iterator<Topic> iterator = list.iterator();
//
//        while(iterator.hasNext()) {
//        Topic currentTopic = iterator.next();
//        System.out.println(currentTopic.getName());
//    }

}
