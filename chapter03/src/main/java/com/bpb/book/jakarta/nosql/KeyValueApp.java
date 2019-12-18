package com.bpb.book.jakarta.nosql;

import jakarta.nosql.mapping.keyvalue.KeyValueTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

public class KeyValueApp {

    public static void main(String[] args) throws InterruptedException {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God diana = new God(1L, "Diana", "Hunt");

            KeyValueTemplate template =
                    container.select(KeyValueTemplate.class)
                            .get();

            template.put(diana);

            final Optional<God> god = template.get(1L, God.class);
            System.out.println("query : " + god);
            template.delete(1L);

            System.out.println("query again: " +
                    template.get(1L, God.class));

        }

        System.exit(0);

    }
}
