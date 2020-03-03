import java.io.IOException;
import java.util.*;

class Menu {

    private Scanner sc = new Scanner(System.in);
    private CsvParser csvParser = new CsvParser();
    private CsvWriter csvWriter = new CsvWriter();

    void menu() {

        do {
            System.out.println(
                    "Welcome to Strucutred Data Service\n" +
                    "(1) Search on actor\n" +
                    "(2) Search on movie title\n" +
                    "(3) Sort based on IMDB rating\n" +
                    "(4) Write to CSV file\n" +
                    "(5) Quit"
            );

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    // Search on Actor name
                    System.out.println("What is the name of the actor?");
                    String actorToken = sc.nextLine();
                    // If there is more than one actor, let user choose which one to pick
                    Set<String> actors = csvParser.getActor(actorToken);
                    if(actors.size() > 1) {
                        // Iterate over actors found
                        System.out.println("We found multiple actors, please choose one");
                        int i = 1;
                        for(String actor : actors) {
                            System.out.println(("(" + i + ") " + actor));
                            i++;
                        }
                        // User chooses index
                        int chosenActor = sc.nextInt(); sc.nextLine();
                        // Turn set to List so I can use get()
                        List<String> actorList = new ArrayList<>(actors);
                        // Get movies with given actor with user chosen index - 1, because of List starting at 0
                        List<MovieInfo> movies = csvParser.getActorMovies(actorList.get(chosenActor - 1));
                        // Print information found
                        if(movies != null) for(MovieInfo mI : movies)System.out.println(mI.toString());
                        else System.out.println("Sorry we do not have information about that actor\n");

                    } else {
                        // If there is only one actor retrieve movies from actor
                        List<MovieInfo> movies = csvParser.getActorMovies(actorToken);
                        // Print information found
                        if(movies != null) for(MovieInfo mI : movies)System.out.println(mI.toString());
                        else System.out.println("Sorry we do not have information about that actor\n");
                    }
                    break;
                case "2":
                    System.out.println("What is the name of the movie?");
                    String movie = sc.nextLine();
                    // Get movies based on user input (Movie title)
                    List<MovieInfo> movies = csvParser.getMovie(movie);
                    // Print information found
                    if(movies != null) for(MovieInfo mI : movies)System.out.println(mI.toString());
                    else System.out.println("Sorry we do not have information about that movie\n");
                    break;
                case "3":
                    try {
                        // Retrieve movies
                        List<MovieInfo> movieInfoList = csvParser.loadCsv();
                        // Sort movies based on IMBD rating
                        movieInfoList.sort(Comparator.comparingDouble(MovieInfo::getRating));
                        // Print information
                        for(MovieInfo mI : movieInfoList) {
                            System.out.println(mI.getTitle() + "\n" + mI.getRating());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    // Write to a new CSV file
                    csvWriter.writeToCsv();
                    break;
                case "5":
                    System.exit(1);
            }

        } while (true);
    }
}
