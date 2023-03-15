package Movies;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int printN = scanner.nextInt();
        scanner.nextLine();
        MoviesCollection moviesCollection = new MoviesCollection();
        Set<String> genres = fillCollection(scanner, moviesCollection);
        System.out.println("=== PRINT BY GENRE ===");
        for (String genre : genres) {
            System.out.println("GENRE: " + genre);
            moviesCollection.printByGenre(genre);
        }
        System.out.println("=== TOP N BY RATING ===");
        printList(moviesCollection.getTopRatedN(printN));

        System.out.println("=== COUNT BY DIRECTOR ===");
        printMap(moviesCollection.getCountByDirector(n));


    }

    static void printMap(Map<String,Integer> countByDirector) {
        countByDirector.entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    static void printList(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          MoviesCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Movie movie = new Movie(parts[0], parts[1], parts[2], Float.parseFloat(parts[3]));
            collection.addMovie(movie);
            categories.add(parts[2]);
        }
        return categories;
    }
}

class Movie{
    public String title;
    public String director;
    public String genre;
    public float rating;

    public Movie(String title, String director, String genre, float rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s) %.2f", title, director, genre, rating);
    }
}

class MoviesCollection{

    List<Movie> movies;
    HashMap<String, Integer> hashMap;
    Map<String, Integer> map = new TreeMap<>();

    public MoviesCollection() {
        movies = new ArrayList<>();
        hashMap = new HashMap<>();
        map = new TreeMap<>();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void printByGenre(String genre) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle).thenComparing(Movie::getRating);
        movies = movies.stream().sorted(comparator).collect(Collectors.toList());
        for (Movie m : movies){
            if(m.genre.equals(genre)){
                System.out.println(m);
            }
        }
    }

    public List<Movie> getTopRatedN(int n){
        //враќа листа на најдобро рангираните N филмови (ако има помалку од N филмови во колекцијата, ги враќа сите).
        Comparator<Movie> comparator = Comparator.comparing(Movie::getRating).reversed();
        return movies.stream().sorted(comparator).limit(n).collect(Collectors.toList());
    }

    public void directorMovieHash(){
        int counter = 0;
        for (Movie m : movies){
            String director = m.director;
            for (Movie movie : movies){
                if(movie.director.equals(director)){
                    counter++;
                }
            }
            hashMap.put(director, counter);
            map.put(director, counter);
            counter = 0;
        }
    }

    public Map<String, Integer> getCountByDirector(int n){
        directorMovieHash();
        Comparator<Movie> comparator = Comparator.comparing(Movie::getDirector);

//        hashMap.values().stream().sorted(comparator);

        return map;
    }

}