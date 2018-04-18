package com.kevin.giflib.data;

import com.kevin.giflib.model.Gif;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 2/12/2018.
 */
@Component
public class GifRepository {
    private static final List<Gif> ALL_GIFS = Arrays.asList(
            new Gif("bird0", 1, LocalDate.of(2017, 7, 16), "Kevin Kimaru", true),
            new Gif("bird1", 2, LocalDate.of(2014, 12, 17), "Kevin Chege", false),
            new Gif("bird2", 3, LocalDate.of(2018, 4, 22), "Robert Kimani", false),
            new Gif("bird3", 1, LocalDate.of(2013, 9, 9), "Peris Wangari", true),
            new Gif("bird4", 2, LocalDate.of(2011, 10, 6), "Liz Neema", false),
            new Gif("bird5", 3, LocalDate.of(2017, 8, 23), "Rhine Machuma", true)

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
