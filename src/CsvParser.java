import java.io.*;
import java.util.*;

class CsvParser {

    Set<String> getActor(String actor) {
        Set<String> actors = new HashSet<>();
        try {
            // Iterate over movies
            for (MovieInfo movieInfo : loadCsv()) {
                // If the movie actor contains parts of the search add to List (ONLY ONCE)
                if(movieInfo.getActorOne().contains(actor)) actors.add(movieInfo.getActorOne());
            }
            return actors;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<MovieInfo> getActorMovies(String actor) {
        try {
            // Iterate over movies
            List<MovieInfo> movieList = new ArrayList<>();
            for (MovieInfo movieInfo : loadCsv()) {
                // If the movie actor contains parts of the search add to List
                if(movieInfo.getActorOne().contains(actor)) movieList.add(movieInfo);
            }
            return movieList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<MovieInfo> getMovie(String movie) {
        try {
            // Iterate over movies
            List<MovieInfo> movieList = new ArrayList<>();
            for (MovieInfo movieInfo : loadCsv()) {
                // If the movie title contains parts of the search add to List
                if(movieInfo.getTitle().contains(movie)) movieList.add(movieInfo);
            }
            return movieList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<MovieInfo> loadCsv() throws FileNotFoundException {
        Scanner read = new Scanner(new File("movie_metadatacsv.csv"));
        List<MovieInfo> movieInfoList = new ArrayList<>();
        while(read.hasNextLine()) {
            // Takes line from CSV File
            String line = read.nextLine();
            // Splits all words where there's a ','
            String[] values = line.split(",");
            // Add all the values to an ArrayList
            List<String> list = Arrays.asList(values);
            // Creates MovieInfo Object based on values from the list
            MovieInfo mI = createMovieInfo(list);
            movieInfoList.add(mI);
        }
        return movieInfoList;
    }

    private MovieInfo createMovieInfo(List<String> list) {
        //Column indices from CSV: #11 Actor, #12 Movie Title, #24 Title Year, #26 IMDB
        MovieInfo mI = new MovieInfo();
        if(!list.get(10).equals("")) {
            mI.setActorOne(list.get(10));
        }
        if(!list.get(11).equals("")) {
            mI.setTitle(list.get(11));
        }
        if(!list.get(23).equals("")) {
            mI.setTitleYear(Integer.parseInt(list.get(23)));
        }
        if(!list.get(25).equals("")) {
            mI.setRating(Double.parseDouble(list.get(25)));
        }
        return mI;
    }
}
