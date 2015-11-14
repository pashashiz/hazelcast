package com.ps.tutorial;

import com.hazelcast.core.*;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import org.apache.log4j.Logger;

public class SampleExample {

    private static final Logger log = Logger.getLogger(SampleExample.class);

    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, String> test = hz.getMap("test");
        test.addEntryListener(new Listener(), true);
        for (int i = 0; i < 10; i++) {
            test.put("key-" + i, "value-" + i);
            Thread.sleep(1000);
        }
    }

    public static class Listener implements EntryAddedListener<String, String>,
            EntryUpdatedListener<String, String>, EntryRemovedListener<String, String> {

        public void entryAdded(EntryEvent<String, String> event) {
            log.info("Entry " + event + " was added");
        }

        public void entryRemoved(EntryEvent<String, String> event) {
            log.info("Entry " + event + " was removed");
        }

        public void entryUpdated(EntryEvent<String, String> event) {
            log.info("Entry " + event + " was updated");
        }
    }

}
