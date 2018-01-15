package com.kevin;

import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        try {
            GlassFishProperties glassfishProperties = new GlassFishProperties();
            glassfishProperties.setPort("http-listener", 9191);
            // glassfishProperties.setPort("http-listener", Integer.parseInt(System.getenv("PORT")));
            final GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);
            glassfish.start();

            ScatteredArchive war = new ScatteredArchive("TestingEmbeddedGlassfish", ScatteredArchive.Type.WAR, new File("src/main/webapp"));
            war.addClassPath(new File("target/classes"));
            glassfish.getDeployer().deploy(war.toURI());

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(glassfish + "shutdown now !!");
                        glassfish.dispose();
                    } catch (GlassFishException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (GlassFishException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

