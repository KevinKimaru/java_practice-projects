package com.kevin.data;

import com.kevin.model.Gif;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 8/7/2017.
 */
@Component
public class GifRepository {
    private static final List<Gif> ALL_GIFS = Arrays.asList(
            new Gif("car", 1, LocalDate.of(2016, 5, 23), "Kevin Kimaru", true),
            new Gif("car1", 2, LocalDate.of(2017, 8, 4), "Liz Mumbi", false),
            new Gif("car2",3, LocalDate.of(2014, 2, 11), "Eric Liquor", false),
            new Gif("car3", 3, LocalDate.of(2013, 3, 20), "Vin Diesel", true),
            new Gif("car4",1, LocalDate.of(2008, 11, 7), "Rock", false),
            new Gif("car5", 2, LocalDate.of(2016, 9, 14), "Gladiaons", true)
    );

    public Gif findByName(String name) {
        for (Gif gif : ALL_GIFS) {
            if (gif.getName().equals(name)) {
                return gif;
            }
        }
        return null;
    }

    public List<Gif> getAllGifs() {
        return ALL_GIFS;
    }

    public List<Gif> findByCategoryId(int id) {
        List<Gif> gifs = new ArrayList<>();
        for (Gif gif: ALL_GIFS) {
            if (gif.getCategoryId() == id) {
                gifs.add(gif);
            }
        }
        return gifs;
    }

}
