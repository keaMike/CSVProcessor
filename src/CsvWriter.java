import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class CsvWriter {
    private CsvParser csvParser = new CsvParser();

    void writeToCsv() {
        try {
            FileOutputStream fOS = new FileOutputStream(new File("MovieCSV.csv"));
            PrintStream pS = new PrintStream(fOS);
            pS.println("Actor, Title, Title Year, IMDB Rating");
            for(MovieInfo mI : csvParser.loadCsv()) {
                pS.print(mI.getActorOne());
                pS.print(",");
                pS.print(mI.getTitle());
                pS.print(",");
                pS.print(mI.getTitleYear());
                pS.print(",");
                pS.print(mI.getRating());
                pS.print(",");
                pS.print("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
